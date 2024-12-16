package org.koma.feature.markdown

import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.parser.Parser.ParserExtension
import com.vladsch.flexmark.util.data.MutableDataHolder

class MermaidExtension: ParserExtension {

  override fun parserOptions(p0: MutableDataHolder) {
  }

  override fun extend(builder: Parser.Builder) {
    builder.customBlockParserFactory(MermaidBlockParserFactory())
  }

}