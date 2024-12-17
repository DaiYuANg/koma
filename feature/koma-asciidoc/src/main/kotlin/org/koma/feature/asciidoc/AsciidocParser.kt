package org.koma.feature.asciidoc

import com.google.auto.service.AutoService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.asciidoctor.Asciidoctor
import org.asciidoctor.Options
import org.asciidoctor.SafeMode
import org.asciidoctor.extension.InlineMacroProcessor
import org.asciidoctor.extension.Treeprocessor
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.koma.api.SourceParser

@AutoService(SourceParser::class)
class AsciidocParser : SourceParser {
  private val log = KotlinLogging.logger {}
  private val supportExtension = setOf("adoc")
  private val asciidoctor = Asciidoctor.Factory.create()

  override fun parseable(extension: String?): Boolean {
    log.info { "Ascii Parsing $extension ${supportExtension.contains(extension)}" }
    return supportExtension.contains(extension)
  }

  override fun parse(source: String): Document {
    val asciiDocument = asciidoctor.load(
      source, Options.builder()
        .safe(SafeMode.UNSAFE)
        .standalone(true)
        .sourcemap(true)
        .build()
    )
    log.atInfo { "Title:${asciiDocument.title}" }
    log.atInfo { "Authors:${asciiDocument.authors}" }
    System.err.println(asciiDocument.catalog.refs)
    return Jsoup.parseBodyFragment(asciiDocument.convert())
  }
}