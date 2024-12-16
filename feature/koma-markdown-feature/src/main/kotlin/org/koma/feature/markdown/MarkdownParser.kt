package org.koma.feature.markdown

import com.google.auto.service.AutoService
import com.vladsch.flexmark.ast.Text
import com.vladsch.flexmark.ext.anchorlink.AnchorLinkExtension
import com.vladsch.flexmark.ext.aside.AsideExtension
import com.vladsch.flexmark.ext.autolink.AutolinkExtension
import com.vladsch.flexmark.ext.emoji.EmojiExtension
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension
import com.vladsch.flexmark.ext.tables.TablesExtension
import com.vladsch.flexmark.ext.toc.TocExtension
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterExtension
import com.vladsch.flexmark.ext.youtube.embedded.YouTubeLinkExtension
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.ast.Node
import com.vladsch.flexmark.util.ast.NodeVisitor
import com.vladsch.flexmark.util.ast.VisitHandler
import com.vladsch.flexmark.util.data.DataSet
import com.vladsch.flexmark.util.data.MutableDataSet
import io.github.oshai.kotlinlogging.KotlinLogging
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.koma.api.SourceParser


@AutoService(SourceParser::class)
class MarkdownParser : SourceParser {

  private val log = KotlinLogging.logger {}
  private var options: DataSet = MutableDataSet()
    .set(
      Parser.EXTENSIONS, listOf(
        TablesExtension.create(),
        StrikethroughExtension.create(),
        EmojiExtension.create(),
        AutolinkExtension.create(),
        AnchorLinkExtension.create(),
        AsideExtension.create(),
        TocExtension.create(),
        YamlFrontMatterExtension.create(),
        YouTubeLinkExtension.create()
      )
    )
    .set(Parser.HTML_BLOCK_DEEP_PARSER, true)
    .set(Parser.HTML_BLOCK_PARSER, true)
    .toImmutable()

  private var parser = Parser.builder(options)
    .build()
  private var renderer: HtmlRenderer = HtmlRenderer.builder(options)
    .build()
  private val supportExtension = setOf("markdown", "md")
  private var globalVisitor: NodeVisitor = NodeVisitor(
    VisitHandler(Text::class.java, GlobalVisitor())
  )

  override fun parseable(extension: String): Boolean {
    log.info { "Parsing $extension ${supportExtension.contains(extension)}" }
    return supportExtension.contains(extension)
  }

  override fun parse(source: String): Document {
    val markdownNodes: Node = parser.parse(source)
//    globalVisitor.visit(markdownNodes)
    val htmlRenderer = renderer.render(markdownNodes)
    return Jsoup.parse(htmlRenderer, "UTF-8")
  }
}