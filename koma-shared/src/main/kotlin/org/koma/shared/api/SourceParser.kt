package org.koma.shared.api

import org.jsoup.nodes.Document
import org.koma.shared.context.SourceParseContext
import org.koma.shared.data.structure.KomaDocument

interface SourceParser {
  fun parseable(context: SourceParseContext): Boolean

  fun parse(context: SourceParseContext): KomaDocument
}