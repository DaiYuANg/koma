package org.koma.feature.template.thymeleaf

import com.google.auto.service.AutoService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.koma.shared.api.KomaTemplateEngine
import org.koma.shared.data.TemplateEngineProvider
import org.koma.shared.data.TemplatePlaceholder
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import org.thymeleaf.templateresolver.FileTemplateResolver

@AutoService(KomaTemplateEngine::class)
class Thymeleaf : KomaTemplateEngine {
  private val classLoaderTemplateResolver =
    ClassLoaderTemplateResolver().apply {
      prefix = "templates/"
      suffix = ".html"
    }
  private val templateEngine: TemplateEngine by lazy {
    val engine = TemplateEngine()
    engine.setTemplateResolver(classLoaderTemplateResolver)
    engine
  }
  private val log = KotlinLogging.logger {}

  private val context by lazy { Context() }

  override fun provider(): TemplateEngineProvider {
    return TemplateEngineProvider.THYMELEAF
  }

  override fun parse(content: String): String {
    FileTemplateResolver()
    log.atInfo { message = "parse:${content.hashCode()}" }
    context.setVariable(TemplatePlaceholder.Content.placeholder, content)
    val html = templateEngine.process("layout", context)
    return html
  }
}
