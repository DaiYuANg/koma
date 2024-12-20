package org.koma.feature.markdown.visitor

import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterNode
import com.vladsch.flexmark.util.ast.Visitor
import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.stream.Collectors
import org.koma.feature.markdown.context.ParseContext

class YamlFrontMatterVisitor(
    private val context: ParseContext,
) : Visitor<YamlFrontMatterNode> {
  private val log = KotlinLogging.logger {}

  override fun visit(node: YamlFrontMatterNode) {
    val value = node.values.stream().collect(Collectors.joining(","))
    log.atDebug { message = "value:$value" }

    when (node.key) {
      TITLE -> {
        context.title = value
      }

      AUTHOR -> {
        context.author = value
      }
    }
    log.atDebug { message = "node:${node.key}" }
  }
}
