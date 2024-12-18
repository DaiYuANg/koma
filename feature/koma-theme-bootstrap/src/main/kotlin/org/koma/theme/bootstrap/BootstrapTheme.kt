package org.koma.theme.bootstrap

import com.google.auto.service.AutoService
import com.helger.css.ECSSVersion
import com.helger.css.decl.CascadingStyleSheet
import com.helger.css.reader.CSSReader
import com.helger.css.reader.CSSReaderSettings
import org.apache.commons.io.IOUtils
import org.koma.shared.api.Theme
import org.koma.shared.data.TemplateEngine
import org.webjars.WebJarAssetLocator
import java.nio.charset.StandardCharsets

@AutoService(Theme::class)
class BootstrapTheme : Theme {
  override fun name(): String {
    return "bootstrap"
  }

  override fun template(): TemplateEngine {
    return TemplateEngine.THYMELEAF
  }

  override fun variables(): Map<String, Any> {
    TODO("Not yet implemented")
  }
}