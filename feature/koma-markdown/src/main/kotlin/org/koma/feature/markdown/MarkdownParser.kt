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
import io.github.oshai.kotlinlogging.KotlinLogging
import org.apache.tika.Tika
import org.jsoup.Jsoup
import org.koma.feature.markdown.context.ParseContext
import org.koma.shared.context.SourceParseContext
import org.koma.shared.api.SourceParser
import org.koma.feature.markdown.factory.create
import org.koma.feature.markdown.visitor.LinkRefVisitor
import org.koma.feature.markdown.visitor.YamlFrontMatterVisitor
import org.koma.shared.data.structure.DocumentMetadata
import org.koma.shared.data.structure.KomaDocument


@AutoService(SourceParser::class)
class MarkdownParser : SourceParser {

  private val log = KotlinLogging.logger {}
  private var options: DataSet = create()

  private var parser = Parser.builder(options)
    .build()
  private var renderer: HtmlRenderer = HtmlRenderer.builder(options)
    .build()
  private val supportExtension = setOf("markdown", "md")

  private val parseContext = ParseContext()

  private val tika = Tika()

  private var globalVisitor: NodeVisitor = NodeVisitor(
    VisitHandler(YamlFrontMatterNode::class.java, YamlFrontMatterVisitor(parseContext)),
    VisitHandler(Link::class.java, LinkRefVisitor(parseContext)),
  )

  override fun parseable(context: SourceParseContext): Boolean {
    log.info { "Parsing ${context.extension} ${supportExtension.contains(context.extension)}" }
    val r = tika.detect(context.file)
    log.debug { "MimeType $r" }
    return supportExtension.contains(context.extension)
  }

  override fun parse(context: SourceParseContext): KomaDocument {
    val markdownNodes: Node = parser.parse(context.source)
    globalVisitor.visit(markdownNodes)
    val htmlRenderer = renderer.render(markdownNodes)
    return KomaDocument(
      htmlDocument = Jsoup.parseBodyFragment(htmlRenderer),
      metadata = DocumentMetadata(
        title = parseContext.title,
        author = parseContext.author,
      ),
    )
  }
}