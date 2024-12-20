package org.koma.shared.context

import com.helger.css.ECSSVersion
import com.helger.css.reader.CSSReader
import org.eclipse.collections.api.factory.Maps

class CompileContext {
  private val a: Map<String, String> = Maps.mutable.empty<String, String>().asSynchronized()

  fun test() {
    CSSReader.readFromString("", ECSSVersion.CSS30)
  }
}
