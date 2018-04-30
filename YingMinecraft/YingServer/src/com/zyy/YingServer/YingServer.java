package com.zyy.YingServer;

import java.util.Map;
import java.util.HashMap;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import com.zyy.Ying;


import com.zyy.YingLogger;
import org.bukkit.ChatColor;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class YingServer extends JavaPlugin {
    public static Ying Ying = null;
    private static YingServer Y;
    public static YingWebSocketServer YWebsocketServer = null;
    public static YingWebServer YWebServer = null;

    public static String YWebPrefix = ChatColor.GRAY + "[" + ChatColor.GREEN + "YingWebServer" + ChatColor.GRAY + "]" + ChatColor.RESET;
    public static String YWebSocketPrefix = ChatColor.GRAY + "[" + ChatColor.GREEN + "YingWebSocketServer" + ChatColor.GRAY + "]" + ChatColor.RESET;


    public static Map<InetAddress, YingPlayer> YPlayerList = new HashMap<>();

    @Override
    public void onEnable() {
        super.onEnable();
        Y = this;
        Ying = (Ying)getServer().getPluginManager().getPlugin("Ying");

        if(true) {

             //YWebServer = YingWebServer.Ying();

            YWebServer = YingWebServer.Ying();

        }

        if(true) {

            InetSocketAddress YAddress = new InetSocketAddress("zyy.com", 6041);
            try {
                YWebsocketServer = new YingWebSocketServer(6041);
                YWebsocketServer.start();
                YingLogger.YInfo(YWebSocketPrefix + "WebSocketServer started on port: " + YWebsocketServer.getPort());
            } catch(Exception ye) {
                ye.printStackTrace();
            }
                /*BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
                while ( true ) {
                    String in = sysin.readLine();
                    s.broadcast( in );
                    if( in.equals( "exit" ) ) {
                        s.stop(1000);
                        break;
                    }
                }*/

        }

        getServer().getPluginManager().registerEvents(new YingListener(), this);



        new BukkitRunnable() {
            @Override
            public void run() {
                YPlayerList.forEach((YK, YV) -> {
                    YingLogger.YDebug(YWebSocketPrefix + YK + "颖" + YV.getYPlayer().isOnline() + "颖" + YV.getYPlayerWebSocket().isOpen());
                });
            }
        }.runTaskAsynchronously(this);



    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static YingServer getYing() {
        return Y;
    }


}
