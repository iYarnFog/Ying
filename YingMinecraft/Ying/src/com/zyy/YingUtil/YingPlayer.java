package com.zyy.YingUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.bukkit.entity.Player;

public class YingPlayer {
    private static List<Player> YPlayerList = new ArrayList();

    public static void Ying() {

    }

    public static void YSetPlayerMeta(Player YPlayer, Object YKey, Object YValue) {
        if(YPlayerList.contains(YPlayer)) {

            Map YPalyerMeta = YingMetadata.getMetaValue(Map.class, "Ying", YPlayer);
            /*
            if(YPalyerMeta.containsKey(YKey)) {
                YPalyerMeta.replace(YKey, YValue);
            } else {
                YPalyerMeta.put(YKey, YValue);
            }
            */
            YPalyerMeta.put(YKey, YValue);
            YingMetadata.setMetaValue("Ying", YPlayer, YPalyerMeta);

        } else {

            YPlayerList.add(YPlayer);
            Map YPalyerMeta = new HashMap();
            YPalyerMeta.put(YKey, YValue);
            YingMetadata.setMetaValue("Ying", YPlayer, YPalyerMeta);

        }
    }

    public static Object YGetPlayerMeta(Player YPlayer, Object YKey) {
        Map YPalyerMeta = YingMetadata.getMetaValue(Map.class, "Ying", YPlayer);
        if(YPalyerMeta.containsKey(YKey)) {
            return YPalyerMeta.get(YKey);
        }
        return null;
    }


}
