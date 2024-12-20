package org.koma.feature.markdown

import com.google.auto.service.AutoService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.apache.tika.Tika
import org.koma.shared.api.SourceParseableDetector
import org.koma.shared.api.SourceParser
import org.koma.shared.context.SourceParseContext

@AutoService(SourceParseableDetector::class)
class MarkdownSourceParseableDetector : SourceParseableDetector {
  private val log = KotlinLogging.logger {}
  private val tika = Tika()

  private val supportExtension = setOf("markdown", "md")
  private val mimeTypes = "text/x-web-markdown"

  override fun parseable(context: SourceParseContext): SourceParser? {
    if (!supportExtension.contains(context.extension)) {
      return null
    }
    if (!tika.detect(context.file).equals(mimeTypes)) {
      return null
    }
    log.info { "Parsing ${context.file}" }
    return MarkdownParser()
  }
}
