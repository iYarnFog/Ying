package com.zyy;


import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.Random;

public class Ying extends JavaPlugin {
    private static Ying Y;


    public static int YT = new Random().nextInt();

    @Override
    public void onLoad() {
        super.onLoad();
        Y = this;
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        super.onEnable();

        for (ChatColor Y: ChatColor.values() ) {
            YingLogger.YInfo(Y.name() + "'" + Y + "颖" + "'");
        }

        YingLogger.YInfo(String.valueOf(YT));

        //Y.getConfig().getString()
        getCommand("ping").setExecutor(new YingCommandExecutor());

        getCommand("Ying").setExecutor(new YingCommandExecutor());
        getCommand("Ying").setTabCompleter(new YingTabCompleter());
        getServer().getPluginManager().registerEvents(new YingListener(), this);

         // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts



    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static Ying getYing() {
        return Y;
    }


}
