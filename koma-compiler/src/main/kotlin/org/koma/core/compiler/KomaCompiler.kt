@file:JvmName("KomaCompiler")

package org.koma.core.compiler

import `in`.wilsonl.minifyhtml.Configuration
import `in`.wilsonl.minifyhtml.MinifyHtml
import io.github.oshai.kotlinlogging.KotlinLogging
import org.apache.commons.io.FileUtils
import org.koma.shared.Engine
import org.koma.shared.SourceParser
import org.koma.core.config.KomaConfig
import org.koma.core.context.CompileContext
import org.koma.core.model.KomaLayout
import org.koma.core.util.outputFilename
import org.koma.core.vistor.CompileSourceVisitor
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.util.*
import kotlin.io.path.Path

class KomaCompiler {
  private val log = KotlinLogging.logger {}
  private val cfg: Configuration = Configuration.Builder()
    .setKeepHtmlAndHeadOpeningTags(true)
    .setMinifyCss(true)
    .setMinifyJs(true)
    .build()


  fun compile(config: KomaConfig, layout: KomaLayout) {
    val outputFile = Path(config.output.directory).toFile()
    if (config.output.clean && outputFile.exists()) {
      FileUtils.forceDelete(outputFile)
    }

    val sourceParser = ServiceLoader.load(SourceParser::class.java).toSet()

    log.info { "KomaCompiler start${sourceParser}" }

    val compileContext = CompileContext(sourceParser)

    Files.walkFileTree(layout.content(), CompileSourceVisitor(compileContext))

    val templateProcessors = ServiceLoader.load(Engine::class.java).toSet()

    compileContext.htmlContext.forEach { (filename, document) ->
      val body = document.body()
      val template = templateProcessors.first().parse(body.html())
      val compressed = MinifyHtml.minify(template, cfg)
      val outputPath = Path(config.output.directory, outputFilename(filename, document.hashCode()))
      FileUtils.writeStringToFile(
        outputPath.toFile(),
        compressed,
        StandardCharsets.UTF_8
      )
    }
  }
}



