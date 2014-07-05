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
package com.blockhaus2000.bukkit.util;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;

import com.blockhaus2000.main.bukkit.IpmMain;
import com.blockhaus2000.minecraft.util.command.SimpleCommandManager;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.resources.MainPluginResource;
import com.blockhaus2000.util.resources.ResourceManager;

/**
 * Provides some static methods to create a new
 * {@link DynamicPluginCommandManager}.
 *
 * @author Blockhaus2000
 */
public class DynamicPluginCommandManagerFactory {
    @MainPluginResource
    private static IpmMain main;

    private DynamicPluginCommandManagerFactory() {
        // Utility classes should not have a public, protected or visible
        // constructor.
    }

    static {
        try {
            // We want use the resource management system, not the deprecated
            // getInstance() method of the IpmMain class.
            ResourceManager.initializeResources(DynamicPluginCommandManagerFactory.class);
        } catch (IllegalArgumentException ex) {
            ExceptionHandler.handle(ex);
        } catch (IllegalAccessException ex) {
            ExceptionHandler.handle(ex);
        }
    }

    /**
     * Instances a new {@link DynamicPluginCommandManager}.
     *
     * <p>
     * The <code>@SuppressWarnings("deprecation")</code> is to suprress the
     * waring of the usage of the deprecated constructor of the
     * {@link SimpleDynamicPluginCommandManager}.
     * </p>
     *
     * @param plugin
     *            The command-holding {@link Plugin}.
     * @param executor
     *            The {@link CommandExecutor} that executes the commands that
     *            will be registered.
     * @return A new {@link DynamicPluginCommandManager}.
     */
    @SuppressWarnings("deprecation")
    public static DynamicPluginCommandManager newInstance(final Plugin plugin, final CommandExecutor executor) {
        return new SimpleDynamicPluginCommandManager(plugin, executor);
    }

    /**
     * Instances a new {@link DynamicPluginCommandManager}. Will call
     * {@link DynamicPluginCommandManagerFactory#newInstance(Plugin, CommandExecutor)}
     * the given {@link Plugin} and <code>executor</code> as the command system
     * or, if <code>useCommandSystem == false</code>, with the given
     * {@link Plugin} as the {@link CommandExecutor}.
     *
     * @param plugin
     *            The command-holding {@link Plugin}.
     * @param useCommandSystem
     *            If <code>true</code>, the command system will be used.
     *            Otherwise, the plugin will be the {@link CommandExecutor}.
     * @return A new {@link DynamicPluginCommandManager}.
     * @see com.blockhaus2000.bukkit.util.DynamicPluginCommandManagerFactory#newInstance(org.bukkit.plugin.Plugin,
     *      org.bukkit.command.CommandExecutor)
     */
    public static DynamicPluginCommandManager newInstance(final Plugin plugin, final boolean useCommandSystem) {
        return DynamicPluginCommandManagerFactory.newInstance(plugin, useCommandSystem ? SimpleCommandManager.getInstance()
                : plugin);
    }

    /**
     * Instances a new {@link DynamicPluginCommandManager}. Will call
     * {@link DynamicPluginCommandManagerFactory#newInstance(Plugin, boolean)}
     * with <code>useCommandSystem = true</code> and the given Plugin.
     *
     * @param plugin
     *            The command-holding {@link Plugin}.
     * @return A new {@link DynamicPluginCommandManager}.
     * @see com.blockhaus2000.bukkit.util.DynamicPluginCommandManagerFactory#newInstance(org.bukkit.plugin.Plugin,
     *      boolean)
     */
    public static DynamicPluginCommandManager newInstance(final Plugin plugin) {
        return DynamicPluginCommandManagerFactory.newInstance(plugin, true);
    }

    /**
     * Instances a new {@link DynamicPluginCommandManager}. Will calling
     * {@link DynamicPluginCommandManagerFactory#newInstance(Plugin, CommandExecutor)}
     * with <code>plugin</code> as the main plugin and the given
     * {@link CommandExecutor}.
     *
     * @param executor
     *            The {@link CommandExecutor} that executes the commands.
     * @return A new {@link DynamicPluginCommandManager}.
     * @see com.blockhaus2000.bukkit.util.DynamicPluginCommandManagerFactory#newInstance(org.bukkit.plugin.Plugin,
     *      org.bukkit.command.CommandExecutor)
     */
    public static DynamicPluginCommandManager newInstance(final CommandExecutor executor) {
        return DynamicPluginCommandManagerFactory.newInstance(DynamicPluginCommandManagerFactory.main, executor);
    }

    /**
     * Instances a new {@link DynamicPluginCommandManager}. Will calling
     * {@link DynamicPluginCommandManagerFactory#newInstance(Plugin, CommandExecutor)}
     * with <code>plugin</code> as the main plugin and <code>executor</code> as
     * the command system executor.
     *
     * @return A new {@link DynamicPluginCommandManager}.
     * @see com.blockhaus2000.bukkit.util.DynamicPluginCommandManagerFactory#newInstance(org.bukkit.plugin.Plugin,
     *      org.bukkit.command.CommandExecutor)
     */
    public static DynamicPluginCommandManager newInstance() {
        return DynamicPluginCommandManagerFactory.newInstance(DynamicPluginCommandManagerFactory.main,
                SimpleCommandManager.getInstance());
    }
}
