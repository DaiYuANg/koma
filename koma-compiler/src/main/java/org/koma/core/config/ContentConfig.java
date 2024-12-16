package org.koma.core.config;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import org.koma.core.annotation.KomaImmutableStyle;

@Value.Immutable
@KomaImmutableStyle
@JsonSerialize(as = ImmutableContentConfig.class)
@JsonDeserialize(as = ImmutableContentConfig.class)
public interface ContentConfig {
  String posts();

  String pages();

  String drafts();
}
