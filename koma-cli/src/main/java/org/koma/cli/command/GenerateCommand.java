package org.koma.cli.command;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.base.Stopwatch;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.FileUtils;
import org.koma.core.compiler.KomaCompiler;
import org.koma.core.config.KomaConfig;
import org.koma.core.model.KomaLayout;
import picocli.CommandLine;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@CommandLine.Command(name = "generate", aliases = "g")
@Singleton
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class GenerateCommand implements Callable<Integer> {

  private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

  private final KomaLayout komaLayout;

  private final Stopwatch stopwatch = Stopwatch.createStarted();

  @SneakyThrows
  @Override
  public Integer call() {
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    val configFile = komaLayout.config().toFile();
    if (!configFile.exists()) {
      log.error("Config file does not exist: {}", configFile);
      return 1;
    }
    @Cleanup val config = FileUtils.openInputStream(configFile);
    val komaConfig = mapper.readValue(config, KomaConfig.class);
    KomaCompiler.compile(komaConfig, komaLayout);
    val second = stopwatch.elapsed(TimeUnit.SECONDS);
    System.err.println(second);
    stopwatch.stop();
    return 0;
  }
}
