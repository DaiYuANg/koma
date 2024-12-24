@file:JvmName("KomaCompiler")

package org.koma.compiler.compiler

import io.github.oshai.kotlinlogging.KotlinLogging
import io.smallrye.mutiny.Uni
import org.koma.compiler.config.KomaConfig
import org.koma.compiler.context.CompileContext
import org.koma.compiler.fs.CompileSourceVisitor
import org.koma.compiler.theme.locate
import org.koma.shared.api.KomaTemplateEngine
import org.koma.shared.api.KomaTheme
import org.koma.shared.api.SourceParseableDetector
import java.nio.file.Files.walkFileTree
import java.nio.file.Path
import java.util.ServiceLoader
import java.util.concurrent.ExecutorService
import kotlin.io.path.absolutePathString

class KomaCompiler(
  private val sourceParsers: Set<SourceParseableDetector> =
    ServiceLoader.load(SourceParseableDetector::class.java).toSet(),
  private val komaThemes: Set<KomaTheme> = ServiceLoader.load(KomaTheme::class.java).toSet(),
  private val komaTemplateEngines: Set<KomaTemplateEngine> = ServiceLoader.load(KomaTemplateEngine::class.java).toSet(),
  private val executor: ExecutorService
) : Compiler {
  private val log = KotlinLogging.logger {}
  private val compileContext = CompileContext()

  init {
    log.atDebug { message = "source parsers:${sourceParsers.size}" }
    log.atDebug { message = "themes:${komaThemes}" }
    log.atDebug { message = "templates:${komaTemplateEngines}" }
  }

  override fun compile(config: KomaConfig, context: Path) {
    parseSourceFiles(config, context)
    val configTheme = config.metadata.theme
    val theme = komaThemes.first {
      it.name() == configTheme
    }
    locate(theme)
//    theme.javaClass.classLoader.getResource("META-INF/")
    log.atDebug { message = "find theme${theme}" }
  }

  private fun parseSourceFiles(config: KomaConfig, context: Path) {
    val visitor = CompileSourceVisitor(compileContext, sourceParsers)
    log.atDebug { message = "Compile Context:${context.absolutePathString()}" }
    val compilePages = Uni.createFrom().item(context.resolve(config.content.pages))
      .emitOn(executor)
      .invoke { pages ->
        run {
          log.atDebug { message = "Pages:${pages.absolutePathString()}" }
        }
      }
      .invoke { pages ->
        run {
          walkFileTree(pages, visitor)
        }
      }

    val compilePosts = Uni.createFrom().item(context.resolve(config.content.posts))
      .emitOn(executor)
      .invoke { posts ->
        run {
          log.atDebug { message = "Pages:${posts.absolutePathString()}" }
        }
      }
      .invoke { posts ->
        run {
          walkFileTree(posts, visitor)
        }
      }

    Uni.combine().all().unis(compilePosts, compilePages)
      .usingConcurrencyOf(2)
      .discardItems()
      .await().indefinitely()
  }
}
