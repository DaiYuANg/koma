package org.koma.theme.bootstrap

import com.google.auto.service.AutoService
import org.koma.shared.api.KomaTheme
import org.koma.shared.data.TemplateEngineProvider
import java.net.URL

@AutoService(KomaTheme::class)
class BootstrapKomaTheme : KomaTheme {
  override fun name(): String = "bootstrap"

  override fun template(): TemplateEngineProvider = TemplateEngineProvider.THYMELEAF

  override fun variables(): Map<String, Any> {
    TODO("Not yet implemented")
  }

  override fun listStaticResources(): List<URL> {
    TODO("Not yet implemented")
  }
}
