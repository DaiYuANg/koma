package org.koma.api;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record DocumentMetadata(
  String title,
  String author
) {
}
