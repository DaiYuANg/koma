package org.koma.api;

import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Document;

public interface SourceParser {

  Document parse(@NotNull String source);
}
