package org.koma.core.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;
import org.koma.core.annotation.KomaImmutableStyle;

@Value.Immutable
@KomaImmutableStyle
@JsonSerialize(as = ImmutableKomaConfig.class)
@JsonDeserialize(as = ImmutableKomaConfig.class)
public interface KomaConfig {
  SiteConfig site();

  @Value.Default
  default OutputConfig output() {
    return ImmutableOutputConfig.builder()
      .clean(false)
      .build();
  }

  ContentConfig content();

  @Nullable
  TemplateConfig template();

  @JsonProperty("static")
  StaticConfig staticConfig();

  MetadataConfig metadata();
}
