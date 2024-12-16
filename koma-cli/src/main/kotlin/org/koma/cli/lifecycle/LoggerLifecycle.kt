package org.koma.cli.lifecycle

import ch.qos.logback.classic.LoggerContext
import com.google.common.util.concurrent.AbstractIdleService
import io.github.oshai.kotlinlogging.KotlinLogging
import lombok.extern.slf4j.Slf4j
import org.koin.core.annotation.Single
import org.slf4j.LoggerFactory


@Single
class LoggerLifecycle : AbstractIdleService() {
  private val log = KotlinLogging.logger {}

  override fun startUp() {
    if (LoggerFactory.getILoggerFactory() is LoggerContext) {
      log.atDebug { message = "LoggerLifecycle init" }
    }
  }

  override fun shutDown() {
    val context = LoggerFactory.getILoggerFactory()
    if (context is LoggerContext) {
      context.stop()
    }
  }
}
