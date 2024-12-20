package org.koma.compiler.fs

import io.github.oshai.kotlinlogging.KotlinLogging
import java.nio.file.FileVisitResult
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes
import kotlin.io.path.name
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.koma.compiler.context.CompileContext
import org.koma.shared.context.SourceParseContext

class CompileSourceVisitor(
    private val compileContext: CompileContext,
) : SimpleFileVisitor<Path>() {
  private val log = KotlinLogging.logger {}

  //  private val pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.kt")

  override fun visitFile(
      file: Path,
      attrs: BasicFileAttributes,
  ): FileVisitResult {
    log.info { "Visiting file ${file.name}" }
    val filename = FilenameUtils.getName(file.name)
    val extension = FilenameUtils.getExtension(file.name)
    val context = SourceParseContext(file = file, extension = extension)

    compileContext.sourceParseableDetectors
        .firstNotNullOfOrNull { it.parseable(context) }
        ?.let {
          val fileContent = FileUtils.readFileToString(file.toFile(), Charsets.UTF_8)
          log.info { "SourceParser $it" }
          val komaDocument = it.parse(fileContent)
          compileContext.htmlContext[filename] = komaDocument.htmlDocument
        }
    return FileVisitResult.CONTINUE
  }

  //  override fun visitFileFailed(file: Path, exc: IOException): FileVisitResult {
  //    log.info { "Visiting file $file" }
  //    return FileVisitResult.CONTINUE
  //  }

  //  override fun postVisitDirectory(dir: Path, exc: IOException?): FileVisitResult {
  //    log.info { "out Visiting directory $dir" }
  //    return FileVisitResult.CONTINUE
  //  }
}
