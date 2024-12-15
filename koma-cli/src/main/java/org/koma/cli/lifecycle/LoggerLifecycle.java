package org.koma.cli.lifecycle;

import ch.qos.logback.classic.LoggerContext;
import com.google.common.util.concurrent.AbstractIdleService;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

@Singleton
@Slf4j
public class LoggerLifecycle extends AbstractIdleService {

  @Override
  protected void startUp() throws Exception {
    if (LoggerFactory.getILoggerFactory() instanceof LoggerContext context) {
      log.atDebug().log("LoggerLifecycle init");
    }
  }

  @Override
  protected void shutDown() throws Exception {
    log.atInfo().log("Pre-destroy");
    if (LoggerFactory.getILoggerFactory() instanceof LoggerContext context) {
      context.stop();
    }
  }
}
