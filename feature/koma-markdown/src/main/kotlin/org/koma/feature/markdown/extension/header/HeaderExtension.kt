package org.koma.feature.markdown.extension.header

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.util.data.MutableDataHolder

class HeaderExtension : HtmlRenderer.HtmlRendererExtension {
  override fun rendererOptions(options: MutableDataHolder) {
  }

  override fun extend(builder: HtmlRenderer.Builder, rendererType: String) {
    builder.nodeRendererFactory(HeaderNodeRendererFactory())
  }

  companion object {
    @JvmStatic
    fun create(): HeaderExtension {
      return HeaderExtension()
    }
  }
}