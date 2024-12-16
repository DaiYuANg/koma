package org.koma.core.context

import org.jsoup.nodes.Document

data class ParseContext(
  private val internal: MutableMap<String, Document> = mutableMapOf()
) {
  fun put(key: String, document: Document) {
    internal[key] = document
  }
}