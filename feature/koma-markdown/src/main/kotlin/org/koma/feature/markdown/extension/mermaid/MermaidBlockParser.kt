package org.koma.feature.markdown.extension.mermaid

import com.vladsch.flexmark.parser.InlineParser
import com.vladsch.flexmark.parser.block.BlockContinue
import com.vladsch.flexmark.parser.block.BlockParser
import com.vladsch.flexmark.parser.block.BlockParserFactory
import com.vladsch.flexmark.parser.block.ParserState
import com.vladsch.flexmark.util.ast.Block
import com.vladsch.flexmark.util.ast.BlockContent
import com.vladsch.flexmark.util.data.MutableDataHolder
import com.vladsch.flexmark.util.sequence.BasedSequence

class MermaidBlockParser : BlockParser {
  private val content = StringBuilder()
  private val processBuilder = ProcessBuilder()

  override fun isContainer(): Boolean {
    TODO("Not yet implemented")
  }

  override fun canContain(
      p0: ParserState?,
      p1: BlockParser?,
      p2: Block?,
  ): Boolean {
    TODO("Not yet implemented")
  }

  override fun getBlock(): Block = MermaidNode(content.toString())

  override fun tryContinue(p0: ParserState?): BlockContinue {
    TODO("Not yet implemented")
  }

  override fun addLine(
      p0: ParserState?,
      p1: BasedSequence?,
  ) {
    TODO("Not yet implemented")
  }

  override fun closeBlock(p0: ParserState?) {
    TODO("Not yet implemented")
  }

  override fun isClosed(): Boolean {
    TODO("Not yet implemented")
  }

  override fun isPropagatingLastBlankLine(p0: BlockParser?): Boolean {
    TODO("Not yet implemented")
  }

  override fun breakOutOnDoubleBlankLine(): Boolean {
    TODO("Not yet implemented")
  }

  override fun isParagraphParser(): Boolean {
    TODO("Not yet implemented")
  }

  override fun getBlockContent(): BlockContent {
    TODO("Not yet implemented")
  }

  override fun finalizeClosedBlock() {
    TODO("Not yet implemented")
  }

  override fun parseInlines(p0: InlineParser?) {
    TODO("Not yet implemented")
  }

  override fun isInterruptible(): Boolean {
    TODO("Not yet implemented")
  }

  override fun isRawText(): Boolean {
    TODO("Not yet implemented")
  }

  override fun canInterruptBy(p0: BlockParserFactory?): Boolean {
    TODO("Not yet implemented")
  }

  override fun getDataHolder(): MutableDataHolder {
    TODO("Not yet implemented")
  }
}
