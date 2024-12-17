package org.koma.feature.markdown.factory

import com.vladsch.flexmark.ext.admonition.AdmonitionExtension
import com.vladsch.flexmark.ext.anchorlink.AnchorLinkExtension
import com.vladsch.flexmark.ext.aside.AsideExtension
import com.vladsch.flexmark.ext.attributes.AttributesExtension
import com.vladsch.flexmark.ext.autolink.AutolinkExtension
import com.vladsch.flexmark.ext.emoji.EmojiExtension
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceExtension
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension
import com.vladsch.flexmark.ext.resizable.image.ResizableImageExtension
import com.vladsch.flexmark.ext.tables.TablesExtension
import com.vladsch.flexmark.ext.toc.TocExtension
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterExtension
import com.vladsch.flexmark.ext.youtube.embedded.YouTubeLinkExtension
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.html.HtmlRenderer.FENCED_CODE_LANGUAGE_CLASS_MAP
import com.vladsch.flexmark.html.HtmlRendererOptions
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.DataSet
import com.vladsch.flexmark.util.data.MutableDataSet
import org.koma.feature.markdown.extension.header.HeaderExtension
import java.util.concurrent.ConcurrentHashMap

val extensions = listOf(
  TablesExtension.create(),
  StrikethroughExtension.create(),
  EmojiExtension.create(),
  AutolinkExtension.create(),
  AnchorLinkExtension.create(),
  AdmonitionExtension.create(),
  AsideExtension.create(),
  TocExtension.create(),
  AttributesExtension.create(),
  EnumeratedReferenceExtension.create(),
  YamlFrontMatterExtension.create(),
  YouTubeLinkExtension.create(),
  HeaderExtension.create(),
  ResizableImageExtension.create()
)

fun create(): DataSet {
  return MutableDataSet()
    .set(Parser.EXTENSIONS, extensions)
    .set(Parser.HTML_BLOCK_DEEP_PARSER, true)
    .set(Parser.HTML_BLOCK_PARSER, true)
    .toImmutable()
}
