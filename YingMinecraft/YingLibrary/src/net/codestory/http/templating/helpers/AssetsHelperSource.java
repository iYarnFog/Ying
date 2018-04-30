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
package net.codestory.http.templating.helpers;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Function;

import net.codestory.http.compilers.CompilerFacade;
import net.codestory.http.io.Resources;
import net.codestory.http.misc.Cache;
import net.codestory.http.misc.Sha1;

import com.github.jknack.handlebars.Options;

public class AssetsHelperSource {
  private final Resources resources;
  private final CompilerFacade compilers;
  private final Function<String, String> urlSupplier;

  public AssetsHelperSource(boolean prodMode, Resources resources, CompilerFacade compilers) {
    this.resources = resources;
    this.compilers = compilers;
    this.urlSupplier = prodMode ? new Cache<>(this::uriWithSha1) : this::uriWithSha1;
  }

  // Handler entry points

  public CharSequence script(Object context, Options options) {
    String attributes = HelperTools.hashAsString(options);

    return HelperTools.toString(context, value -> singleScript(value.toString(), attributes));
  }

  public CharSequence css(Object context, Options options) {
    String attributes = HelperTools.hashAsString(options);

    return HelperTools.toString(context, value -> singleCss(value.toString(), attributes));
  }

  // Internal

  private String uriWithSha1(String uri) {
    try {
      Path path = resources.findExistingPath(uri);
      if ((path != null) && (resources.isPublic(path))) {
        return uri + '?' + Sha1.of(resources.readBytes(path));
      }

      Path sourcePath = compilers.findPublicSourceFor(uri);
      if (sourcePath != null) {
        return uri + '?' + Sha1.of(resources.readBytes(sourcePath));
      }
    } catch (IOException e) {
      throw new IllegalStateException("Unable to compute sha1 for: " + uri, e);
    }

    return uri;
  }

  private CharSequence singleScript(String path, String attributes) {
    String uri = addExtensionIfMissing(path, ".js");

    return "<script src=\"" + urlSupplier.apply(uri) + "\"" + attributes + "></script>";
  }

  private CharSequence singleCss(String path, String attributes) {
    String uri = addExtensionIfMissing(path, ".css");

    return "<link rel=\"stylesheet\" href=\"" + urlSupplier.apply(uri) + "\"" + attributes + ">";
  }

  private static String addExtensionIfMissing(String uri, String extension) {
    return uri.endsWith(extension) ? uri : uri + extension;
  }
}
