package org.koma.compiler.fs

import io.github.oshai.kotlinlogging.KotlinLogging
import java.nio.file.FileVisitResult
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes
import kotlin.io.path.name
import org.apache.commons.io.FileUtils
import org.koma.compiler.context.CompileContext
import org.koma.shared.api.SourceParseableDetector
import org.koma.shared.context.SourceParseContext
import java.io.IOException
import kotlin.io.path.absolutePathString
import kotlin.io.path.extension
import kotlin.io.path.nameWithoutExtension

class CompileSourceVisitor(
  private val compileContext: CompileContext,
  private val sourceParseableDetectors: Set<SourceParseableDetector>
) : SimpleFileVisitor<Path>() {
  private val log = KotlinLogging.logger {}

  override fun preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult {
    log.atDebug { message = "Enter ${dir.absolutePathString()}" }
    return FileVisitResult.CONTINUE
  }

  override fun visitFile(
    file: Path,
    attrs: BasicFileAttributes,
  ): FileVisitResult {
    log.info { "Visiting file ${file.nameWithoutExtension}" }
    val filename = file.nameWithoutExtension
    val extension = file.extension
    val context = SourceParseContext(file = file, extension = extension)

    sourceParseableDetectors
      .firstNotNullOfOrNull { it.parseable(context) }
      ?.let {
        val fileContent = FileUtils.readFileToString(file.toFile(), Charsets.UTF_8)
        log.info { "SourceParser $it" }
        val komaDocument = it.parse(fileContent)
        compileContext.htmlContext.add(komaDocument)
      }
    return FileVisitResult.CONTINUE
  }

  override fun visitFileFailed(file: Path, exc: IOException): FileVisitResult {
    log.atError { exc.message }
    return FileVisitResult.CONTINUE
  }
}
