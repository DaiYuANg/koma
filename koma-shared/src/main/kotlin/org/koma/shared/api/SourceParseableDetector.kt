package org.koma.shared.api

import org.koma.shared.context.SourceParseContext

interface SourceParseableDetector {
  fun parseable(context: SourceParseContext): SourceParser?
}
