package org.koma.cli.command

import com.github.ajalt.clikt.core.Context
import com.google.common.base.Stopwatch
import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.TimeUnit
import me.tongfei.progressbar.ProgressBar
import org.apache.commons.io.FileUtils
import org.fusesource.jansi.Ansi.ansi
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koma.cli.filter.ConfigFileFilter
import org.koma.cli.service.ConfigParser
import org.koma.compiler.compiler.KomaCompiler
import org.koma.compiler.config.KomaConfig
import java.util.concurrent.ExecutorService

@Single
class GenerateCommand : BaseCommand(), KoinComponent {
  private val log = KotlinLogging.logger {}
  private val configParser: ConfigParser by inject()
  private val cliExecutor: ExecutorService by inject()

  private val stopwatch = Stopwatch.createStarted()

  private val compiler by lazy { KomaCompiler(executor = cliExecutor) }

  override fun run() {
    ProgressBar("Test", 100).use {
      it.setExtraMessage("Generating...")
      parseConfigFile()?.let { config ->
        log.atDebug { message = "config file:${config}" }
        compiler.compile(config, folder.toPath())
      }
      println(
        ansi()
          .bold()
          .fgBrightDefault()
          .render("Compile:${stopwatch.elapsed(TimeUnit.SECONDS)}s")
          .reset(),
      )
      cliExecutor.shutdown()
      if (!cliExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
        log.atWarn { message = "Some tasks did not finish within the timeout period." }
        cliExecutor.shutdownNow()
      }
      stopwatch.stop()
      it.stepBy(stopwatch.elapsed(TimeUnit.MICROSECONDS))
    }
  }

  override fun aliases(): Map<String, List<String>> = mapOf("g" to listOf("generate"))

  override fun help(context: Context): String = "generate,g [folder] Generate site"

  private fun parseConfigFile(): KomaConfig? {
    val files = FileUtils.listFiles(folder, ConfigFileFilter(), null)
    return files.firstNotNullOfOrNull {
      configParser.parse(it.inputStream(), it.extension)
    }
  }
}
