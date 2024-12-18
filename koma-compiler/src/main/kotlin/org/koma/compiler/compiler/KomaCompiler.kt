@file:JvmName("KomaCompiler")

package org.koma.compiler.compiler

import `in`.wilsonl.minifyhtml.Configuration
import `in`.wilsonl.minifyhtml.MinifyHtml
import io.github.oshai.kotlinlogging.KotlinLogging
import org.apache.commons.io.FileUtils
import org.koma.compiler.config.KomaConfig
import org.koma.compiler.context.CompileContext
import org.koma.compiler.model.KomaLayout
import org.koma.compiler.util.outputFilename
import org.koma.compiler.fs.CompileSourceVisitor
import org.koma.shared.api.Template
import org.koma.shared.api.SourceParser
import org.koma.shared.api.Theme
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Files.walkFileTree
import java.util.*
import kotlin.io.path.Path

class KomaCompiler(
  private val sourceParsers: Set<SourceParser> = ServiceLoader.load(SourceParser::class.java).toSet(),
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
//      val outputPath = Path(config.output.directory, outputFilename(filename, document.hashCode()))
//      FileUtils.writeStringToFile(
//        outputPath.toFile(),
//        template,
//        StandardCharsets.UTF_8
//      )
//    }
  }
}



