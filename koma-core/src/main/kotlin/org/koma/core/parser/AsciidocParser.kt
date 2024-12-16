package org.koma.core.parser

import org.asciidoctor.Asciidoctor
import org.asciidoctor.Options
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.koin.java.KoinJavaComponent.inject
import org.koma.api.SourceParser

class AsciidocParser : SourceParser {
  private val asciidoctor: Asciidoctor by inject(Asciidoctor::class.java)
  override fun parse(source: String): Document {
    val result = asciidoctor.convert(source.trim(), Options.builder().build())
    return Jsoup.parseBodyFragment(result)
  }
}