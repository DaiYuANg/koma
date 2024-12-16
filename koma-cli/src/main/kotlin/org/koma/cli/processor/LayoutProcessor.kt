package org.koma.cli.processor

import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koma.cli.util.checkDirExistsOrCreate
import org.koma.core.model.KomaLayout

@Single
class LayoutProcessor(
  private val komaLayout: KomaLayout
) : KoinComponent, InitProcessor {
  override fun process() {
    val processDir = listOf(komaLayout.content(), komaLayout.templates(), komaLayout.assets());
    processDir.forEach {
      checkDirExistsOrCreate(it)
    }
  }
}
