package org.koma.theme.bootstrap

import org.apache.commons.io.IOUtils
import org.webjars.WebJarAssetLocator
import java.nio.charset.StandardCharsets

class Theme {
  init {
    val bootstrap = WebJarAssetLocator().getFullPath("bootstrap.js");
    val a = ClassLoader.getSystemClassLoader().getResourceAsStream(bootstrap)
    System.err.println(IOUtils.resourceToString(bootstrap, StandardCharsets.UTF_8, ClassLoader.getSystemClassLoader()))
  }
}