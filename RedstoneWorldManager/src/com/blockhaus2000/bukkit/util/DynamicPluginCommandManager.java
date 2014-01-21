/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.bukkit.util;

import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;

import com.blockhaus2000.minecraft.util.command.Command;
import com.blockhaus2000.util.OutputStream;
import com.blockhaus2000.util.ReflectionUtil;
import com.blockhaus2000.util.command.CommandInfo;

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

    public boolean registerCommands(List<CommandInfo> registered) {
        CommandMap commandMap = getCommandMap();

        if (commandMap == null || registered == null || registered.size() == 0) {
            return false;
        }

        for (CommandInfo target : registered) {
            Command cmdAnot = target.getCommandAnot();

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
                commandMap = fallbackCommandMap = new SimpleCommandMap(Bukkit.getServer());
                Bukkit.getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessListener(commandMap), plugin);
            }
        }

        return commandMap;
    }
}
