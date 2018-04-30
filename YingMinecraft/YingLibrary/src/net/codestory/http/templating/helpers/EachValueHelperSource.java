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

import java.io.*;
import java.util.*;

import com.github.jknack.handlebars.*;

public class EachValueHelperSource {
  public CharSequence each_value(Object context, Object param, Options options) throws IOException {
    if (context == null) {
      return "";
    }

    return (param instanceof Iterable<?>)
      ? iterableContext(context, (Iterable<?>) param, options)
      : hashContext(context, param, options);
  }

  private CharSequence hashContext(Object context, Object param, Options options) throws IOException {
    StringBuilder buffer = new StringBuilder();

    Set<Map.Entry<String, Object>> keys = options.propertySet(param);

    Context parent = options.context;
    for (Map.Entry<String, Object> key : keys) {
      Context current = Context
        .newContext(parent, options.wrap(context).get(key.getKey()))
        .data("value", key.getValue())
        .data("key", key.getKey());
      buffer.append(options.fn(current));
    }

    return buffer;
  }

  private CharSequence iterableContext(Object context, Iterable<?> keys, Options options) throws IOException {
    StringBuilder buffer = new StringBuilder();

    Context parent = options.context;
    for (Object key : keys) {
      Context current = Context
        .newContext(parent, options.wrap(context).get(key.toString()))
        .data("key", key);
      buffer.append(options.fn(current));
    }

    return buffer;
  }
}
