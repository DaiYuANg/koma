package org.koma.cli.provider;

import lombok.val;
import org.jetbrains.annotations.NotNull;
import picocli.CommandLine;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class ManifestVersionProvider implements CommandLine.IVersionProvider {
  @Override
  public String[] getVersion() throws Exception {
    val resources = ManifestVersionProvider.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
    while (resources.hasMoreElements()) {
      val url = resources.nextElement();
      try {
        val manifest = new Manifest(url.openStream());
        if (isApplicableManifest(manifest)) {
          Attributes attr = manifest.getMainAttributes();
          return new String[]{get(attr, "Implementation-Title") + " version \"" +
            get(attr, "Implementation-Version") + "\""};
        }
      } catch (IOException ex) {
        return new String[]{"Unable to read from " + url + ": " + ex};
      }
    }
    return new String[0];
  }

  private boolean isApplicableManifest(@NotNull Manifest manifest) {
    val attributes = manifest.getMainAttributes();
    return "picocli".equals(get(attributes, "Implementation-Title"));
  }

  private Object get(@NotNull Attributes attributes, String key) {
    return attributes.get(new Attributes.Name(key));
  }
}
