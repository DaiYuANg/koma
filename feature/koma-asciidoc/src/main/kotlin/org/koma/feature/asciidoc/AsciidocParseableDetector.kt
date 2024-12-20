package org.koma.feature.asciidoc

import com.google.auto.service.AutoService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.apache.tika.Tika
import org.koma.shared.api.SourceParseableDetector
import org.koma.shared.api.SourceParser
import org.koma.shared.context.SourceParseContext

@AutoService(SourceParseableDetector::class)
class AsciidocParseableDetector : SourceParseableDetector {
  private val log = KotlinLogging.logger {}
  private val supportExtension = setOf("adoc")
  private val mimeType = "text/x-asciidoc"
  private val tika = Tika()

  override fun parseable(context: SourceParseContext): SourceParser? {
    if (!supportExtension.contains(context.extension)) {
      return null
    }
    if (!tika.detect(context.file).equals(mimeType)) {
      return null
    }
    log.info { "Ascii Parsing ${context.file}" }
    return AsciidocParser()
  }
}
