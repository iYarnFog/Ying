/**
 * Copyright (C) 2013-2015 all@code-story.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package net.codestory.http.reload;

import java.nio.file.Path;

import com.google.common.annotations.VisibleForTesting;

public class FolderWatcher {
  private final Path folder;
  private final FolderChangeListener listener;

  private WatchServiceFacade watcher;

  public FolderWatcher(Path folder, FolderChangeListener listener) {
    this.folder = folder;
    this.listener = listener;
  }

  public void ensureStarted() {
    if (watcher != null) {
      return;
    }

    if (!folder.toFile().exists()) {
      return;
    }

    if (isMac()) {
      watcher = new OsxWatchService(folder);
    } else {
      watcher = new JdkWatchService(folder);
    }
    watcher.onChange(listener);
  }

  public void stop() {
    if (watcher != null) {
      watcher.stop();
    }
  }

  @VisibleForTesting
  protected boolean isMac() {
    return System.getProperty("os.name").startsWith("Mac OS X");
  }
}
