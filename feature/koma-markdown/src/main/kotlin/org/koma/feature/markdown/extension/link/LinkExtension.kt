package org.koma.feature.markdown.extension.link

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.util.data.MutableDataHolder

class LinkExtension : HtmlRenderer.HtmlRendererExtension {
  override fun rendererOptions(p0: MutableDataHolder) {}

  override fun extend(
      builder: HtmlRenderer.Builder,
      p1: String,
  ) {
    builder.nodeRendererFactory(LinkNodeRenderFactory())
  }

  companion object {
    @JvmStatic fun create(): LinkExtension = LinkExtension()
  }
}
