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
package net.codestory.http.io;

public class Strings {
  private Strings() {
    // Static utility class
  }

  public static int countMatches(CharSequence in, char what) {
    int count = 0;

    for (int i = 0, n = in.length(); i < n; i++) {
      if (what == in.charAt(i)) {
        count++;
      }
    }

    return count;
  }

  public static String substringBeforeLast(String in, String what) {
    int index = in.lastIndexOf(what);
    if (index == -1) {
      return in;
    }
    return in.substring(0, index);
  }

  public static String substringAfter(String in, String what) {
    int index = in.lastIndexOf(what);
    if (index == -1) {
      return "";
    }
    return in.substring(index + what.length());
  }

  public static String stripQuotes(String text) {
    if (text == null) {
      return null;
    }
    if (text.length() <= 1) {
      return text;
    }
    if ((text.charAt(0) == '"') && (text.charAt(text.length() - 1) == '"')) {
      return text.substring(1, text.length() - 1);
    }
    return text;
  }

  public static String replaceLast(String in, String what, String replaceWith) {
    int index = in.lastIndexOf(what);
    if (index == -1) {
      return in;
    }
    return in.substring(0, index) + replaceWith;
  }

  public static String extension(String uri) {
    int dotIndex = uri.lastIndexOf('.');
    if (dotIndex <= 0) {
      return "";
    }
    return uri.substring(dotIndex);
  }
}
