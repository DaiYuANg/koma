package org.koma.feature.asciidoc

import com.google.auto.service.AutoService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.asciidoctor.Asciidoctor
import org.asciidoctor.Options
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.koma.api.SourceParser

@AutoService(SourceParser::class)
class AsciidocParser : SourceParser {
  private val log = KotlinLogging.logger {}
  private val supportExtension = setOf("adoc")

  override fun parseable(extension: String?): Boolean {
    log.info { "Ascii Parsing $extension ${supportExtension.contains(extension)}" }
    return supportExtension.contains(extension)
  }

  override fun parse(source: String): Document {
    val asciidoctor = Asciidoctor.Factory.create()
    val asciidoctorResult = asciidoctor.convert(source.trim(), Options.builder().build())
    return Jsoup.parseBodyFragment(asciidoctorResult)
  }
}