package org.koma.shared

import org.jsoup.nodes.Document

interface SourceParser {
  fun parseable(context: SourceParseContext): Boolean

  fun parse(context: SourceParseContext): Document
}