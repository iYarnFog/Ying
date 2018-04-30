package luna.rabbi2._7dtdcraft;

import luna.rabbi2._7dtdcraft.staticitem.ItemName;
import luna.rabbi2._7dtdcraft.staticitem.Items;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {

        ItemName.init();        
        Items.init();
        System.out.println("Ying Init Success");

        // 自定义合成
        Server server = getServer();
        ShapedRecipe sr_gunpowder = new ShapedRecipe(Items.YItemsMap.get("gunpowder"));
        sr_gunpowder.shape("+++", "+*+", "+++");
        sr_gunpowder.setIngredient('*', Material.SUGAR);
        sr_gunpowder.setIngredient('+', Material.AIR);
        server.addRecipe(sr_gunpowder);
        ShapedRecipe Ysr_gunpowder = new ShapedRecipe(Items.YItemsMap.get("nitrate"));
        Ysr_gunpowder.shape("*++", "+*+", "++*");
        Ysr_gunpowder.setIngredient('*', Material.SUGAR);
        Ysr_gunpowder.setIngredient('+', Material.AIR);
        server.addRecipe(Ysr_gunpowder);
    }
}
