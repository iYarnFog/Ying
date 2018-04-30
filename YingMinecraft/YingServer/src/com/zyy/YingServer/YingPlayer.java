package com.zyy.YingServer;

import org.bukkit.entity.Player;
import org.java_websocket.WebSocket;

public class YingPlayer {

    private Player YPlayer;
    private WebSocket YPlayerWebSocket;


    public Player getYPlayer() {
        return YPlayer;
    }

    public void setYPlayer(Player YPlayer) {
        this.YPlayer = YPlayer;
    }

    public WebSocket getYPlayerWebSocket() {
        return YPlayerWebSocket;
    }

    public void setYPlayerWebSocket(WebSocket YPlayerWebSocket) {
        this.YPlayerWebSocket = YPlayerWebSocket;
    }

}
