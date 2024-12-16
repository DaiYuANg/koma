package org.koma.core.module

import org.apache.maven.cli.CliRequest
import org.apache.maven.cli.MavenCli


class ModuleLoader {

  init {
    val cli = MavenCli()
    cli.doMain(arrayOf("install"), ".", System.out, System.err)
  }
}