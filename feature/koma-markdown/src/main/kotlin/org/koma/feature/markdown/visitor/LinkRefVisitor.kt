package org.koma.feature.markdown.visitor

import com.vladsch.flexmark.ast.Link
import com.vladsch.flexmark.util.ast.Visitor
import io.github.oshai.kotlinlogging.KotlinLogging
import org.koma.feature.markdown.context.ParseContext

class LinkRefVisitor (
  private val context: ParseContext
): Visitor<Link> {
  private val log = KotlinLogging.logger {}
  override fun visit(p0: Link) {
    log.atInfo { message = "ref:${p0}" }
  }
}