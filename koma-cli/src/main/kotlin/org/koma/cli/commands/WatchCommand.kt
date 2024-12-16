package org.koma.cli.commands

import com.github.ajalt.clikt.core.CliktCommand
import io.github.oshai.kotlinlogging.KotlinLogging
import org.apache.commons.io.filefilter.FileFilterUtils
import org.apache.commons.io.filefilter.HiddenFileFilter
import org.apache.commons.io.monitor.FileAlterationMonitor
import org.apache.commons.io.monitor.FileAlterationObserver
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koma.cli.listener.LayoutListener
import org.koma.core.model.KomaLayout
import java.util.concurrent.TimeUnit

@Single
class WatchCommand : CliktCommand(), KoinComponent {

  private val log = KotlinLogging.logger {}
  private val layoutListener by inject<LayoutListener>()
  private val komaLayout by inject<KomaLayout>()

  override fun run() {
    val directories = FileFilterUtils
      .and(
        FileFilterUtils.directoryFileFilter(),
        HiddenFileFilter.VISIBLE
      );
    val files = FileFilterUtils.and(FileFilterUtils.fileFileFilter());
    val filter = FileFilterUtils.or(directories, files);
    val observer = FileAlterationObserver.builder().setPath(komaLayout.path)
      .setFileFilter(filter)
      .get();
    val interval = TimeUnit.SECONDS.toMillis(1);
    observer.addListener(layoutListener);
    val monitor = FileAlterationMonitor(interval, observer);
    monitor.start();
  }
}