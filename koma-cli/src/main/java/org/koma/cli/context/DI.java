package org.koma.cli.context;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jetbrains.annotations.NotNull;
import org.koma.cli.module.CliModule;

public enum DI {
  INSTANCE;

  private final Injector injector = Guice.createInjector(new CliModule());

  public <T> @NotNull T getBean(Class<T> clazz) {
    return injector.getInstance(clazz);
  }
}
