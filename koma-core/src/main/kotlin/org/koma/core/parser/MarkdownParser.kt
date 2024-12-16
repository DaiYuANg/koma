package org.koma.core.parser

import com.vladsch.flexmark.ext.tables.TablesExtension
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.ast.KeepType
import com.vladsch.flexmark.util.data.MutableDataSet
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.koma.api.SourceParser

class MarkdownParser : SourceParser {

  private val options = MutableDataSet()
    .set(Parser.REFERENCES_KEEP, KeepType.LAST)
    .set(HtmlRenderer.INDENT_SIZE, 2)
    .set(HtmlRenderer.PERCENT_ENCODE_URLS, true)

    .set(TablesExtension.COLUMN_SPANS, false)
    .set(TablesExtension.APPEND_MISSING_COLUMNS, true)
    .set(TablesExtension.DISCARD_EXTRA_COLUMNS, true)
    .set(TablesExtension.HEADER_SEPARATOR_COLUMN_MATCH, true)
    .set(Parser.EXTENSIONS, listOf(TablesExtension.create()))
    .toImmutable();
  private val parser = Parser.builder(options)
    .build()
  private val renderer = HtmlRenderer.builder(options).build()

  override fun parse(source: String): Document {
    val markdownDocument = parser.parse(source)
    val html = renderer.render(markdownDocument)
    return Jsoup.parseBodyFragment(html)
  }
}