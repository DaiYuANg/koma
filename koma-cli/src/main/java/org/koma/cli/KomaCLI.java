package org.koma.cli;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.fusesource.jansi.AnsiConsole;
import org.koma.cli.context.DI;
import picocli.CommandLine;

import static org.fusesource.jansi.AnsiConsole.systemInstall;
import static org.fusesource.jansi.AnsiConsole.systemUninstall;

@Slf4j
public class KomaCLI {

  static {
    systemInstall();
  }

  public static void main(String... args) {
    log.atInfo().log("Starting Koma CLI...");
    val code = DI.INSTANCE.getBean(CommandLine.class).execute(args);
    if (code != 9090) {
      systemUninstall();
      System.exit(code);
    }
  }
}
