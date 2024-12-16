package org.koma.cli.command;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.koma.cli.context.DI;
import org.koma.cli.provider.ManifestVersionProvider;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
  name = "koma", mixinStandardHelpOptions = true,
  version = "koma 0.1",
  description = "Static Site generator",
  subcommands = {
    GenerateCommand.class,
    ServeCommand.class,
    WatchCommand.class,
    InitCommand.class
  },
  versionProvider = ManifestVersionProvider.class
)
@Slf4j
@Singleton
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class RootCommand implements Callable<Integer> {

  @Override
  public Integer call() {
    DI.INSTANCE.getBean(CommandLine.class).usage(System.out);
    return 0;
  }
}