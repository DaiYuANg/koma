package org.koma.core.context;

import io.soabase.recordbuilder.core.RecordBuilder;
import org.koma.core.config.KomaConfig;

@RecordBuilder
public record ProcessContext(
  KomaConfig config
) {
}
