package org.koma.core.vistor

import io.github.oshai.kotlinlogging.KotlinLogging
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.koma.core.context.CompileContext
import org.koma.shared.SourceParseContext
import java.io.IOException
import java.nio.file.FileVisitResult
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes
import kotlin.io.path.name


class CompileSourceVisitor(
  private val compileContext: CompileContext
) : SimpleFileVisitor<Path>() {
  private val log = KotlinLogging.logger {}

  override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
    log.info { "Visiting file ${file.name}" }
    val filename = FilenameUtils.getName(file.name)
    val extension = FilenameUtils.getExtension(file.name)
    val fileContent = FileUtils.readFileToString(file.toFile(), Charsets.UTF_8)
    val context = SourceParseContext(
      file = file,
      source = fileContent,
      extension = extension
    )

    compileContext.sourceParser
      .find { it.parseable(context) }?.apply {

        log.info { "SourceParser $this" }
        val result = this.parse(context)
        compileContext.htmlContext[filename] = result
      }
    return FileVisitResult.CONTINUE
  }

  override fun visitFileFailed(file: Path, exc: IOException): FileVisitResult {
    log.info { "Visiting file $file" }
    return FileVisitResult.CONTINUE
  }

  override fun postVisitDirectory(dir: Path, exc: IOException?): FileVisitResult {
    log.info { "Visiting directory $dir" }
    return FileVisitResult.CONTINUE
  }
}