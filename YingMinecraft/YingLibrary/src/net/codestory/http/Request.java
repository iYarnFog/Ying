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
package net.codestory.http;

import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import net.codestory.http.convert.TypeConvert;
import net.codestory.http.cors.CORSHelper;
import net.codestory.http.cors.CORSRequestType;
import net.codestory.http.internal.*;
import net.codestory.http.io.InputStreams;

import static java.util.stream.Collectors.toMap;
import static net.codestory.http.constants.Headers.X_FORWARDED_FOR;

public interface Request extends Unwrappable {
  String uri();

  String method();

  String content() throws IOException;

  String contentType();

  List<String> headerNames();

  List<String> headers(String name);

  String header(String name);

  InputStream inputStream() throws IOException;

  InetSocketAddress clientAddress();

  boolean isSecure();

  Cookies cookies();

  Query query();

  List<Part> parts();

  // Content to Bean

  default boolean isUrlEncodedForm() {
    String contentType = header("Content-Type");
    return (contentType != null) && (contentType.contains("application/x-www-form-urlencoded"));
  }

  default byte[] contentAsBytes() throws IOException {
    return InputStreams.readBytes(inputStream());
  }

  default <T> T contentAs(Class<T> type) throws IOException {
    if (isUrlEncodedForm()) {
      return TypeConvert.convertValue(query().keyValues(), type);
    }
    return TypeConvert.fromJson(content(), type);
  }

  @SuppressWarnings("unchecked")
  default <T> T contentAs(Type type) throws IOException {
    if (isUrlEncodedForm()) {
      return (T) TypeConvert.convertValue(query().keyValues(), type);
    }
    return TypeConvert.fromJson(content(), type);
  }

  default <T> T contentAs(TypeReference<T> type) throws IOException {
    if (isUrlEncodedForm()) {
      return TypeConvert.convertValue(query().keyValues(), type);
    }
    return TypeConvert.fromJson(content(), type);
  }

  // Headers helpers

  default String header(String name, String defaultValue) {
    return Optional.ofNullable(header(name)).orElse(defaultValue);
  }

  default Map<String, List<String>> headers() {
    return headerNames().stream().collect(toMap(name -> name, this::headers));
  }

  // Support forwarding

  default String clientAddressForwarded() {
    String forwarded = header(X_FORWARDED_FOR);
    return (forwarded != null) ? forwarded : clientAddress().toString();
  }

  // CORS

  default CORSRequestType corsRequestType() {
    return CORSHelper.corsRequestType(this);
  }

  default boolean isCORS() {
    return corsRequestType().isCORS();
  }

  default boolean isPreflight() {
    return corsRequestType().isPreflight();
  }
}
