package com.zyy;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Ying extends JavaPlugin {
    private static Ying Y;

    @Override
    public void onEnable() {
        super.onEnable();
        Y = this;
        for (ChatColor Y: ChatColor.values() ) {
            YingLogger.YInfo(Y.name() + "'" + Y + "颖" + "'");
        }

        try {
            throw new NullPointerException("颖: 测试");
        } catch(Exception ye) {
            ye.printStackTrace();
            ye.getMessage();
        }

        //Y.getConfig().getString()
        getCommand("ping").setExecutor(new YingCommandExecutor());

        getCommand("Ying").setExecutor(new YingCommandExecutor());
        getServer().getPluginManager().registerEvents(new YingListener(), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static Ying getYing() {
        return Y;
    }


}
