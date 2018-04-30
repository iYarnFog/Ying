package com.zyy;


import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.zyy.YingUtil.YingPluginManager;
import org.bukkit.util.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class YingTabCompleter implements TabCompleter {
    private static final String[] COMMANDS = new String[]{"check", "disable", "dump", "enable", "help", "info", "list", "load", "lookup", "reload", "restart", "unload", "usage"};

    public YingTabCompleter() {
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp() && !sender.hasPermission("plugman.admin") && !sender.hasPermission("plugman." + args[0])) {
            return null;
        } else {
            List<String> completions = new ArrayList();
            String partialPlugin;
            if (args.length == 1) {
                partialPlugin = args[0];
                List<String> commands = new ArrayList(Arrays.asList(COMMANDS));
                StringUtil.copyPartialMatches(partialPlugin, commands, completions);
            }

            if (args.length == 2) {
                partialPlugin = args[1];
                List<String> plugins = YingPluginManager.getPluginNames(false);
                StringUtil.copyPartialMatches(partialPlugin, plugins, completions);
            }

            Collections.sort(completions);
            return completions;
        }
    }
}

