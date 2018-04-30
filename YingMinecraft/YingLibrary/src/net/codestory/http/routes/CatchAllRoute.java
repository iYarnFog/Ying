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
package net.codestory.http.routes;

import net.codestory.http.*;

class CatchAllRoute implements Route {
  private final String method;
  private final AnyRoute route;

  CatchAllRoute(AnyRoute route) {
    this("ANY", route);
  }

  CatchAllRoute(String method, AnyRoute route) {
    this.method = method;
    this.route = route;
  }

  public String getMethod() {
    return method;
  }

  @Override
  public boolean matchUri(String uri) {
    return true;
  }

  @Override
  public boolean matchMethod(String method) {
    return ("ANY".equals(this.method)) || (method.equals(this.method));
  }

  @Override
  public Object body(Context context) throws Exception {
    return route.body(context);
  }
}
