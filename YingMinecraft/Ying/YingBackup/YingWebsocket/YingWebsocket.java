package com.zyy.YingWebsocket;

import com.zyy.Ying;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

public class YingWebsocket extends WebSocketClient {

    public YingWebsocket(URI serverUri , Draft draft ) {
        super( serverUri, draft );
    }

    public YingWebsocket( URI serverURI ) {
        super( serverURI );
    }

    public static void Ying() {

    }

    public static void YSend(String Y) {
        if(Ying.YingWebsocket.getReadyState() == READYSTATE.OPEN) {
            Ying.YingWebsocket.send(Y);
        } else {
            Ying.YingWebsocket.connect();
        }
    }

    @Override
    public void onOpen( ServerHandshake handshakedata ) {
        //System.out.println( "opened connection" );
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }

    @Override
    public void onMessage( String message ) {
        System.out.println( "received: " + message );
    }

    @Override
    public void onClose( int code, String reason, boolean remote ) {
        // The codecodes are documented in class org.java_websocket.framing.CloseFrame
        //System.out.println( "Connection closed by " + ( remote ? "remote peer" : "us" ) + " Code: " + code + " Reason: " + reason );
    }

    @Override
    public void onError( Exception ex ) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }
}



