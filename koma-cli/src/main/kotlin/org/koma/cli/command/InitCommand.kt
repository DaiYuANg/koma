package org.koma.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.*
import com.github.ajalt.clikt.parameters.types.boolean
import com.github.ajalt.clikt.parameters.types.file
import io.github.oshai.kotlinlogging.KotlinLogging
import org.eclipse.jgit.api.Git
import org.fusesource.jansi.Ansi
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koma.cli.processor.InitProcessor
import java.io.File

@Single
class InitCommand : BaseCommand(), KoinComponent {
  private val log = KotlinLogging.logger {}
  private val enableRepository: Boolean by option().boolean().default(false).help("Enable Git repository")
  private val initProcessor: InitProcessor by inject()
  override fun run() {
    if (enableRepository) {
      Git.init()
    }
    echo(Ansi.ansi().fgBrightCyan().bold().render("init").reset())
    if (folder.exists()) {
      log.atInfo { "Folder $folder already exists" }
    }
    log.atInfo { message = "init" }
    initProcessor.process()
  }
}