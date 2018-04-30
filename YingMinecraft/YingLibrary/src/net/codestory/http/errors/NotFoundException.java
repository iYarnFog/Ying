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
package net.codestory.http.errors;

import net.codestory.http.constants.*;

public class NotFoundException extends HttpException {
  public NotFoundException() {
    super(HttpStatus.NOT_FOUND);
  }

  public static <T> T notFoundIfNull(T value) {
    if (value != null) {
      return value;
    }
    throw new NotFoundException();
  }
}
