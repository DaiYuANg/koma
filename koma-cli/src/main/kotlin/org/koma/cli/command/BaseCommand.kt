package org.koma.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.defaultLazy
import com.github.ajalt.clikt.parameters.arguments.help
import com.github.ajalt.clikt.parameters.types.file
import java.io.File

abstract class BaseCommand: CliktCommand() {
  protected val folder by argument()
    .file(mustExist = true, canBeDir = true, mustBeReadable = true, mustBeWritable = true)
    .help("Generate folder")
    .defaultLazy {
      File("")
    }
}