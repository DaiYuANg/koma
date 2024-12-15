package org.koma.cli.provider

import jakarta.inject.Inject
import jakarta.inject.Provider
import lombok.RequiredArgsConstructor
import org.koma.cli.command.RootCommand
import org.koma.cli.factory.PicocliFactory
import picocli.CommandLine

class CommandLineProvider @Inject constructor(
  private val command: RootCommand,
  private val picocliFactory: PicocliFactory
) : Provider<CommandLine> {


  override fun get(): CommandLine {
    return CommandLine(command, picocliFactory)
  }
}
