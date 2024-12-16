package org.koma.core.context

import io.smallrye.mutiny.Uni
import org.jsoup.nodes.Document
import org.koma.api.SourceParser

data class CompileContext(
  val sourceParser: Set<SourceParser>
) {
  val htmlContext: MutableMap<String, Document> = mutableMapOf()

  val process = mutableListOf<Uni<Void>>()
}