package org.koma.compiler.context

import io.smallrye.mutiny.Uni
import org.jsoup.nodes.Document
import org.koma.shared.api.SourceParseableDetector

data class CompileContext(
    val sourceParseableDetectors: Set<SourceParseableDetector>,
) {
  val htmlContext: MutableMap<String, Document> = mutableMapOf()

  val process = mutableListOf<Uni<Void>>()
}
