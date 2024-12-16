package org.koma.cli.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.boolean
import io.javalin.Javalin
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent

@Single
class ServerCommand : CliktCommand(), KoinComponent {

  private val watch: Boolean by option().boolean().default(false)

  override fun run() {
    Javalin.create { config ->
      config.showJavalinBanner = false
      config.useVirtualThreads = true
    }.start(9999)
  }
}