package org.koma.cli

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.toml.TomlFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.ajalt.clikt.core.main
import com.google.common.util.concurrent.AbstractIdleService
import com.google.common.util.concurrent.ServiceManager
import io.github.cdimascio.dotenv.Dotenv
import kotlin.io.path.Path
import org.fusesource.jansi.AnsiConsole.systemInstall
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Qualifier
import org.koin.core.annotation.Single
import org.koin.core.context.startKoin
import org.koin.ksp.generated.*
import org.koma.cli.command.*
import org.koma.compiler.model.KomaLayout
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Module
@ComponentScan
class CliModule {

  @Single
  fun executor(): ExecutorService {
    return Executors.newThreadPerTaskExecutor(
      Thread.ofPlatform()
        .name("koma-cli-", 0)
        .factory()
    )
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
    modules(CliModule().module)
  }
  val koin = container.koin

  val serviceManager = koin.get<ServiceManager>()
  serviceManager.startAsync()
  val root = koin.get<RootCommand>()
  systemInstall()

  root.main(args)

  serviceManager.stopAsync()
}
