package org.koma.cli.command

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.defaultLazy
import com.github.ajalt.clikt.parameters.arguments.help
import com.github.ajalt.clikt.parameters.types.file
import com.google.common.base.Stopwatch
import io.github.oshai.kotlinlogging.KotlinLogging
import me.tongfei.progressbar.ProgressBar
import org.apache.commons.io.FileUtils
import org.fusesource.jansi.Ansi
import org.fusesource.jansi.Ansi.ansi
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koma.core.compiler.KomaCompiler
import org.koma.core.config.KomaConfig
import org.koma.core.model.KomaLayout
import java.io.File
import java.util.concurrent.TimeUnit

@Single
class GenerateCommand : BaseCommand(), KoinComponent {

  private val log = KotlinLogging.logger {}
  private val objectMapper: ObjectMapper by inject()

  private val komaLayout: KomaLayout by inject()

  private val stopwatch = Stopwatch.createStarted()

  override fun run() {
    println(folder)
    val configFile = komaLayout.config().toFile();
    if (!configFile.exists()) {
      log.atError { message = "Config file does not exist: $configFile" };
    }
    ProgressBar("Test", 100).use {
      it.setExtraMessage("Generating...");
      FileUtils.openInputStream(configFile).use {
        val komaConfig = objectMapper.readValue(it, KomaConfig::class.java)
        KomaCompiler().compile(komaConfig, komaLayout)
        println(
          ansi()
            .bold()
            .fgBrightDefault()
            .render("Compile:${stopwatch.elapsed(TimeUnit.SECONDS)}s").reset()
        )
        stopwatch.stop()
      }
      it.stepBy(stopwatch.elapsed(TimeUnit.MICROSECONDS))
    }
  }

  override fun aliases(): Map<String, List<String>> {
    return mapOf("g" to listOf("generate"))
  }

  override fun help(context: Context): String {
    return "generate,g [folder] Generate site"
  }
}