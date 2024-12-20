package org.koma.feature.markdown.context

import java.util.Date

class ParseContext {
  var author: String? = null
  var date: Date = Date()
  var description: String? = null
  val categories: MutableList<String> = mutableListOf()
  var title: String? = null
  val tags: MutableList<String> = mutableListOf()
}
