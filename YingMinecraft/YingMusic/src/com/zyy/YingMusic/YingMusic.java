package com.zyy.YingMusic;


import com.zyy.Ying;
import com.zyy.YingServer.YingServer;
import com.zyy.YingServer.YingWebSocketServer;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

public class YingMusic extends JavaPlugin {
    private static YingMusic Y;

    protected  static Ying Ying = null;
    protected static YingServer YServer = null;

    @Override
    public void onLoad() {
        super.onLoad();
        Y = this;

    }

    @Override
    public void onEnable() {
        super.onEnable();

        getServer().getPluginManager().registerEvents(new YingListener(), this);

         // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
        Ying = (Ying) getServer().getPluginManager().getPlugin("Ying");
        YServer = (YingServer) getServer().getPluginManager().getPlugin("YingServer");

        YamlConfiguration YYaml = new YamlConfiguration();
        try {
            YYaml.load(new File(Ying.getDataFolder().getAbsolutePath() + "YingMusic.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        Map YMusicList = (Map) YYaml.getMapList("Ying.YingMusic");
        if (YMusicList == null) {
            YYaml.set("Ying.YingMusic", null);
        }
        YYaml.save();

        ;
        System.out.println();

        //YYaml.

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static YingMusic getYing() {
        return Y;
    }


}
