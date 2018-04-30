package com.zyy.YingMusic;

import com.zyy.YingLogger;
import com.zyy.YingServer.YingServer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class YingListener implements Listener {


	public YingListener() {

	}

	public static void Ying() {

	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent YEvent) {
		YingMusicCore.Ying(YEvent.getPlayer(), YEvent.getTo());
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent YEvent)
	{
		try {
			JSONObject YJson = new JSONObject();
			YJson.put("Ying", "颖");
			Map<String, String> Y = new HashMap<>();
			Y.put("YingUsername", YEvent.getPlayer().getName());
			Y.put("YingMessage", YEvent.getMessage());
			YJson.put("YingChat", Y);
			YingServer.YWebsocketServer.broadcast(YJson.toJSONString());
		} catch(WebsocketNotConnectedException YException) {
			YingLogger.YError(YException.getMessage());
		}
	}

}
