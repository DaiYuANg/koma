package org.koma.shared.api

import org.koma.shared.data.TemplateEngineProvider

interface KomaTemplateEngine {

  fun provider(): TemplateEngineProvider

  fun parse(content: String): String
}
