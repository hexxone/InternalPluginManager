/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.util;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * 
 * @author Blockhaus2000
 */
public class PluginUtil {
    public static void disable(final Plugin plugin, final List<String> messages) {
        Bukkit.getServer().getPluginManager().disablePlugin(plugin);
        sendMessages(messages);
    }

    public static void disable(final Plugin plugin) {
        disable(plugin, null);
    }

    public static void enable(final Plugin plugin, final List<String> messages) {
        Bukkit.getServer().getPluginManager().enablePlugin(plugin);
        sendMessages(messages);
    }

    public static void enable(final Plugin plugin) {
        enable(plugin, null);
    }

    private static void sendMessages(final List<String> messages) {
        if (messages == null) {
            return;
        }

        OutputStream.log(messages);
    }
}
