package com.zyy;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class YingCommandExecutor implements CommandExecutor {

	public YingCommandExecutor() {

	}
  
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Ying.getYing().getServer().getConsoleSender().sendMessage(cmd.getName());
		Ying.getYing().saveDefaultConfig();

		if(cmd.getName().equalsIgnoreCase("Ying")) {
			if (args[0].equalsIgnoreCase("YingWindow")) {
				YingLogger.YDebug("Success Open YingWindow");
			}
			new YingWindow().Ying();
		}

		return true;
	}
	
}
