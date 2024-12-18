package org.koma.compiler.model

import org.koma.compiler.config.FILE_NAME
import java.nio.file.Path

data class KomaLayout(private val _basePath: Path) {
  val path: Path
    get() = _basePath

  val content: Path
  get() = _basePath.resolve("content")

  fun config(): Path {
    return _basePath.resolve(FILE_NAME)
  }

  fun assets(): Path {
    return _basePath.resolve("assets")
  }

  fun templates(): Path {
    return _basePath.resolve("templates")
  }

  fun dist(): Path {
    return _basePath.resolve("dist")
  }
}