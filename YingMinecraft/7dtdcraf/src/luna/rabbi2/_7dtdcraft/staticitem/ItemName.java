package luna.rabbi2._7dtdcraft.staticitem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class YItemInfo {
    public String YName;
    public List<String> YLore;
}

public class ItemName {

    public static HashMap<String, YItemInfo> YItemNameMap = new HashMap<>();
    
    private static YItemInfo YItemInfo; // = new HashMap<String, List<String>>();
    
    private static String NITRATE;
    private static List<String> NITRATE_LORE;

    private static String GUNPOWDER;
    private static List<String> GUNPOWDER_LORE;

    public static void init() {

        NITRATE = "§f§l硝酸钾";
        NITRATE_LORE = new ArrayList<>(12);
        NITRATE_LORE.add("");
        NITRATE_LORE.add("  §7§l类别: §5化学");
        NITRATE_LORE.add("  §7§lID: xxx");
        NITRATE_LORE.add("  §7§l毒性: §6有毒(3) - 不可食用");
        NITRATE_LORE.add("");
        NITRATE_LORE.add("     §9硝酸钾是多种产品的原材料,  也可");
        NITRATE_LORE.add("§9以和§c木炭§9、§c硫磺§9混合制成§c黑火药.");
        NITRATE_LORE.add("");
        NITRATE_LORE.add("");
        NITRATE_LORE.add("     §7如果你无法很好地利用硝酸钾就说"); // 15
        NITRATE_LORE.add("§7明你和原始人没什么差别.");
        NITRATE_LORE.add("                   §7—— 贾比尔·伊本·哈扬");
        YItemInfo = new YItemInfo();
        YItemInfo.YName = NITRATE;
        YItemInfo.YLore = NITRATE_LORE;
        YItemNameMap.put("NITRATE", YItemInfo);

        GUNPOWDER = "§4§l黑火药";
        GUNPOWDER_LORE = new ArrayList<>(12);
        GUNPOWDER_LORE.add("");
        GUNPOWDER_LORE.add("  §7§l类别: §e爆炸/混合物");
        GUNPOWDER_LORE.add("  §7§lID: xxx");
        GUNPOWDER_LORE.add("  §7§l毒性: §a微毒(2) - 不可食用");
        GUNPOWDER_LORE.add("");
        GUNPOWDER_LORE.add("     §9黑火药可用于制造");
        GUNPOWDER_LORE.add("");
        GUNPOWDER_LORE.add("");
        GUNPOWDER_LORE.add("");
        GUNPOWDER_LORE.add("");
        GUNPOWDER_LORE.add("");
        GUNPOWDER_LORE.add("");
        GUNPOWDER_LORE.add("     §7中国古代四大发明之一,  公元十三");
        GUNPOWDER_LORE.add("§7世纪由阿拉伯人传入欧洲。 火药的发明");
        GUNPOWDER_LORE.add("§7让兵器产生了划时代的改变,  推动了历");
        GUNPOWDER_LORE.add("§7史的发展.");
        YItemInfo = new YItemInfo();
        YItemInfo.YName = GUNPOWDER;
        YItemInfo.YLore = GUNPOWDER_LORE;
        YItemNameMap.put("GUNPOWDER", YItemInfo);
        
    }
}
