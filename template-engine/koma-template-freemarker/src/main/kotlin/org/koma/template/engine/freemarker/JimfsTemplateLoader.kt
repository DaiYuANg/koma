package org.koma.template.engine.freemarker

import freemarker.cache.TemplateLoader
import java.io.Reader
import java.io.StringReader
import java.nio.file.FileSystem
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.ConcurrentHashMap

class JimfsTemplateLoader(fileSystem: FileSystem) : TemplateLoader {
  private val templateDir: Path = fileSystem.getPath("/templates")
  private val templateCache = ConcurrentHashMap<String, Path>()

  override fun findTemplateSource(name: String): Any? {
    val templatePath = templateDir.resolve("$name.ftl")
    return if (Files.exists(templatePath)) {
      templateCache[name] = templatePath
      templatePath
    } else {
      null
    }
  }

  override fun getLastModified(templateSource: Any?): Long {
    val path = templateSource as Path
    return Files.getLastModifiedTime(path).toMillis()
  }

  override fun getReader(templateSource: Any?, p1: String?): Reader {
    val path = templateSource as Path
    val content = Files.readString(path)
    return StringReader(content)
  }

  override fun closeTemplateSource(p0: Any?) {
  }
}