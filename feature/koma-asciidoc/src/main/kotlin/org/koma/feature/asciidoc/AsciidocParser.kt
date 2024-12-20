package org.koma.feature.asciidoc

import com.google.auto.service.AutoService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.asciidoctor.Asciidoctor
import org.asciidoctor.Options
import org.asciidoctor.SafeMode
import org.jsoup.Jsoup
import org.koma.shared.api.SourceParser
import org.koma.shared.data.structure.DocumentMetadata
import org.koma.shared.data.structure.KomaDocument

@AutoService(SourceParser::class)
class AsciidocParser : SourceParser {
  private val log = KotlinLogging.logger {}

  private val asciidoctor = Asciidoctor.Factory.create()

  override fun parse(source: String): KomaDocument {
    val asciiDocument =
        asciidoctor.load(
            source,
            Options.builder().safe(SafeMode.UNSAFE).standalone(true).sourcemap(true).build(),
        )
    log.atInfo { "Title:${asciiDocument.title}" }
    log.atInfo { "Authors:${asciiDocument.authors}" }
    return KomaDocument(
        htmlDocument = Jsoup.parseBodyFragment(asciiDocument.convert()),
        metadata = DocumentMetadata(title = asciiDocument.title, author = null),
    )
  }
}
