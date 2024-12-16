package org.koma.cli.commands

import com.github.ajalt.clikt.core.CliktCommand
import io.javalin.Javalin
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent

@Single
class ServerCommand : CliktCommand(), KoinComponent {
  override fun run() {
    Javalin.create { config ->
      config.showJavalinBanner = false
      config.useVirtualThreads = true
    }.start(9999)
  }
}