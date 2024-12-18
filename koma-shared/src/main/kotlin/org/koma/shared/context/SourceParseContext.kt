package org.koma.shared.context

import java.nio.file.Path

data class  SourceParseContext(
  val file:Path,
val source: String,
  val extension: String,
)
