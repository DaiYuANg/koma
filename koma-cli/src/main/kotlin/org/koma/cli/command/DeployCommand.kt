package org.koma.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent

@Single
class DeployCommand: CliktCommand(),KoinComponent {
  override fun run() {
    this.echo("deploy")
  }
}