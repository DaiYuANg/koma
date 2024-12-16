package org.koma.feature.markdown

import com.vladsch.flexmark.parser.block.*
import com.vladsch.flexmark.util.data.DataHolder

class MermaidBlockParserFactory : CustomBlockParserFactory {
  override fun apply(p0: DataHolder): BlockParserFactory {
    TODO("Not yet implemented")
  }

  override fun getAfterDependents(): MutableSet<Class<*>>? {
    TODO("Not yet implemented")
  }

  override fun getBeforeDependents(): MutableSet<Class<*>>? {
    TODO("Not yet implemented")
  }

  override fun affectsGlobalScope(): Boolean {
    TODO("Not yet implemented")
  }
}