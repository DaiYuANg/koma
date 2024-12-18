package org.koma.shared.api

import org.koma.shared.data.TemplateEngine

interface Theme {

  fun name(): String

  fun template(): TemplateEngine

  fun variables(): Map<String, Any>
}