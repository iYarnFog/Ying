package com.zyy.YingWindow;

import com.teamdev.jxbrowser.chromium.*;
import com.teamdev.jxbrowser.chromium.events.ConsoleEvent;
import com.teamdev.jxbrowser.chromium.events.ConsoleListener;
import com.teamdev.jxbrowser.chromium.events.ScriptContextAdapter;
import com.teamdev.jxbrowser.chromium.events.ScriptContextEvent;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.net.URL;

public class YingWindow {

    static {
        try {
            Field e = az.class.getDeclaredField("e");
            e.setAccessible(true);
            Field f = az.class.getDeclaredField("f");
            f.setAccessible(true);
            Field modifersField = Field.class.getDeclaredField("modifiers");
            modifersField.setAccessible(true);
            modifersField.setInt(e, e.getModifiers() & ~Modifier.FINAL);
            modifersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
            e.set(null, new BigInteger("1"));
            f.set(null, new BigInteger("1"));
            modifersField.setAccessible(false);
        } catch (Exception ye) {
            ye.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);

        JFrame frame = new JFrame();
        frame.add(view, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setTitle("YingWindow");
        //frame.set
        frame.setResizable(false);
        frame.setVisible(true);

        BrowserContext browserContext = browser.getContext();
        ProtocolService protocolService = browserContext.getProtocolService();
        protocolService.setProtocolHandler("jar", new ProtocolHandler() {
            @Override
            public URLResponse onRequest(URLRequest request) {
                try {
                    URLResponse response = new URLResponse();
                    URL path = new URL(request.getURL());
                    InputStream inputStream = path.openStream();
                    DataInputStream stream = new DataInputStream(inputStream);
                    byte[] data = new byte[stream.available()];
                    stream.readFully(data);
                    response.setData(data);
                    String mimeType = getMimeType(path.toString());
                    response.getHeaders().setHeader("Content-Type", mimeType);
                    return response;
                } catch (Exception ignored) {}
                return null;
            }
        });
        browser.loadURL("http://www.spgril.com");

        //browser.loadHTML("Ying<script>window.Ying.iYing();</script>");

        browser.addScriptContextListener(new ScriptContextAdapter() {
            @Override
            public void onScriptContextCreated(ScriptContextEvent YEvent) {
                Browser YBrowser = YEvent.getBrowser();
                JSValue YWindow = YBrowser.executeJavaScriptAndReturnValue("window");
                YWindow.asObject().setProperty("Ying", new YingApi());
            }
        });

        browser.addConsoleListener(new ConsoleListener() {
            @Override
            public void onMessage(ConsoleEvent consoleEvent) {
                System.out.println("Ying > " + consoleEvent.getMessage());
            }
        });
    }

}
