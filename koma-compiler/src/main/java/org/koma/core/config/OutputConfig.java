package org.koma.core.config;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import org.koma.core.annotation.KomaImmutableStyle;

@Value.Immutable
@KomaImmutableStyle
@JsonSerialize(as = ImmutableOutputConfig.class)
@JsonDeserialize(as = ImmutableOutputConfig.class)
public abstract class OutputConfig {

  @Value.Default
  public String directory(){
    return "dist";
  };

  public abstract Boolean clean();
}
