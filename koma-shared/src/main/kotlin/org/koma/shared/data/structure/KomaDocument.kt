package org.koma.shared.data.structure

import org.jsoup.nodes.Document

data class KomaDocument(
  val htmlDocument: Document,
  val metadata: DocumentMetadata
) {

}