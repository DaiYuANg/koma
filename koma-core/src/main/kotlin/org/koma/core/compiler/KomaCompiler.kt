@file:JvmName("KomaCompiler")
package org.koma.core.compiler

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.ast.Document
import com.vladsch.flexmark.util.data.MutableDataSet
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.apache.commons.io.filefilter.SuffixFileFilter
import org.apache.commons.io.filefilter.TrueFileFilter
import org.koma.core.config.KomaConfig
import org.koma.core.layout.KomaLayout
import org.koma.core.template.JteTemplateEngine
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import java.nio.charset.StandardCharsets


fun compile(config: KomaConfig, layout: KomaLayout): Unit {
  val rootPath = layout.basePath
  val content = rootPath.resolve("content").toFile()
  val files = FileUtils.listFiles(
    content,
    SuffixFileFilter(".md"),
    TrueFileFilter.INSTANCE
  )
  val options = MutableDataSet()
  val parser = Parser.builder(options).build()
  val renderer = HtmlRenderer.builder(options).build()
  for (file in files) {
    val fileContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8)
    val document: Document = parser.parse(fileContent)
    val html: String = renderer.render(document)
    System.err.println(file)
    val templateResolver = ClassLoaderTemplateResolver()
    templateResolver.prefix = "templates/" // 类路径下的 templates 文件夹
    templateResolver.suffix = ".html"
    val templateEngine = TemplateEngine()
    templateEngine.setTemplateResolver(templateResolver)
    JteTemplateEngine()
    val context = Context()
    context.setVariable("content", html)
    val output = templateEngine.process("layout", context)
    System.err.println(layout.dist().toAbsolutePath())
    val filename = FilenameUtils.getName(file.name)
    FileUtils.writeStringToFile(
      layout.dist().toAbsolutePath().resolve("$filename.html").toFile(),
      output,
      StandardCharsets.UTF_8
    )
  }
}