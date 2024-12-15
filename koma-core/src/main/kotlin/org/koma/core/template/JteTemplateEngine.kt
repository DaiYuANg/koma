package org.koma.core.template

import gg.jte.CodeResolver
import gg.jte.ContentType
import gg.jte.TemplateEngine
import gg.jte.resolve.DirectoryCodeResolver
import java.nio.file.Path

class JteTemplateEngine : Engine {
  override fun parse(): String {
    val codeResolver: CodeResolver =
      DirectoryCodeResolver(Path.of("jte")) // This is the directory where your .jte files are located.
    val templateEngine = TemplateEngine.create(codeResolver, ContentType.Html)
    return ""
  }
}
