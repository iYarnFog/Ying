package com.zyy;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.java_websocket.exceptions.WebsocketNotConnectedException;

public class YingListener implements Listener {


	public YingListener() {

	}

	public static void Ying() {

	}



	@EventHandler
	public void onPlayerMove(PlayerMoveEvent YEvent) {
		//YingMusic.Ying(YEvent.getPlayer(), YEvent.getTo());
	}
}
