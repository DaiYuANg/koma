package org.koma.cli.commands

import com.github.ajalt.clikt.core.CliktCommand
import io.github.oshai.kotlinlogging.KotlinLogging
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koma.cli.processor.InitProcessor

@Single
class InitCommand : CliktCommand(), KoinComponent {
  private val log = KotlinLogging.logger {}
  private val initProcessor: InitProcessor by inject()
  override fun run() {
    log.atInfo { message="init" }
    initProcessor.process()
  }
}