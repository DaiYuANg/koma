package org.koma.feature.template.thymeleaf

import com.google.auto.service.AutoService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.koma.shared.api.Template
import org.koma.shared.data.TemplatePlaceholder
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

@AutoService(Template::class)
class Thymeleaf : Template {
  private val classLoaderTemplateResolver =
      ClassLoaderTemplateResolver().apply {
        prefix = "templates/"
        suffix = ".html"
      }
  private val templateEngine: TemplateEngine =
      TemplateEngine().apply { setTemplateResolver(classLoaderTemplateResolver) }
  private val log = KotlinLogging.logger {}

  override fun parse(content: String): String {
    log.atInfo { message = "parse:${content.hashCode()}" }
    val context = Context()
    context.setVariable(TemplatePlaceholder.Content.placeholder, content)
    val html = templateEngine.process("layout", context)
    return html
  }
}
