package org.koma.compiler.model

import java.nio.file.Path
import org.koma.compiler.config.FILE_NAME

data class KomaLayout(
    private val _basePath: Path,
) {
  val path: Path
    get() = _basePath

  val content: Path
    get() = _basePath.resolve("content")

  fun config(): Path = _basePath.resolve(FILE_NAME)

  fun assets(): Path = _basePath.resolve("assets")

  fun templates(): Path = _basePath.resolve("templates")

  fun dist(): Path = _basePath.resolve("dist")
}
