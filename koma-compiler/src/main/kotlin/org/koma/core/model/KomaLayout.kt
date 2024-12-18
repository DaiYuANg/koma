package org.koma.core.model

import org.jetbrains.annotations.Contract
import org.koma.core.config.FILE_NAME
import java.nio.file.Path

data class KomaLayout(private val _basePath: Path) {
  val path: Path
    get() = _basePath

  fun config(): Path {
    return _basePath.resolve(FILE_NAME)
  }

  fun content(): Path {
    return _basePath.resolve("content")
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