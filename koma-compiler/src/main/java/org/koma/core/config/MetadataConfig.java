package org.koma.core.config;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import org.koma.core.annotation.KomaImmutableStyle;

import java.util.Map;

@Value.Immutable
@KomaImmutableStyle
@JsonSerialize(as = ImmutableMetadataConfig.class)
@JsonDeserialize(as = ImmutableMetadataConfig.class)
public interface MetadataConfig {

  String author();

  String theme();

  Map<String, String> social();
}
