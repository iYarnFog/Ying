package com.zyy.YingServer;

/*
 * Copyright (c) 2010-2017 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */


import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.zyy.Ying;
import com.zyy.YingLogger;

import org.bukkit.entity.Player;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.simple.JSONObject;

/**
 * A simple WebSocketServer implementation. Keeps track of a "chatroom".
 */
public class YingWebSocketServer extends WebSocketServer {

    public YingWebSocketServer(int port ) throws UnknownHostException {
        super( new InetSocketAddress( port ) );
    }

    public YingWebSocketServer(InetSocketAddress address ) {
        super( address );
    }

    @Override
    public void onOpen( WebSocket conn, ClientHandshake handshake ) {
        InetAddress YAddress = conn.getRemoteSocketAddress().getAddress();
        broadcast( "new connection: " + handshake.getResourceDescriptor() );
        YingLogger.YInfo(  YAddress.getHostAddress() + " entered the room!" );
        System.out.println(YingServer.YPlayerList);
        if(YingServer.YPlayerList.containsKey(YAddress)) {
            Player YPlayer = YingServer.YPlayerList.get(YAddress).getYPlayer();

            JSONObject YJson = new JSONObject();
            YJson.put("Ying", "颖");
            Map Y = new HashMap<>();
            Y.put("YingUsername", YPlayer.getName());
            YJson.put("YingUserinfo", Y);
            conn.send(YJson.toJSONString());
        } else {
            YingPlayer YPlayer = new YingPlayer();
            YPlayer.setYPlayerWebSocket(conn);
            YingServer.YPlayerList.put(YAddress, YPlayer);
        }
    }

    @Override
    public void onClose( WebSocket conn, int code, String reason, boolean remote ) {
        broadcast( conn + " has left the room!" );
        //YingServer.YPlayerList.remove(conn.getRemoteSocketAddress().getAddress());
        YingLogger.YInfo(YingServer.YWebSocketPrefix + conn + " has left the room!" );

        if(YingServer.YPlayerList.get(conn.getRemoteSocketAddress().getAddress()).getYPlayer().isOnline()) {
            YingLogger.YDebug("颖: Remove");
            YingServer.YPlayerList.remove(conn.getRemoteSocketAddress().getAddress());
        }

    }

    @Override
    public void onMessage( WebSocket conn, String message ) {
        broadcast( message );
        YingLogger.YInfo( YingServer.YWebSocketPrefix + conn + ": " + message );
    }
    @Override
    public void onMessage( WebSocket conn, ByteBuffer message ) {
        broadcast( message.array() );
        YingLogger.YInfo( YingServer.YWebSocketPrefix + conn + ": " + message );
    }

    @Override
    public void onError( WebSocket conn, Exception ex ) {
        ex.printStackTrace();
        if( conn != null ) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    @Override
    public void onStart() {
        YingLogger.YInfo(YingServer.YWebSocketPrefix + "Server started!");
    }

}

