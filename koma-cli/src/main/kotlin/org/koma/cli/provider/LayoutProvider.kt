package org.koma.cli.provider

import jakarta.inject.Provider
import org.koma.core.model.KomaLayout
import kotlin.io.path.Path

class LayoutProvider : Provider<KomaLayout> {
  override fun get(): KomaLayout {
    return KomaLayout(Path(""))
  }
}