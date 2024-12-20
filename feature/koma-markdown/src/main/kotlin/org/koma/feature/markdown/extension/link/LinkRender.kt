package org.koma.feature.markdown.extension.link

import com.vladsch.flexmark.ast.Link
import com.vladsch.flexmark.html.HtmlWriter
import com.vladsch.flexmark.html.renderer.NodeRenderer
import com.vladsch.flexmark.html.renderer.NodeRendererContext
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler
import com.vladsch.flexmark.util.data.DataHolder

class LinkRender(
    private val options: DataHolder,
) : NodeRenderer {
  override fun getNodeRenderingHandlers(): MutableSet<NodeRenderingHandler<*>> =
      mutableSetOf(
          NodeRenderingHandler(Link::class.java, this::render),
      )

  fun render(
      node: Link,
      context: NodeRendererContext,
      html: HtmlWriter,
  ) {
    //    val parent = node.parent
    //    context.delegateRender()
    //    if (parent is Heading && parent.level == 1) {
    //      context.renderChildren(node)
    //    } else {
    //      context.delegateRender()
    //    }
  }
}
