package org.koma.cli.command

import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent

@Single
class DeployCommand : BaseCommand(), KoinComponent {
  override fun run() {
    this.echo("deploy")
  }
}
