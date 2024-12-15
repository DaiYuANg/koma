package org.koma.core.config;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import org.koma.core.annotation.KomaImmutableStyle;

@Value.Immutable
@KomaImmutableStyle
@JsonSerialize(as = org.koma.core.config.ImmutableSiteConfig.class)
@JsonDeserialize(as = org.koma.core.config.ImmutableSiteConfig.class)
public interface SiteConfig {

  String url();

  String title();

  String description();
}
