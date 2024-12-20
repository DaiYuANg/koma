package org.koma.feature.markdown.extension.link

import com.vladsch.flexmark.html.renderer.DelegatingNodeRendererFactory
import com.vladsch.flexmark.html.renderer.NodeRenderer
import com.vladsch.flexmark.util.data.DataHolder

class LinkNodeRenderFactory : DelegatingNodeRendererFactory {
  override fun apply(p0: DataHolder): NodeRenderer = LinkRender(p0)

  override fun getDelegates(): MutableSet<Class<*>> =
      mutableSetOf(LinkNodeRenderFactory::class.java)
}
