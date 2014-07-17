/* This file is part of InternalPluginManager
 *
 * Copyright 2014 Blockhaus2000
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  see the License for the specific language governing permissions and
 *  Limitations under the License.
 */
package com.blockhaus2000.util;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * This class is a utility class for easy disableing and enabling of Bukkit (!)
 * plugins.
 *
 */
public final class PluginUtil {
    private PluginUtil() {
        // Utility classes should not have a public, protected or visible
        // constructor.
    }

    /**
     * Disables the given {@link Plugin} and sends the given messages. The
     * messages are send with the usage of
     * {@link ChatOut#log(java.util.Collection)}. Will call
     * {@link PluginManager#disablePlugin(org.bukkit.plugin.Plugin)}
     *
     * @param plugin
     *            The plugin that has to be disabled.
     * @param messages
     *            The messages that has to be send.
     * @see com.blockhaus2000.util.ChatOut#log(java.util.Collection)
     * @see org.bukkit.plugin.PluginManager#disablePlugin(org.bukkit.plugin.Plugin)
     */
    public static void disable(final Plugin plugin, final Collection<String> messages) {
        Bukkit.getServer().getPluginManager().disablePlugin(plugin);
        PluginUtil.sendMessages(messages);
    }

    /**
     * Disables the given plugin. Will call
     * {@link PluginUtil#disable(Plugin, Collection)} with
     * <code>messages = null</code>.
     *
     * @param plugin
     *            The plugin that has to be disabled.
     * @see com.blockhaus2000.util.PluginUtil#disable(org.bukkit.plugin.Plugin,
     *      java.util.Collection)
     */
    public static void disable(final Plugin plugin) {
        PluginUtil.disable(plugin, null);
    }

    /**
     * Enables the given {@link Plugin} and sends the given messages. The
     * messages are send with the usage of
     * {@link ChatOut#log(java.util.Collection)}. Will call
     * {@link PluginManager#enablePlugin(org.bukkit.plugin.Plugin)}
     *
     * @param plugin
     *            The plugin that has to be disabled.
     * @param messages
     *            The messages that has to be send.
     * @see com.blockhaus2000.util.ChatOut#log(java.util.Collection)
     * @see org.bukkit.plugin.PluginManager#enablePlugin(org.bukkit.plugin.Plugin)
     */
    public static void enable(final Plugin plugin, final Collection<String> messages) {
        Bukkit.getServer().getPluginManager().enablePlugin(plugin);
        PluginUtil.sendMessages(messages);
    }

    /**
     * Enables the given plugin. Will call
     * {@link PluginUtil#enable(Plugin, Collection)} with
     * <code>messages = null</code>.
     *
     * @param plugin
     *            The plugin that has to be disabled.
     * @see com.blockhaus2000.util.PluginUtil#enable(org.bukkit.plugin.Plugin,
     *      java.util.Collection)
     */
    public static void enable(final Plugin plugin) {
        PluginUtil.enable(plugin, null);
    }

    private static void sendMessages(final Collection<String> messages) {
        if (messages == null) {
            return;
        }

        ChatOut.log(messages);
    }
}
