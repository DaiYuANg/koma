package org.koma.cli.commands

import com.github.ajalt.clikt.completion.completionOption
import com.github.ajalt.clikt.core.NoOpCliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.versionOption
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Properties

@Single
class RootCommand : NoOpCliktCommand(), KoinComponent {

  private val generateCommand: GenerateCommand by inject()
  private val watchCommand: WatchCommand by inject()
  private val serverCommand: ServerCommand by inject()
  private val initCommand: InitCommand by inject()
  private val deployCommand: DeployCommand by inject()

  init {
    javaClass
      .getClassLoader()
      .getResourceAsStream("META-INF/MANIFEST.MF")?.apply {
        val properties = Properties()
        properties.load(this)
        val version = properties.getValue("Implementation-Version")
        println("Version: $version")
        versionOption(version.toString())
      }
  }

  init {
    subcommands(
      generateCommand,
      initCommand,
      serverCommand,
      watchCommand,
      deployCommand
    )
  }

  init {
    completionOption()
  }

  override fun aliases(): Map<String, List<String>> {
    return mapOf(
      "g" to listOf("generate"),
      "i" to listOf("init")
    )
  }
}