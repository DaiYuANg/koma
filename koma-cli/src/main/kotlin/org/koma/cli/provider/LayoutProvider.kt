package org.koma.cli.provider

import jakarta.inject.Provider
import org.koma.core.layout.KomaLayout
import kotlin.io.path.Path

class LayoutProvider : Provider<KomaLayout> {
  override fun get(): KomaLayout {
    return KomaLayout.builder().basePath(Path("")).build()
  }
}