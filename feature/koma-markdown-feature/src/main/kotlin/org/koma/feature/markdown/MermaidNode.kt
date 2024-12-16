package org.koma.feature.markdown

import com.vladsch.flexmark.util.ast.Block
import com.vladsch.flexmark.util.sequence.BasedSequence

class MermaidNode constructor(
  private val content: String,
) : Block() {
  override fun getSegments(): Array<BasedSequence> {
    TODO("Not yet implemented")
  }
}