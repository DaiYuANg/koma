package org.koma.cli.command;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.koma.cli.listener.LayoutListener;
import org.koma.core.model.KomaLayout;
import picocli.CommandLine;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@CommandLine.Command(name = "watch", aliases = "w")
@Singleton
@RequiredArgsConstructor
public class WatchCommand implements Callable<Integer> {

  private final KomaLayout komaLayout;

  @Override
  public Integer call() throws Exception {
    val directories = FileFilterUtils
      .and(
      FileFilterUtils.directoryFileFilter(),
      HiddenFileFilter.VISIBLE
      );
    val files = FileFilterUtils.and(FileFilterUtils.fileFileFilter());
    val filter = FileFilterUtils.or(directories, files);
    val observer = FileAlterationObserver.builder().setPath(komaLayout.getPath())
      .setFileFilter(filter)
      .get();
    val interval = TimeUnit.SECONDS.toMillis(1);
    observer.addListener(new LayoutListener());
    val monitor = new FileAlterationMonitor(interval, observer);
    // 开始监控
    monitor.start();
    return 9090;
  }
}
