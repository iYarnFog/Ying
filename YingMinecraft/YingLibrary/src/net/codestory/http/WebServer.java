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

import net.codestory.http.filters.log.LogRequestFilter;
import net.codestory.http.internal.Handler;
import net.codestory.http.internal.HttpServerWrapper;
import net.codestory.http.internal.SimpleServerWrapper;
import net.codestory.http.websockets.WebSocketHandler;

import java.io.File;

public class WebServer extends AbstractWebServer<WebServer> {

  public static int YPort = 6040;
  public static File YPath = new File(".");

  protected int threadCount = 8;
  protected int selectThreads = 1;
  protected int webSocketThreads = 10;

  public static void main(String[] args) {
    WebServer Y = new WebServer()
      .configure(routes -> routes.filter(new LogRequestFilter())
        .get("/Ying", "YingYing"))
      .start(6040);

    Y.env = Y.env.withWorkingDir(new File("Z:\\YingServer\\Ying\\Ying\\plugins\\Ying"));

      System.out.println(Y.env.workingDir().getAbsolutePath());

      //""-javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2017.2.3\lib\idea_rt.jar=3398:D:\Program Files\JetBrains\IntelliJ IDEA 2017.2.3\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_111\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_111\jre\lib\rt.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\out\production\YingLibrary;Z:\YingServer\Ying\spigot-api.jar;Z:\YingServer\Ying\spigot.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\Ying\out\artifacts\Ying_jar\Ying.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\jna-4.2.1.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\simple-http-6.0.1.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\simple-common-6.0.1.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\simple-transport-6.0.1.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\less4j-1.17.2.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\antlr-runtime-3.5.2.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\commons-beanutils-1.8.3.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\gson-2.5.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\protobuf-java-2.5.0.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\handlebars-2.2.3.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\antlr4-runtime-4.5.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\rhino-1.7R4.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\markdown4j-2.2-cj-1.1.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\snakeyaml-1.17.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\fast-classpath-scanner-1.9.0.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\jackson-databind-2.8.5.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\jackson-core-2.8.5.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\jackson-datatype-jsr310-2.8.5.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\jackson-annotations-2.8.5.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\slf4j-api-1.7.21.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\webjars-locator-0.32.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\webjars-locator-core-0.30.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\commons-compress-1.9.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\coffee-script-1.11.0.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\commons-io-2.5.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\commons-lang3-3.5.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\guava-20.0.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\commons-logging-1.2.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\javax.inject-1.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\aopalliance-1.0.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\spring-context-4.3.4.RELEASE.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\spring-aop-4.3.4.RELEASE.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\spring-beans-4.3.4.RELEASE.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\spring-core-4.3.4.RELEASE.jar;C:\Users\Administrator\IdeaProjects\YingMinecraft\YingLibrary\YingLibrary\spring-expression-4.3.4.RELEASE.jar" net.codestory.http.WebServer


  }

  public static WebServer Ying() {
    WebServer Y = new WebServer()
            .configure(routes -> routes.filter(new LogRequestFilter())
                    .get("/Ying", "YingYing"))
            .start(YPort);

    System.out.println(Y.env.workingDir().getAbsolutePath());

    return Y;
  }

  public WebServer withThreadCount(int threadCount) {
    this.threadCount = threadCount;
    return this;
  }

  public WebServer withSelectThreads(int selectThreads) {
    this.selectThreads = selectThreads;
    return this;
  }

  public WebServer withWebSocketThreads(int webSocketThreads) {
    this.webSocketThreads = webSocketThreads;
    return this;
  }

  @Override
  protected HttpServerWrapper createHttpServer(Handler httpHandler, WebSocketHandler webSocketHandler) {
    return new SimpleServerWrapper(httpHandler, webSocketHandler, threadCount, selectThreads, webSocketThreads);
  }
}
