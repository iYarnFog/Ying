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
package net.codestory.http.compilers;

import java.io.*;
import java.nio.file.*;
import java.util.function.*;

import net.codestory.http.logs.*;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.newBufferedWriter;

public class DiskCache {
  private final File root;

  public DiskCache(String version, boolean prodMode) {
    this.root = Paths.get(System.getProperty("user.home"), ".code-story", "cache", version, prodMode ? "prod" : "dev").toFile();
    Logs.cachingOnDisk(this.root);
  }

  CacheEntry computeIfAbsent(String sha1, String extension, Supplier<String> toCompiled) {
    File file = new File(new File(root, extension.substring(1)), sha1);
    if (file.exists()) {
      try {
        return CacheEntry.fromFile(file);
      } catch (IOException e) {
        // ignore cache entry
      }
    }

    String compiled = toCompiled.get();

    try {
      writeToCache(file, compiled);
      return CacheEntry.fromString(compiled);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  private static void writeToCache(File file, String data) throws IOException {
    File parentFile = file.getParentFile();
    if (!parentFile.exists() && !parentFile.mkdirs()) {
      throw new IllegalStateException("Unable to create cache folder: " + parentFile);
    }

    File tmpFile = new File(file.getAbsolutePath() + ".tmp");

    try (Writer writer = newBufferedWriter(tmpFile.toPath(), UTF_8)) {
      writer.append(data);
    }

    tmpFile.renameTo(file);
  }
}
