package org.koma.cli.factory;

import com.google.inject.ConfigurationException;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.koma.cli.context.DI;
import picocli.CommandLine;

@Singleton
@Slf4j
public class PicocliFactory implements CommandLine.IFactory {

  @Override
  public <K> K create(Class<K> aClass) throws Exception {
    try {
      return DI.INSTANCE.getBean(aClass);
    } catch (ConfigurationException ex) { // no implementation found in Guice configuration
      return CommandLine.defaultFactory().create(aClass); // fallback if missing
    }
  }
}
