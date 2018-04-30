package com.zyy;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

import static org.bukkit.Bukkit.getServer;

public class YingLogger
{
    private static ConsoleCommandSender Y = getServer().getConsoleSender();

    private static String YPrefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "颖" + ChatColor.GRAY + "]" + ChatColor.RESET;

    YingLogger() {

    }

    public static void Ying() {

    }

    public static void YInfo(String YMessage)
    {
        Y.sendMessage(YPrefix + YMessage);
    }

    public static void YWarn(String YMessage)
    {
        Y.sendMessage(YPrefix + ChatColor.YELLOW + YMessage);
    }

    public static void YError(String YMessage)
    {
        Y.sendMessage(YPrefix + ChatColor.RED + YMessage);
    }

    public static void YDebug(String YMessage)
    {
        Y.sendMessage(YPrefix + ChatColor.WHITE + YMessage);
    }

}
