package org.koma.feature.markdown

import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterBlock
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterNode
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterVisitor
import io.github.oshai.kotlinlogging.KotlinLogging

class YamlMetadataVisitor : YamlFrontMatterVisitor {
  private val log = KotlinLogging.logger {}
  override fun visit(node: YamlFrontMatterNode) {
    log.atDebug { message="node:${node.key}" }
  }

  override fun visit(block: YamlFrontMatterBlock) {
  }
}