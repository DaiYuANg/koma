package org.koma.cli.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;

@UtilityClass
@Slf4j
public class IO {

  public void checkDirExistsOrCreate(@NotNull Path dir) {
    val dirFile = dir.toAbsolutePath().toFile();
    if (!dirFile.exists()) {
      if (dirFile.mkdir()) {
        log.info("created {}", dir.toAbsolutePath().toString());
      }
    }
  }
}
