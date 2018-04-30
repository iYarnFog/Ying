package com.zyy.YingServer;


import com.zyy.YingLogger;
import net.codestory.http.WebServer;
import net.codestory.http.YingConfig;
import net.codestory.http.internal.Handler;
import net.codestory.http.AbstractWebServer;
import net.codestory.http.internal.HttpServerWrapper;
import net.codestory.http.internal.SimpleServerWrapper;
import net.codestory.http.websockets.WebSocketHandler;
import net.codestory.http.filters.log.LogRequestFilter;

import java.io.File;

public class YingWebServer extends AbstractWebServer<YingWebServer> {

    protected int threadCount = 8;
    protected int selectThreads = 1;
    protected int webSocketThreads = 10;

    public static YingWebServer Ying() {
        YingWebServer Y = new YingWebServer()
                .configure(routes -> routes.filter(new LogRequestFilter())
                        .get("/Ying", "YingYing"))
                .start(6040);

        //YingConfig.YFile = YingServer.Ying.getDataFolder();
        YingLogger.YInfo(YingServer.YWebPrefix + Y.env.workingDir().getAbsolutePath());

        return Y;
    }


    public YingWebServer withThreadCount(int threadCount) {
        this.threadCount = threadCount;
        return this;
    }

    public YingWebServer withSelectThreads(int selectThreads) {
        this.selectThreads = selectThreads;
        return this;
    }

    public YingWebServer withWebSocketThreads(int webSocketThreads) {
        this.webSocketThreads = webSocketThreads;
        return this;
    }

    @Override
    protected HttpServerWrapper createHttpServer(Handler httpHandler, WebSocketHandler webSocketHandler) {
        return new SimpleServerWrapper(httpHandler, webSocketHandler, threadCount, selectThreads, webSocketThreads);
    }
}
