package org.koma.cli.filter

import org.apache.commons.io.filefilter.IOFileFilter
import org.koma.compiler.config.KOMA_CONFIG_NAME
import java.io.File

class ConfigFileFilter:IOFileFilter {
  override fun accept(file: File): Boolean {
    return file.name.startsWith(KOMA_CONFIG_NAME,true)
  }

  override fun accept(dir: File, name: String): Boolean {
    return false
  }
}