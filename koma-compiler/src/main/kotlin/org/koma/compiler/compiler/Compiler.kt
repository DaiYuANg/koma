package org.koma.compiler.compiler

import org.koma.compiler.config.KomaConfig
import java.nio.file.Path

interface Compiler {
  fun compile(config: KomaConfig, context: Path)
}