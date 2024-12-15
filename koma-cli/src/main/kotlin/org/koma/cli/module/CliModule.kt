package org.koma.cli.module

import com.google.inject.AbstractModule
import io.github.cdimascio.dotenv.Dotenv
import org.koma.cli.factory.PicocliFactory
import org.koma.cli.provider.CommandLineProvider
import org.koma.cli.provider.DotenvProvider
import org.koma.cli.provider.LayoutProvider
import org.koma.core.layout.KomaLayout
import picocli.CommandLine

class CliModule : AbstractModule() {

  override fun configure() {
    bind(KomaLayout::class.java).toProvider(LayoutProvider::class.java)
    bind(PicocliFactory::class.java).toInstance(PicocliFactory())
    bind(CommandLine::class.java).toProvider(CommandLineProvider::class.java)
    bind(Dotenv::class.java).toProvider(DotenvProvider::class.java)
  }
}