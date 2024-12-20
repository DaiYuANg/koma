package org.koma.theme.bootstrap

import com.google.auto.service.AutoService
import org.koma.shared.api.Theme
import org.koma.shared.data.TemplateEngine

@AutoService(Theme::class)
class BootstrapTheme : Theme {
  override fun name(): String = "bootstrap"

  override fun template(): TemplateEngine = TemplateEngine.THYMELEAF

  override fun variables(): Map<String, Any> {
    TODO("Not yet implemented")
  }
}
