package org.koma.feature.markdown.extension.header

import com.vladsch.flexmark.ext.anchorlink.internal.AnchorLinkNodeRenderer
import com.vladsch.flexmark.html.renderer.DelegatingNodeRendererFactory
import com.vladsch.flexmark.html.renderer.NodeRenderer
import com.vladsch.flexmark.util.data.DataHolder


class HeaderNodeRendererFactory : DelegatingNodeRendererFactory {
  override fun apply(p0: DataHolder): NodeRenderer {
    return HeaderNodeRenderer(p0);
  }

  override fun getDelegates(): MutableSet<Class<*>> {
    return mutableSetOf(AnchorLinkNodeRenderer.Factory::class.java)
  }
}