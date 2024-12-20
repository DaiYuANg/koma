package org.koma.cli.command

import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.boolean
import io.github.oshai.kotlinlogging.KotlinLogging
import org.eclipse.jgit.api.Git
import org.fusesource.jansi.Ansi
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koma.cli.util.checkDirExistsOrCreate
import org.koma.compiler.model.KomaLayout

@Single
class InitCommand : BaseCommand(), KoinComponent {
  private val log = KotlinLogging.logger {}
  private val enableRepository: Boolean by
      option().boolean().default(false).help("Enable Git repository")
  private val komaLayout: KomaLayout by inject()

  override fun run() {
    if (enableRepository) {
      Git.init()
    }
    echo(
        Ansi.ansi().fgBrightCyan().bold().render("init").reset(),
    )
    if (folder.exists()) {
      log.atInfo { "Folder $folder already exists" }
    }
    log.atInfo { message = "init" }
    generateDir()
  }

  private fun generateDir() {
    val processDir = listOf(komaLayout.content, komaLayout.templates(), komaLayout.assets())
    processDir.forEach { checkDirExistsOrCreate(it) }
  }
}
