package org.koma.core.parser

import org.asciidoctor.Asciidoctor
import org.asciidoctor.Options
import org.asciidoctor.OptionsBuilder
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.koma.core.api.SourceParser

class AsciidocParser : SourceParser {
  private val asciidoctor: Asciidoctor = Asciidoctor.Factory.create()
  override fun parse(source: String): Document {
    val result = asciidoctor.convert(source.trim(), Options.builder().build())
    return Jsoup.parseBodyFragment(result)
  }
}