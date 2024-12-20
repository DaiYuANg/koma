@file:JvmName("KomaCompiler")

package org.koma.compiler.compiler

import io.github.oshai.kotlinlogging.KotlinLogging
import java.nio.file.Files.walkFileTree
import java.util.*
import kotlin.io.path.Path
import org.apache.commons.io.FileUtils
import org.koma.compiler.config.KomaConfig
import org.koma.compiler.context.CompileContext
import org.koma.compiler.fs.CompileSourceVisitor
import org.koma.compiler.model.KomaLayout
import org.koma.shared.api.SourceParseableDetector
import org.koma.shared.api.Template
import org.koma.shared.api.Theme

class KomaCompiler(
    private val sourceParsers: Set<SourceParseableDetector> =
        ServiceLoader.load(SourceParseableDetector::class.java).toSet(),
    private val themes: Set<Theme> = ServiceLoader.load(Theme::class.java).toSet(),
    private val templates: Set<Template> = ServiceLoader.load(Template::class.java).toSet()
) {
  private val log = KotlinLogging.logger {}

  init {
    log.atDebug { message = "source parsers:${sourceParsers.size}" }
    log.atDebug { message = "themes:${themes}" }
    log.atDebug { message = "templates:${templates}" }
  }

  fun compile(config: KomaConfig, layout: KomaLayout) {
    val outputFile = Path(config.output.directory).toFile()
    if (config.output.clean && outputFile.exists()) {
      FileUtils.forceDelete(outputFile)
    }
    val compileContext = CompileContext(sourceParsers)
    walkFileTree(layout.content, CompileSourceVisitor(compileContext))

    //    val templateProcessors = ServiceLoader.load(Template::class.java).toSet()
    //
    //    compileContext.htmlContext.forEach { (filename, document) ->
    //      val body = document.body()
    //      val template = templateProcessors.first().parse(body.html())
    //
    //      val outputPath = Path(config.output.directory, outputFilename(filename,
    // document.hashCode()))
    //      FileUtils.writeStringToFile(
    //        outputPath.toFile(),
    //        template,
    //        StandardCharsets.UTF_8
    //      )
    //    }
  }
}
