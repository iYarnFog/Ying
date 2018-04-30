package com.zyy.YingUtil;

import com.google.common.base.Joiner;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLClassLoader;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.zyy.Ying;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.event.Event;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredListener;

public class YingPluginManager {
    public static void enable(Plugin plugin)
    {
        if ((plugin != null) && (!plugin.isEnabled())) {
            Bukkit.getPluginManager().enablePlugin(plugin);
        }
    }

    public static void enableAll()
    {
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            if (!isIgnored(plugin)) {
                enable(plugin);
            }
        }
    }

    public static void disable(Plugin plugin)
    {
        if ((plugin != null) && (plugin.isEnabled())) {
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }

    public static void disableAll()
    {
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            if (!isIgnored(plugin)) {
                disable(plugin);
            }
        }
    }

    public static String getFormattedName(Plugin plugin)
    {
        return getFormattedName(plugin, false);
    }

    public static String getFormattedName(Plugin plugin, boolean includeVersions)
    {
        ChatColor color = plugin.isEnabled() ? ChatColor.GREEN : ChatColor.RED;
        String pluginName = color + plugin.getName();
        if (includeVersions) {
            pluginName = pluginName + " (" + plugin.getDescription().getVersion() + ")";
        }
        return pluginName;
    }

    public static Plugin getPluginByName(String[] args, int start)
    {
        return null;//getPluginByName(StringUtil.consolidateStrings(args, start));
    }

    public static Plugin getPluginByName(String name)
    {
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            if (name.equalsIgnoreCase(plugin.getName())) {
                return plugin;
            }
        }
        return null;
    }

    public static List<String> getPluginNames(boolean fullName)
    {
        List<String> plugins = new ArrayList();
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            plugins.add(fullName ? plugin.getDescription().getFullName() : plugin.getName());
        }
        return plugins;
    }

    public static String getPluginVersion(String name)
    {
        Plugin plugin = getPluginByName(name);
        if ((plugin != null) && (plugin.getDescription() != null)) {
            return plugin.getDescription().getVersion();
        }
        return null;
    }

    public static String getUsages(Plugin plugin)
    {
        List<String> parsedCommands = new ArrayList();

        Map commands = plugin.getDescription().getCommands();
        if (commands != null)
        {
            Iterator commandsIt = commands.entrySet().iterator();
            while (commandsIt.hasNext())
            {
                Map.Entry thisEntry = (Map.Entry)commandsIt.next();
                if (thisEntry != null) {
                    parsedCommands.add((String)thisEntry.getKey());
                }
            }
        }
        if (parsedCommands.isEmpty()) {
            return "No commands registered.";
        }
        return Joiner.on(", ").join(parsedCommands);
    }

    public static List<String> findByCommand(String command)
    {
        List<String> plugins = new ArrayList();
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins())
        {
            Map<String, Map<String, Object>> commands = plugin.getDescription().getCommands();
            if (commands != null)
            {
                Iterator<Map.Entry<String, Map<String, Object>>> commandIterator = commands.entrySet().iterator();
                while (commandIterator.hasNext())
                {
                    Map.Entry<String, Map<String, Object>> commandNext = (Map.Entry)commandIterator.next();
                    if (((String)commandNext.getKey()).equalsIgnoreCase(command))
                    {
                        plugins.add(plugin.getName());
                    }
                    else
                    {
                        Iterator<Map.Entry<String, Object>> attributeIterator = ((Map)commandNext.getValue()).entrySet().iterator();
                        while (attributeIterator.hasNext())
                        {
                            Map.Entry<String, Object> attributeNext = (Map.Entry)attributeIterator.next();
                            if (((String)attributeNext.getKey()).equals("aliases"))
                            {
                                Object aliases = attributeNext.getValue();
                                if ((aliases instanceof String))
                                {
                                    if (((String)aliases).equalsIgnoreCase(command)) {
                                        plugins.add(plugin.getName());
                                    }
                                }
                                else
                                {
                                    List<String> array = (List)aliases;
                                    for (String str : array) {
                                        if (str.equalsIgnoreCase(command)) {
                                            plugins.add(plugin.getName());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return plugins;
    }

    public static boolean isIgnored(Plugin plugin)
    {
        return isIgnored(plugin.getName());
    }

    public static boolean isIgnored(String plugin)
    {
        List<String> YList = new ArrayList<String>();
        YList.add("Ying");
        for (String name : YList) {
            if (name.equalsIgnoreCase(plugin)) {
                return true;
            }
        }
        return false;
    }

    private static String load(Plugin plugin)
    {
        return load(plugin.getName());
    }

    public static String load(String name)
    {
        Plugin target = null;

        File pluginDir = new File("plugins");
        if (!pluginDir.isDirectory()) {
            return null;//PlugMan.getInstance().getMessageFormatter().format("load.plugin-directory", new Object[0]);
        }
        File pluginFile = new File(pluginDir, name + ".jar");
        if (!pluginFile.isFile()) {
            for (File f : pluginDir.listFiles()) {
                if (f.getName().endsWith(".jar")) {
                    try
                    {
                        PluginDescriptionFile desc = Ying.getYing().getPluginLoader().getPluginDescription(f);
                        if (desc.getName().equalsIgnoreCase(name))
                        {
                            pluginFile = f;
                            break;
                        }
                    }
                    catch (InvalidDescriptionException e)
                    {
                        return null;//PlugMan.getInstance().getMessageFormatter().format("load.cannot-find", new Object[0]);
                    }
                }
            }
        }
        try
        {
            target = Bukkit.getPluginManager().loadPlugin(pluginFile);
        }
        catch (InvalidDescriptionException e)
        {
            e.printStackTrace();
            return null;//PlugMan.getInstance().getMessageFormatter().format("load.invalid-description", new Object[0]);
        }
        catch (InvalidPluginException e)
        {
            e.printStackTrace();
            return null;//PlugMan.getInstance().getMessageFormatter().format("load.invalid-plugin", new Object[0]);
        }
        target.onLoad();
        Bukkit.getPluginManager().enablePlugin(target);

        return null;//PlugMan.getInstance().getMessageFormatter().format("load.loaded", new Object[] { target.getName() });
    }

    public static void reload(Plugin plugin)
    {
        if (plugin != null)
        {
            unload(plugin);
            load(plugin);
        }
    }

    public static void reloadAll()
    {
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            if (!isIgnored(plugin)) {
                reload(plugin);
            }
        }
    }

    public static String unload(Plugin plugin)
    {
        String name = plugin.getName();

        PluginManager pluginManager = Bukkit.getPluginManager();

        SimpleCommandMap commandMap = null;

        List<Plugin> plugins = null;

        Map<String, Plugin> names = null;
        Map<String, Command> commands = null;
        Map<Event, SortedSet<RegisteredListener>> listeners = null;

        boolean reloadlisteners = true;
        if (pluginManager != null)
        {
            pluginManager.disablePlugin(plugin);
            try
            {
                Field pluginsField = Bukkit.getPluginManager().getClass().getDeclaredField("plugins");
                pluginsField.setAccessible(true);
                plugins = (List)pluginsField.get(pluginManager);

                Field lookupNamesField = Bukkit.getPluginManager().getClass().getDeclaredField("lookupNames");
                lookupNamesField.setAccessible(true);
                names = (Map)lookupNamesField.get(pluginManager);
                try
                {
                    Field listenersField = Bukkit.getPluginManager().getClass().getDeclaredField("listeners");
                    listenersField.setAccessible(true);
                    listeners = (Map)listenersField.get(pluginManager);
                }
                catch (Exception e)
                {
                    reloadlisteners = false;
                }
                Field commandMapField = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
                commandMapField.setAccessible(true);
                commandMap = (SimpleCommandMap)commandMapField.get(pluginManager);

                Field knownCommandsField = SimpleCommandMap.class.getDeclaredField("knownCommands");
                knownCommandsField.setAccessible(true);
                commands = (Map)knownCommandsField.get(commandMap);
            }
            catch (NoSuchFieldException e)
            {
                e.printStackTrace();
                return null;//PlugMan.getInstance().getMessageFormatter().format("unload.failed", new Object[] { name });
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
                return null;//PlugMan.getInstance().getMessageFormatter().format("unload.failed", new Object[] { name });
            }
        }
        pluginManager.disablePlugin(plugin);
        if ((plugins != null) && (plugins.contains(plugin))) {
            plugins.remove(plugin);
        }
        if ((names != null) && (names.containsKey(name))) {
            names.remove(name);
        }

        Iterator it;
        if ((listeners != null) && (reloadlisteners)) {
            for (SortedSet<RegisteredListener> set : listeners.values()) {
                for (it = set.iterator(); it.hasNext();)
                {
                    RegisteredListener value = (RegisteredListener)it.next();
                    if (value.getPlugin() == plugin) {
                        it.remove();
                    }
                }
            }
        }

        //Iterator<Map.Entry<String, Command>> it;
        if (commandMap != null) {
            for (it = commands.entrySet().iterator(); it.hasNext();)
            {
                Map.Entry<String, Command> entry = (Map.Entry)it.next();
                if ((entry.getValue() instanceof PluginCommand))
                {
                    PluginCommand c = (PluginCommand)entry.getValue();
                    if (c.getPlugin() == plugin)
                    {
                        c.unregister(commandMap);
                        it.remove();
                    }
                }
            }
        }
        ClassLoader cl = plugin.getClass().getClassLoader();
        if ((cl instanceof URLClassLoader))
        {
            try
            {
                Field pluginField = cl.getClass().getDeclaredField("plugin");
                pluginField.setAccessible(true);
                pluginField.set(cl, null);

                Field pluginInitField = cl.getClass().getDeclaredField("pluginInit");
                pluginInitField.setAccessible(true);
                pluginInitField.set(cl, null);
            }
            catch (NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException ex)
            {
                Logger.getLogger(YingPluginManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            try
            {
                ((URLClassLoader)cl).close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(YingPluginManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.gc();

        return null;//PlugMan.getInstance().getMessageFormatter().format("unload.unloaded", new Object[] { name });
    }
}
