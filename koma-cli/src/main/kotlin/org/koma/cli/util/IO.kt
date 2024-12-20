package org.koma.cli.util

import io.github.oshai.kotlinlogging.KotlinLogging
import java.nio.file.Path

val log = KotlinLogging.logger {}

fun checkDirExistsOrCreate(dir: Path) {
  val dirFile = dir.toAbsolutePath().toFile()
  if (dirFile.exists()) return
  if (dirFile.mkdir()) {
    log.info { "created ${dir.toAbsolutePath()}" }
  }
}
