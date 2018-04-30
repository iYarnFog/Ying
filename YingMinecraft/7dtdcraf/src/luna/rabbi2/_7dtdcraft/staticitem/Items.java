package luna.rabbi2._7dtdcraft.staticitem;

//Java
import java.util.List;
import java.util.HashMap;

//Bukkit
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {
    
    public static HashMap<String, ItemStack> YItemsMap = new HashMap<>();

    private static ItemStack nitrate; // 硝酸盐
    private static ItemStack gunpowder; // 火药


    public static void init() {
        /*for(String YName : ItemName.YItemNameMap.keySet()) {
            System.out.println(YName);
            
        }*/
        
        nitrate = new ItemStack(Material.SUGAR);
        ItemMeta meta_nitrate = nitrate.getItemMeta();
        meta_nitrate.setDisplayName(ItemName.YItemNameMap.get("NITRATE").YName);
        meta_nitrate.setLore(ItemName.YItemNameMap.get("NITRATE").YLore);
        nitrate.setItemMeta(meta_nitrate);
        YItemsMap.put("nitrate", nitrate);
        
        gunpowder = new ItemStack(Material.SULPHUR);
        ItemMeta meta_gunpowder = gunpowder.getItemMeta();   
        meta_nitrate.setDisplayName(ItemName.YItemNameMap.get("GUNPOWDER").YName);
        meta_nitrate.setLore(ItemName.YItemNameMap.get("GUNPOWDER").YLore);
        gunpowder.setItemMeta(meta_gunpowder);
        YItemsMap.put("gunpowder", gunpowder);
    }
}
