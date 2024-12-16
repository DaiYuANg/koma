package org.koma.feature.markdown

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.koma.api.SourceParser

class MarkdownParser : SourceParser {
  override fun parse(source: String): Document {
    return Jsoup.parse(source, "UTF-8")
  }
}