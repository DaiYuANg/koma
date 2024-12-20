package org.koma.shared.api

import org.koma.shared.data.structure.KomaDocument

interface SourceParser {
  //  fun parseable(context: SourceParseContext): Boolean

  fun parse(source: String): KomaDocument
}
