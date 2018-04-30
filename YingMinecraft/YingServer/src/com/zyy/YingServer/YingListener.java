package com.zyy.YingServer;

import java.util.HashMap;
import java.net.InetAddress;
import java.util.Map;

import com.zyy.YingLogger;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import org.bukkit.event.player.PlayerQuitEvent;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.json.simple.JSONObject;



public class YingListener implements Listener {


	public YingListener() {

	}

	public static void Ying() {

	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent YEvent)
	{
		InetAddress YAddress = YEvent.getPlayer().getAddress().getAddress();
		System.out.println(YingServer.YPlayerList);
		if(YingServer.YPlayerList.containsKey(YAddress)) {
			YingPlayer YPlayer = YingServer.YPlayerList.get(YAddress);
			YPlayer.setYPlayer(YEvent.getPlayer());

			JSONObject YJson = new JSONObject();
			YJson.put("Ying", "颖");
			Map Y = new HashMap<>();
			Y.put("YingUsername", YEvent.getPlayer().getName());
			YJson.put("YingUserinfo", Y);

			YPlayer.getYPlayerWebSocket().send(YJson.toJSONString());
		} else {
			YingPlayer YPlayer = new YingPlayer();

			YPlayer.setYPlayer(YEvent.getPlayer());
			YingServer.YPlayerList.put(YAddress, YPlayer);

		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent YEvent) {
		if(YingServer.YPlayerList.get(YEvent.getPlayer().getAddress().getAddress()).getYPlayerWebSocket().isOpen()/*!YEvent.getPlayer().isOnline()*/) {
			YingServer.YPlayerList.remove(YEvent.getPlayer().getAddress().getAddress());
		}
	}



}
