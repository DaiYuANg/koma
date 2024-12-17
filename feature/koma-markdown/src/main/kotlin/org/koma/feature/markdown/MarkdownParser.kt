package org.koma.feature.markdown

import com.google.auto.service.AutoService
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterNode
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.ast.Node
import com.vladsch.flexmark.util.ast.NodeVisitor
import com.vladsch.flexmark.util.ast.VisitHandler
import com.vladsch.flexmark.util.data.DataSet
import io.github.oshai.kotlinlogging.KotlinLogging
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.koma.api.SourceParser
import org.koma.feature.markdown.factory.create
import org.koma.feature.markdown.visitor.YamlFrontMatterVisitor


@AutoService(SourceParser::class)
class MarkdownParser : SourceParser {

  private val log = KotlinLogging.logger {}
  private var options: DataSet = create()

  private var parser = Parser.builder(options)
    .build()
  private var renderer: HtmlRenderer = HtmlRenderer.builder(options)
    .build()
  private val supportExtension = setOf("markdown", "md")
  private var globalVisitor: NodeVisitor = NodeVisitor(
    VisitHandler(YamlFrontMatterNode::class.java, YamlFrontMatterVisitor())
  )

  override fun parseable(extension: String): Boolean {
    log.info { "Parsing $extension ${supportExtension.contains(extension)}" }
    return supportExtension.contains(extension)
  }

  override fun parse(source: String): Document {
    val markdownNodes: Node = parser.parse(source)
    globalVisitor.visit(markdownNodes)
    val htmlRenderer = renderer.render(markdownNodes)
    return Jsoup.parseBodyFragment(htmlRenderer)
  }
}