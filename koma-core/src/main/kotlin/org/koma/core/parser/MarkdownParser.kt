package org.koma.core.parser

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.MutableDataSet
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.koma.core.api.SourceParser

class MarkdownParser : SourceParser {

  private val options = MutableDataSet()
  private val parser = Parser.builder(options)
    .build()
  private val renderer = HtmlRenderer.builder(options).build()

  override fun parse(source: String): Document {
    val markdownDocument = parser.parse(source)
    val html = renderer.render(markdownDocument)
    return Jsoup.parse(html)
  }
}