package org.koma.cli.provider

import io.github.cdimascio.dotenv.Dotenv
import jakarta.inject.Provider

class DotenvProvider : Provider<Dotenv> {

  override fun get(): Dotenv {
    return Dotenv.configure().ignoreIfMalformed().ignoreIfMissing().load()
  }
}
