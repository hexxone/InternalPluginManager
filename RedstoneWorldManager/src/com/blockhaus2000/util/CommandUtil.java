/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.util;

import org.bukkit.plugin.Plugin;

import com.blockhaus2000.bukkit.util.DynamicPluginCommandManager;
import com.blockhaus2000.minecraft.util.command.CommandManager;

/**
 * 
 * @author Blockhaus2000
 */
public class CommandUtil {
    public static void registerCommands(final Class<?> clazz, final Object obj, final Plugin plugin) {
        new DynamicPluginCommandManager(plugin, CommandManager.getInstance()).registerCommands(CommandManager.getInstance()
                .register(clazz, obj));
    }

    public static void registerCommands(final Class<?> clazz, final Plugin plugin) {
        registerCommands(clazz, null, plugin);
    }

    public static void registerCommands(final Object obj, final Plugin plugin) {
        registerCommands(obj.getClass(), obj, plugin);
    }
}
