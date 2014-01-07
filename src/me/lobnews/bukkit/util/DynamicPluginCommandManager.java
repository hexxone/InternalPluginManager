/*
 * This file is part of RedstoneWorldManager
 * 
 */
package me.lobnews.bukkit.util;

import java.util.List;
import java.util.logging.Level;

import me.lobnews.minecraft.util.command.Command;
import me.lobnews.util.OutputStream;
import me.lobnews.util.ReflectionUtil;
import me.lobnews.util.command.CommandSyntax;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;

/**
 * 
 * @author Blockhaus2000
 */
public class DynamicPluginCommandManager {
    private final Plugin plugin;
    private final CommandExecutor executor;

    private CommandMap fallbackCommandMap;

    public DynamicPluginCommandManager(final Plugin plugin, final CommandExecutor executor) {
        this.plugin = plugin;
        this.executor = executor;
    }

    public DynamicPluginCommandManager(final Plugin plugin) {
        this(plugin, plugin);
    }

    public boolean registerCommands(List<CommandSyntax> registered) {
        CommandMap commandMap = getCommandMap();

        if (commandMap == null || registered == null || registered.size() == 0) {
            return false;
        }

        for (CommandSyntax target : registered) {
            Command cmdAnot = target.getCommand();

            DynamicPluginCommand cmd = new DynamicPluginCommand(cmdAnot.aliases(), cmdAnot.desc(), cmdAnot.usage(), executor,
                    plugin);
            cmd.setPermission(cmdAnot.permission());
            commandMap.register(plugin.getDescription().getName(), cmd);
        }

        return true;
    }

    private CommandMap getCommandMap() {
        CommandMap commandMap = ReflectionUtil.getField(Bukkit.getServer().getPluginManager(), "commandMap");

        if (commandMap == null) {
            if (fallbackCommandMap != null) {
                OutputStream.log(Level.SEVERE, "Could not retrieve server CommandMap, using fallback instead!");
                Bukkit.getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessListener(commandMap), plugin);
                commandMap = fallbackCommandMap = new SimpleCommandMap(Bukkit.getServer());
            }
        }

        return commandMap;
    }
}
