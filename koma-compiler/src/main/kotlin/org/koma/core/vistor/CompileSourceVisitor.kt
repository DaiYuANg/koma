package org.koma.core.vistor

import io.github.oshai.kotlinlogging.KotlinLogging
import io.smallrye.mutiny.Uni
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.koma.core.context.CompileContext
import java.io.IOException
import java.nio.file.FileVisitResult
import java.nio.file.FileVisitor
import java.nio.file.Path
import java.nio.file.attribute.BasicFileAttributes
import java.util.concurrent.Executors
import kotlin.io.path.name


class CompileSourceVisitor(
  private val compileContext: CompileContext
) : FileVisitor<Path> {
  private val log = KotlinLogging.logger {}
  private val executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2 + 1)
  override fun preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult {
    log.info { "Visiting $dir" }
    return FileVisitResult.CONTINUE
  }

  override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
    log.info { "Visiting file ${file.name}" }
    val filename = FilenameUtils.getName(file.name)
    val extension = FilenameUtils.getExtension(file.name)
    val process = Uni.createFrom().voidItem()
      .emitOn(executor)
      .invoke(Runnable {
        compileContext.sourceParser
          .find { it.parseable(extension) }?.apply {
            val fileContent = FileUtils.readFileToString(file.toFile(), Charsets.UTF_8)
            log.info { "SourceParser $this" }
            val result = this.parse(fileContent)
            compileContext.htmlContext[filename] = result
          }
      })
    compileContext.process.add(process)
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