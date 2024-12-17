package org.koma.api;


import com.helger.css.ECSSVersion;
import com.helger.css.reader.CSSReader;
import org.eclipse.collections.api.factory.Maps;

import java.util.Map;

public class CompileContext {
  private final Map<String, String> a = Maps.mutable.<String, String>empty().asSynchronized();

  public void test() {
    CSSReader.readFromString("", ECSSVersion.CSS30);
  }
}
