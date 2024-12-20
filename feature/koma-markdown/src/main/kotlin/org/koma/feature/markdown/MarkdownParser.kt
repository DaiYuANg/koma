package org.koma.feature.markdown

import com.google.auto.service.AutoService
import com.vladsch.flexmark.ast.Link
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterNode
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.ast.Node
import com.vladsch.flexmark.util.ast.NodeVisitor
import com.vladsch.flexmark.util.ast.VisitHandler
import com.vladsch.flexmark.util.data.DataSet
import org.jsoup.Jsoup
import org.koma.feature.markdown.context.ParseContext
import org.koma.feature.markdown.factory.create
import org.koma.feature.markdown.visitor.LinkRefVisitor
import org.koma.feature.markdown.visitor.YamlFrontMatterVisitor
import org.koma.shared.api.SourceParser
import org.koma.shared.data.structure.DocumentMetadata
import org.koma.shared.data.structure.KomaDocument

@AutoService(SourceParser::class)
class MarkdownParser : SourceParser {
  private var options: DataSet = create()

  private var parser = Parser.builder(options).build()
  private var renderer: HtmlRenderer = HtmlRenderer.builder(options).build()

  private val parseContext = ParseContext()

  private var globalVisitor: NodeVisitor =
      NodeVisitor(
          VisitHandler(YamlFrontMatterNode::class.java, YamlFrontMatterVisitor(parseContext)),
          VisitHandler(Link::class.java, LinkRefVisitor(parseContext)),
      )

  override fun parse(source: String): KomaDocument {
    val markdownNodes: Node = parser.parse(source)
    globalVisitor.visit(markdownNodes)
    val htmlRenderer = renderer.render(markdownNodes)
    return KomaDocument(
        htmlDocument = Jsoup.parseBodyFragment(htmlRenderer),
        metadata =
            DocumentMetadata(
                title = parseContext.title,
                author = parseContext.author,
            ),
    )
  }
}
