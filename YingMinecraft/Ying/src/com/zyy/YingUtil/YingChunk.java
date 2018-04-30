package com.zyy.YingUtil;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class YingChunk {

    public static void YChunkFix(Player p, World w, int x, int z) {
        w.regenerateChunk(x, z);
        w.refreshChunk(x, z);

        w.unloadChunk(x, z);
        w.loadChunk(x, z);
        p.sendMessage(ChatColor.GRAY + "[YingChunkFixer] Regenerated chunk (" + ChatColor.WHITE + x + ", " + z + ChatColor.GRAY + ")");
        //this.log.info(p.getName() + " regenerated chunk at: " + x + ", " + z + ")");
    }
}
