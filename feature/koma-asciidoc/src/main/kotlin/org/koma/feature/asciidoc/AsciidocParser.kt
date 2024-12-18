package org.koma.feature.asciidoc

import com.google.auto.service.AutoService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.asciidoctor.Asciidoctor
import org.asciidoctor.Options
import org.asciidoctor.SafeMode
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.koma.shared.SourceParseContext
import org.koma.shared.SourceParser

@AutoService(SourceParser::class)
class AsciidocParser : SourceParser {
  private val log = KotlinLogging.logger {}
  private val supportExtension = setOf("adoc")
  private val asciidoctor = Asciidoctor.Factory.create()

  override fun parseable(context: SourceParseContext): Boolean {
    log.info { "Ascii Parsing ${context.extension} ${supportExtension.contains(context.extension)}" }
    return supportExtension.contains(context.extension)
  }

  override fun parse(context: SourceParseContext): Document {
    val asciiDocument = asciidoctor.load(
      context.extension, Options.builder()
        .safe(SafeMode.UNSAFE)
        .standalone(true)
        .sourcemap(true)
        .build()
    )
    log.atInfo { "Title:${asciiDocument.title}" }
    log.atInfo { "Authors:${asciiDocument.authors}" }
    return Jsoup.parseBodyFragment(asciiDocument.convert())
  }
}