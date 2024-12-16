package org.koma.cli

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import com.google.common.util.concurrent.AbstractIdleService
import com.google.common.util.concurrent.Service
import com.google.common.util.concurrent.ServiceManager
import io.github.cdimascio.dotenv.Dotenv
import org.fusesource.jansi.AnsiConsole.systemInstall
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.context.startKoin
import org.koin.ksp.generated.*
import org.koma.cli.commands.*
import org.koma.core.model.KomaLayout
import kotlin.io.path.Path

@Module
@ComponentScan
class CliModule {

  @Single
  fun komaLayout(): KomaLayout {
    return KomaLayout(Path(""))
  }

  @Single
  fun objectMapper(): ObjectMapper {
    val mapper = ObjectMapper(YAMLFactory())
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper
  }

  @Single
  fun serviceManager(services: List<AbstractIdleService>): ServiceManager {
    return ServiceManager(services)
  }

  @Single
  fun dotenv(): Dotenv {
    return Dotenv.configure().ignoreIfMissing().ignoreIfMalformed().systemProperties().load()
  }
}

fun main(args: Array<String>) {
  val container = startKoin {
    printLogger()
    modules(
      CliModule().module
    )
  }
  val koin = container.koin

  val serviceManager = koin.get<ServiceManager>()
  serviceManager.startAsync()
  val root = koin.get<RootCommand>()
  systemInstall()

  root.main(args)

  serviceManager.stopAsync()
}