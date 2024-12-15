package org.koma.core.layout;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.koma.core.constant.ConfigConstant;

import java.nio.file.Path;

@Builder
@Getter
public class KomaLayout {
  public final Path basePath;

  public Path config() {
    return basePath.resolve(ConfigConstant.FILE_NAME);
  }

  @Contract(pure = true)
  public @NotNull Path content() {
    return basePath.resolve("content");
  }

  public @NotNull Path assets() {
    return basePath.resolve("assets");
  }

  public @NotNull Path templates() {
    return basePath.resolve("templates");
  }

  public @NotNull Path dist() {
    return basePath.resolve("dist");
  }
}
