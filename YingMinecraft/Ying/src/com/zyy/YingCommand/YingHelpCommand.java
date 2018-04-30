package com.zyy.YingCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

import java.util.Date;

@YingCommandBase(YingName = "YingHelpCommand", YingCommand = "help", YingDescription = "YingHelp")
public class YingHelpCommand implements CommandExecutor {

    public boolean onCommand(CommandSender YSender, Command YCommand, String YLabel, String[] YArgs) {
        System.out.println(YCommand.getName() + " " + new Date());
        return true;
    }
}
