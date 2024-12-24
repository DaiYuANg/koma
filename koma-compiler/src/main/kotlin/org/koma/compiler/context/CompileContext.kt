package org.koma.compiler.context

import kotlinx.html.*
import kotlinx.html.stream.createHTML
import org.koma.shared.data.structure.KomaDocument
import java.util.concurrent.CopyOnWriteArraySet

class CompileContext {
  val htmlContext = CopyOnWriteArraySet<KomaDocument>()

  fun generateIndex(): String {
    return createHTML().html {
      head {
        title("Homepage")
        meta { charset = "UTF-8" }
        link(rel = "stylesheet", href = "styles.css") // 可选：添加样式表
      }
      body {
        h1 { +"Welcome to the Index Page" }
        ul {
          htmlContext.forEach {
            li {
              val target = it.generateFilename()
              a(href = "./${target}") {
                +target
              }
            }
          }
        }
      }
    }
  }
}
