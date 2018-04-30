package com.zyy;

import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.List;


import com.zyy.YingCommand.YingCommandBase;
import com.zyy.YingCommand.YingCommandManager;
import com.zyy.YingNMS.YingCompatibility;
import com.zyy.YingNMS.YingTellraw.ClickEventType;
import com.zyy.YingNMS.YingTellraw.MessageComponent;
import com.zyy.YingNMS.YingTellraw.MessageParts;
import com.zyy.YingUtil.YingChunk;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import org.bukkit.Bukkit;
import org.bukkit.World;

import com.google.common.collect.Lists;

public class YingCommandExecutor implements CommandExecutor {

	public YingCommandExecutor() {

	}

	public static void Ying() {

	}
  
	@Override
	public boolean onCommand(CommandSender YSender, Command YCommand, String YLabel, String[] YArgs) {
		Ying.getYing().getServer().getConsoleSender().sendMessage(YCommand.getName());
		Ying.getYing().saveDefaultConfig();

		try {
			YingCommandManager.getClasses("com.zyy.YingCommand").forEach((Y) -> {
                if(Y.isAnnotationPresent(YingCommandBase.class)){
					YingCommandBase YY = Y.getAnnotation(YingCommandBase.class);
                	if(YArgs[0].equalsIgnoreCase(YY.YingCommand())) {
						System.out.println(YY.YingName());
						try {
							CommandExecutor YCommandExecutor = ((CommandExecutor) Y.newInstance());
							//com.zyy.YingCommand.YingHelpCommand.onCommand();
							YCommandExecutor.onCommand(YSender, YCommand, YLabel, YArgs);
							//YY.getMethod("onCommand").invoke(null, null, "Ying", null);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return;
					}
                }
            });
		} catch (Exception e) {
			e.printStackTrace();
		}

		Player YPlayer = (Player) YSender;

		if(YCommand.getName().equalsIgnoreCase("Ying")) {
			//判断是否是玩家
			if(!(YSender instanceof Player)){
				YSender.sendMessage("只能在游戏里执行此命令！");
				//return true;
			}
			//判断是否有权限
			if(!YSender.hasPermission("xxx.use")){
				YSender.sendMessage("你没有权限。");
				//return true;
			}
			YingCompatibility.sendActionBar(YPlayer, YCommand.getName());
			if(YArgs == null) {
				return false;
			}
			if (YArgs[0].equalsIgnoreCase("YingWindow")) {
				new YingWindow().Ying();
				YingLogger.YDebug("Success Open YingWindow");
			}
			if (YArgs[0].equalsIgnoreCase("YingChunk")) {
				YingChunk.YChunkFix(YPlayer, YPlayer.getWorld(), (int)YPlayer.getLocation().getX(), (int)YPlayer.getLocation().getY());
			}
			if (YArgs[0].equalsIgnoreCase("Ying")) {

				MessageComponent msg = new MessageComponent();
				msg.addText("自殺ボタンはこちら→");
				MessageParts button = new MessageParts("[あぼーん]", ChatColor.BLUE);
				button.setClickEvent(ClickEventType.RUN_COMMAND, "/kill");
				button.setHoverText("押しても後悔してはならない\n絶対に後悔してはならない", ChatColor.GOLD);
				msg.addParts(button);
				msg.addText(" 押すなよ！絶対に押すなよ！！", ChatColor.RED);

				try {
					YingCompatibility.YingChatPacket(YPlayer, msg.YingBuild(), (byte) 1);
				} catch (ReflectiveOperationException e) {
					e.printStackTrace();
				}

			}
		}

		return true;
	}
	
}
