package com.zyy.YingUtil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.ChatMessageType;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class YingAction {

    private static String YVersion = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

    public static void Ying() {

    }

    public static void sendActionBar(Player YPlayer, String YMessage)
    {
        YMessage = ChatColor.translateAlternateColorCodes('&', YMessage);
        try
        {
            if (YVersion.equals("v1_12_R1"))
            {
                IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + YMessage + "\"}");
                PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(cbc, ChatMessageType.GAME_INFO);
                ((CraftPlayer)YPlayer).getHandle().playerConnection.sendPacket(packetPlayOutChat);
            }
            else if ((!YVersion.equalsIgnoreCase("v1_8_R1")) && (!YVersion.contains("v1_7_")))
            {
                Class<?> c1 = Class.forName("org.bukkit.craftbukkit." + YVersion + ".entity.CraftPlayer");
                Object p = c1.cast(YPlayer);

                Class<?> c4 = Class.forName("net.minecraft.server." + YVersion + ".PacketPlayOutChat");
                Class<?> c5 = Class.forName("net.minecraft.server." + YVersion + ".Packet");

                Class<?> c2 = Class.forName("net.minecraft.server." + YVersion + ".ChatComponentText");
                Class<?> c3 = Class.forName("net.minecraft.server." + YVersion + ".IChatBaseComponent");
                Object o = c2.getConstructor(new Class[] { String.class }).newInstance(new Object[] { YMessage });
                Object ppoc = c4.getConstructor(new Class[] { c3, Byte.TYPE }).newInstance(new Object[] { o, 2 });

                Method getHandle = c1.getDeclaredMethod("getHandle", new Class[0]);
                Object handle = getHandle.invoke(p, new Object[0]);

                Field fieldConnection = handle.getClass().getDeclaredField("playerConnection");
                Object playerConnection = fieldConnection.get(handle);

                Method sendPacket = playerConnection.getClass().getDeclaredMethod("sendPacket", new Class[] { c5 });
                sendPacket.invoke(playerConnection, new Object[] { ppoc });
            }
            else
            {
                Class<?> c1 = Class.forName("org.bukkit.craftbukkit." + YVersion + ".entity.CraftPlayer");
                Object p = c1.cast(YPlayer);

                Class<?> c4 = Class.forName("net.minecraft.server." + YVersion + ".PacketPlayOutChat");
                Class<?> c5 = Class.forName("net.minecraft.server." + YVersion + ".Packet");

                Class<?> c2 = Class.forName("net.minecraft.server." + YVersion + ".ChatSerializer");
                Class<?> c3 = Class.forName("net.minecraft.server." + YVersion + ".IChatBaseComponent");
                Method m3 = c2.getDeclaredMethod("a", new Class[] { String.class });
                Object cbc = c3.cast(m3.invoke(c2, new Object[] { "{\"text\": \"" + YMessage + "\"}" }));
                Object ppoc = c4.getConstructor(new Class[] { c3, Byte.TYPE }).newInstance(new Object[] { cbc, 2 });

                Method getHandle = c1.getDeclaredMethod("getHandle", new Class[0]);
                Object handle = getHandle.invoke(p, new Object[0]);

                Field fieldConnection = handle.getClass().getDeclaredField("playerConnection");
                Object playerConnection = fieldConnection.get(handle);

                Method sendPacket = playerConnection.getClass().getDeclaredMethod("sendPacket", new Class[] { c5 });
                sendPacket.invoke(playerConnection, new Object[] { ppoc });
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}


