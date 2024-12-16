package org.koma.core.vistor

import io.github.oshai.kotlinlogging.KotlinLogging
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.koma.core.context.ParseContext
import org.koma.core.parser.AsciidocParser
import org.koma.core.parser.MarkdownParser
import org.webjars.WebJarAssetLocator
import java.io.IOException
import java.nio.file.FileVisitResult
import java.nio.file.FileVisitor
import java.nio.file.Path
import java.nio.file.attribute.BasicFileAttributes
import kotlin.io.path.name


class CompileSourceVisitor(
  private val parseContext: ParseContext
) : FileVisitor<Path> {
  private val log = KotlinLogging.logger {}
  private val markdownParser = MarkdownParser()
  private val asciidocParser = AsciidocParser()
  private val supportFileType = setOf("md", "adoc")
  override fun preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult {
    log.info { "Visiting $dir" }
    return FileVisitResult.CONTINUE
  }

  override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
    log.info { "Visiting file ${file.name}" }
    val filename = FilenameUtils.getName(file.name)
    val extension = FilenameUtils.getExtension(file.name)
    if (supportFileType.contains(extension)) {
      val fileContent = FileUtils.readFileToString(file.toFile(), Charsets.UTF_8)
      when (extension) {
        "md" -> {
          val document = markdownParser.parse(fileContent)
          parseContext.put(filename, document)
        }

        "adoc" -> {
          val document = asciidocParser.parse(fileContent)
          parseContext.put(filename, document)
        }
      }
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