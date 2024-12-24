package org.koma.shared.data.structure

import org.apache.commons.codec.digest.DigestUtils
import org.jsoup.nodes.Document

data class KomaDocument(
  val htmlDocument: Document,
  val metadata: DocumentMetadata,
) {
  private fun documentHash(): String {
    return DigestUtils.sha1Hex(htmlDocument.html())
  }

  fun generateFilename(): String {
    return "${metadata.title}-${documentHash()}.html"
  }
}
