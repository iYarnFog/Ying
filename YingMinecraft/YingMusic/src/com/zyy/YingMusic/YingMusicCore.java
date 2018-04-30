package com.zyy.YingMusic;

import com.zyy.YingUtil.YingAction;
import com.zyy.YingUtil.YingPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import java.util.Map;
import java.util.HashMap;

public class YingMusicCore {

    private static Map<String, String> YPlayerList = new HashMap();

    public static void Ying(Player YPlayer, Location YLocation) {
        //com.zyy.YingMusicCore.YingMusicCore;
        Map<String, Integer> YMap = new HashMap();
        YMap.put("Y-X-1", 0);
        YMap.put("Y-Y-1", 0);
        YMap.put("Y-Z-1", 0);
        YMap.put("Y-X-2", 10);
        YMap.put("Y-Y-2", 10);
        YMap.put("Y-Z-2", 10);
        Map<String, Integer> YYLocation = new HashMap();
        YYLocation.put("X", new Double(YLocation.getX()).intValue());
        YYLocation.put("Y", new Double(YLocation.getY()).intValue());
        YYLocation.put("Z", new Double(YLocation.getZ()).intValue());
        //YLogger.YingMusic("Map Elements");
        //YLogger.YingMusic("\t" + YMap);
        //YLogger.YingMusic("\t" + YYLocation);
        if (chuck(YMap, YYLocation)) {
            if (!YPlayerList.containsKey(YPlayer.getName())) {
                YPlayerList.put(YPlayer.getName(), "颖");
                YPlayer.sendMessage(ChatColor.AQUA + "你进入了区块颖");

                YingAction.sendActionBar(YPlayer, ChatColor.AQUA + "你进入了区块颖");
                //ParticleEffect.CLOUD.send(YPlayer, YPlayer.getLocation().getX(), );
                YingPlayer.YSetPlayerMeta(YPlayer, "YingChunk", "颖");
                //YingMetadata.setMetaValue("YingChunk", YPlayer, "颖");

                //YingLogger.YInfo(YingMetadata.getMetaValue(String.class, "YingChunk", YPlayer));

                //YingLogger.YInfo(YingPlayer.YGetPlayerMeta(YPlayer, "YingChunk").toString());

                //YingParticleEffect.CLOUD.sendToPlayer();
                //List YList = new ArrayList<String>();
                //YList.add("music");
                //YList.add("play");
                //YList.add(YConfig.YingMusic().getString("YingMusic.MusicList.YingMusic.src"));
                //YSocketc.send(YJson.YingMusic(YPlayer.getName(), "", YList));

                JSONObject YJson = new JSONObject();
                YJson.put("Ying", "颖");
                Map Y = new HashMap<>();
                Y.put("YingUsername", YPlayer.getName());
                Y.put("YingMusicName", "岑宁儿 - 追光者" );
                Y.put("YingMusicSrc", "YingMusic.mp3");
                Y.put("YingLyricSrc", "YingMusic.lrc");
                YJson.put("YingMusic", Y);

                YingMusic.YServer.YWebsocketServer.broadcast(YJson.toJSONString());
            }
        } else if (YPlayerList.containsKey(YPlayer.getName())) {
            YPlayerList.remove(YPlayer.getName());
            YPlayer.sendMessage(ChatColor.YELLOW + "你离开了区块颖");
            YingAction.sendActionBar(YPlayer, ChatColor.YELLOW + "你离开了区块颖");
        }
    }

    private static boolean chuck(Map YMap, Map YLocation) {
        boolean YX = false;
        boolean YY = false;
        boolean YZ = false;
        if (YInt(YMap.get("Y-X-1")) >= YInt(YMap.get("Y-X-2"))) {
            if ((YInt(YLocation.get("X")) < YInt(YMap.get("Y-X-1"))) && (YInt(YLocation.get("X")) > YInt(YMap.get("Y-X-2")))) {
                YX = true;
            }
        } else if ((YInt(YLocation.get("X")) > YInt(YMap.get("Y-X-1"))) && (YInt(YLocation.get("X")) < YInt(YMap.get("Y-X-2")))) {
            YX = true;
        }

        if (YInt(YMap.get("Y-Y-1")) >= YInt(YMap.get("Y-Y-2"))) {
            if ((YInt(YLocation.get("Y")) < YInt(YMap.get("Y-Y-1"))) && (YInt(YLocation.get("Y")) > YInt(YMap.get("Y-Y-2")))) {
                YY = true;
            }
        } else if ((YInt(YLocation.get("Y")) > YInt(YMap.get("Y-Y-1"))) && (YInt(YLocation.get("Y")) < YInt(YMap.get("Y-Y-2")))) {
            YY = true;
        }

        if (YInt(YMap.get("Y-Z-1")) >= YInt(YMap.get("Y-Z-2"))) {
            if ((YInt(YLocation.get("Z")) < YInt(YMap.get("Y-Z-1"))) && (YInt(YLocation.get("Z")) > YInt(YMap.get("Y-Z-2")))) {
                YZ = true;
            }
        } else if ((YInt(YLocation.get("Z")) > YInt(YMap.get("Y-Z-1"))) && (YInt(YLocation.get("Z")) < YInt(YMap.get("Y-Z-2")))) {
            YZ = true;
        }
        return (YX) && (YY) && (YZ);
    }

    private static int YInt(Object Ying) {
        return Integer.parseInt(String.valueOf(Ying));
    }

}
