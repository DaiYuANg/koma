package org.koma.shared.context

import java.nio.file.Path

data class SourceParseContext(
    val file: Path,
    val extension: String,
)
