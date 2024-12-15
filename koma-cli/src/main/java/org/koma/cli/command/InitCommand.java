package org.koma.cli.command;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.koma.cli.processor.InitProcessor;
import picocli.CommandLine.Command;

import java.util.List;

@Command(name = "init", aliases = "i")
@Singleton
@RequiredArgsConstructor
@Slf4j
public class InitCommand implements Runnable {
  private final List<InitProcessor> processors;

  @Override
  public void run() {
//    Init directory
    processors.forEach(InitProcessor::process);
  }
}
