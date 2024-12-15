package org.koma.cli.command;

import io.javalin.Javalin;
import jakarta.inject.Singleton;
import picocli.CommandLine;

import java.util.Optional;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "serve", aliases = "s")
@Singleton
public class ServeCommand implements Callable<Integer> {

  @CommandLine.Option(names = "-w")
  private Optional<Boolean> watch;

  @Override
  public Integer call() throws Exception {
    Javalin.create(config -> {
      config.showJavalinBanner = false;
      config.useVirtualThreads = true;
    }).start(9999);
    return 9090;
  }
}
