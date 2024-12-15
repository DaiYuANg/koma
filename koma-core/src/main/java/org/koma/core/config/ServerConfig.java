package org.koma.core.config;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import org.koma.core.annotation.KomaImmutableStyle;

import static org.koma.core.constant.ConfigConstant.DEFAULT_HOST;

@Value.Immutable
@KomaImmutableStyle
@JsonSerialize(as = org.koma.core.config.ImmutableServerConfig.class)
@JsonDeserialize(as = org.koma.core.config.ImmutableServerConfig.class)
public interface ServerConfig {
  @Value.Default
  default String host() {
    return DEFAULT_HOST;
  }

  Integer port();
}
