package org.koma.feature.markdown

import com.vladsch.flexmark.ast.Text
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterNode
import com.vladsch.flexmark.util.ast.Node
import com.vladsch.flexmark.util.ast.Visitor
import io.github.oshai.kotlinlogging.KotlinLogging

class GlobalVisitor : Visitor<Text> {
  private val wordCount = 0
  private val log = KotlinLogging.logger {}
  private val yamlMetadataVisitor = YamlMetadataVisitor()

  override fun visit(p0: Text) {
    log.atInfo { "${p0.chars.unescape()} visit" }
  }
}