package org.koma.feature.template.thymeleaf

import com.google.auto.service.AutoService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.koma.shared.Engine
import org.koma.shared.TemplatePlaceholder
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver


@AutoService(Engine::class)
class ThymeleafEngine : Engine {

  private val classLoaderTemplateResolver = ClassLoaderTemplateResolver().apply {
    prefix = "templates/"
    suffix = ".html"
  }
  private val templateEngine: TemplateEngine = TemplateEngine().apply {
    setTemplateResolver(classLoaderTemplateResolver)
  }
  private val log = KotlinLogging.logger {}

  override fun parse(content: String): String {
    log.atInfo { message = "parse:${content.hashCode()}" }
    val context = Context()
    context.setVariable(TemplatePlaceholder.Content.placeholder, content)
    val html = templateEngine.process("layout", context)
    return html
  }
}