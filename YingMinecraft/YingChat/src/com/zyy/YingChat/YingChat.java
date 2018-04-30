package com.zyy.YingChat;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class YingChat extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();

        Bukkit.getPluginManager().registerEvents(new YingListener(), this);

    }
}
