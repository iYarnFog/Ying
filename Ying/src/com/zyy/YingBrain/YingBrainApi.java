package com.zyy.YingBrain;

import com.zyy.YingWebsocket.YingServer;
import org.java_websocket.WebSocketImpl;

import java.net.UnknownHostException;

public class YingBrainApi {

    private static YingServer Y = null;

    public static void Ying() {

    }

    public static void YingWebsocket(int YPort) {
        WebSocketImpl.DEBUG  = true;

        try {
            Y = new YingServer( YPort );
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Y.start();
        System.out.println( "ChatServer started on port: " + Y.getPort() );


        /*
        while ( true ) {
            String in = sysin.readLine();
            Y.broadcast( in );
            if( in.equals( "exit" ) ) {
                Y.stop(1000);
                break;
            }
        }
        */

    }

    public static void YingWebsocketBroadcast(String YMessage) {
        if (Y == null) {
            return;
        }
        Y.broadcast(YMessage);
    }


}
