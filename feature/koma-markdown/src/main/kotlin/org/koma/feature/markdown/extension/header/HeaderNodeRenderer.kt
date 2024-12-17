package org.koma.feature.markdown.extension.header

import com.vladsch.flexmark.ast.Heading
import com.vladsch.flexmark.ext.anchorlink.AnchorLink
import com.vladsch.flexmark.html.HtmlWriter
import com.vladsch.flexmark.html.renderer.NodeRenderer
import com.vladsch.flexmark.html.renderer.NodeRendererContext
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler
import com.vladsch.flexmark.parser.ParserEmulationProfile
import com.vladsch.flexmark.profile.pegdown.Extensions
import com.vladsch.flexmark.util.data.DataHolder


class HeaderNodeRenderer(
  private val options: DataHolder
) : NodeRenderer {
  override fun getNodeRenderingHandlers(): MutableSet<NodeRenderingHandler<*>> {
    return mutableSetOf(
      NodeRenderingHandler(AnchorLink::class.java, this::render),
      NodeRenderingHandler(Heading::class.java, this::render)
    )
  }

  fun render(node: AnchorLink, context: NodeRendererContext, html: HtmlWriter?) {
    val parent = node.parent

    if (parent is Heading && parent.level == 1) {
      context.renderChildren(node)
    } else {
      context.delegateRender()
    }
  }

  fun render(node: Heading, context: NodeRendererContext, html: HtmlWriter) {
    if (node.level == 1) {
      // render without anchor link
      val extensions = ParserEmulationProfile.PEGDOWN_EXTENSIONS[context.options]
      if (context.htmlOptions.renderHeaderId || haveExtension(extensions, Extensions.ANCHORLINKS) || haveAllExtensions(
          extensions,
          Extensions.EXTANCHORLINKS or Extensions.EXTANCHORLINKS_WRAP
        )
      ) {
        val id = context.getNodeId(node)
        if (id != null) {
          html.attr("id", id)
        }
      }

      if (context.htmlOptions.sourcePositionParagraphLines) {
        html.srcPos(node.chars).withAttr().tagLine("h" + node.level) {
          html.srcPos(node.text).withAttr().tag("span")
          context.renderChildren(node)
          html.tag("/span")
        }
      } else {
        html.srcPos(node.text).withAttr().tagLine("h" + node.level) { context.renderChildren(node) }
      }
    } else {
      context.delegateRender()
    }
  }

  private fun haveExtension(extensions: Int, flags: Int): Boolean {
    return (extensions and flags) != 0
  }

  private fun haveAllExtensions(extensions: Int, flags: Int): Boolean {
    return (extensions and flags) == flags
  }
}