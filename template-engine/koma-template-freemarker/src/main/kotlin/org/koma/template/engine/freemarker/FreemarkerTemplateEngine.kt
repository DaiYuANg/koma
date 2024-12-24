package org.koma.template.engine.freemarker

import freemarker.template.Configuration
import freemarker.template.Template
import org.koma.shared.api.KomaTemplateEngine
import org.koma.shared.data.TemplateEngineProvider
import java.io.StringWriter
import java.nio.file.FileSystems

class FreemarkerTemplateEngine : KomaTemplateEngine {

  override fun provider(): TemplateEngineProvider {
    return TemplateEngineProvider.FREEMARKER
  }

  override fun parse(content: String): String {
    val jimfsTemplateLoader = JimfsTemplateLoader(FileSystems.getDefault())
    val cfg = Configuration(Configuration.getVersion())
    cfg.templateLoader = jimfsTemplateLoader
    val template: Template = cfg.getTemplate("example")
    val dataModel = mapOf("name" to "Jimfs User")
    val out = StringWriter()
    template.process(dataModel, out)
    return out.buffer.toString()
  }
}