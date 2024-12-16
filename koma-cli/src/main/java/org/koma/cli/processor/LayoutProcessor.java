package org.koma.cli.processor;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.koma.cli.util.IO;
import org.koma.core.model.KomaLayout;

import java.util.List;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class LayoutProcessor implements InitProcessor {
  private final KomaLayout komaLayout;

  @Override
  public void process() {
    val processDir = List.of(komaLayout.content(), komaLayout.templates(), komaLayout.assets());
    processDir.forEach(IO::checkDirExistsOrCreate);
  }
}
