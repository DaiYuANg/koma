@file:JvmName("KomaCompiler")

package org.koma.core.compiler

import `in`.wilsonl.minifyhtml.Configuration
import `in`.wilsonl.minifyhtml.MinifyHtml
import io.github.oshai.kotlinlogging.KotlinLogging
import io.smallrye.mutiny.Uni
import org.apache.commons.io.FileUtils
import org.koma.api.Engine
import org.koma.api.SourceParser
import org.koma.core.config.KomaConfig
import org.koma.core.context.CompileContext
import org.koma.core.model.KomaLayout
import org.koma.core.util.outputFilename
import org.koma.core.vistor.CompileSourceVisitor
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.time.Duration
import java.util.*
import java.util.concurrent.Executors
import java.util.function.Consumer
import kotlin.io.path.Path

class KomaCompiler {
  private val log = KotlinLogging.logger {}
  private val cfg: Configuration = Configuration.Builder()
    .setKeepHtmlAndHeadOpeningTags(true)
    .setMinifyCss(true)
    .setMinifyJs(true)
    .build()

  fun compile(config: KomaConfig, layout: KomaLayout) {
    val outputFile = Path(config.output().directory()).toFile()
    if (config.output().clean() && outputFile.exists()) {
      FileUtils.forceDelete(outputFile)
    }

    val sourceParser = ServiceLoader.load(SourceParser::class.java).toSet()

    log.info { "KomaCompiler start${sourceParser}" }

    val compileContext = CompileContext(sourceParser)

    Files.walkFileTree(layout.content(), CompileSourceVisitor(compileContext))

    val templateProcessors = ServiceLoader.load(Engine::class.java).toSet()

    Uni.combine().all()
      .unis<Uni<Void>>(compileContext.process)
      .usingConcurrencyOf(compileContext.process.size)
      .discardItems()
      .await()
      .indefinitely()

    val writeProcess = compileContext.htmlContext.map { (filename, document) ->
      Uni.createFrom()
        .item(document.body())
        .emitOn(Executors.newVirtualThreadPerTaskExecutor())
        .map { templateProcessors.first().parse(it.html()) }
        .map { MinifyHtml.minify(it, cfg) }
        .invoke(Consumer {
          val outputPath = Path(config.output().directory(), outputFilename(filename, document.hashCode()))
          FileUtils.writeStringToFile(
            outputPath.toFile(),
            it,
            StandardCharsets.UTF_8
          )
        })
    }.toList()

    Uni.combine().all()
      .unis<Uni<Void>>(writeProcess)
      .usingConcurrencyOf(writeProcess.size)
      .with {
        log.info { "KomaCompiler end" }
      }
  }
}



