package org.koma.shared.api

import org.koma.shared.data.TemplateEngineProvider
import java.net.URL

interface KomaTheme {
  fun name(): String

  fun template(): TemplateEngineProvider

  fun variables(): Map<String, Any>

  fun listStaticResources(): List<URL>
}
