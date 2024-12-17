package org.koma.feature.markdown.visitor

import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterNode
import com.vladsch.flexmark.util.ast.Visitor
import io.github.oshai.kotlinlogging.KotlinLogging

class YamlFrontMatterVisitor : Visitor<YamlFrontMatterNode> {
  private val wordCount = 0
  private val log = KotlinLogging.logger {}
  private val yamlMetadataVisitor = YamlMetadataVisitor()
  override fun visit(p0: YamlFrontMatterNode) {
    yamlMetadataVisitor.visit(p0)
  }

//  override fun visit(p0: Text) {
//    log.atInfo { "${p0.chars.unescape()} visit" }
//  }
}