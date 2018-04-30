package com.zyy.YingLibrary;

import com.zyy.Ying;
import com.zyy.YingLogger;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Random;

public class YingLibrary extends JavaPlugin {

    public static Ying Ying = null;
    private static YingLibrary Y;
    private static String YPrefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "YingLibrary" + ChatColor.GRAY + "]" + ChatColor.RESET;

    @Override
    public void onEnable() {
        super.onEnable();
        Y = this;
        Ying = (Ying)getServer().getPluginManager().getPlugin("Ying");



        YingLogger.YInfo(String.valueOf(YPrefix + Ying.YT));




    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static YingLibrary getYing() {
        return Y;
    }


}
