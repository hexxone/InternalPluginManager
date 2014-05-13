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

import org.bukkit.plugin.Plugin;

import com.blockhaus2000.bukkit.util.DynamicPluginCommandManagerFactory;
import com.blockhaus2000.minecraft.util.command.SimpleCommandManager;

/**
 * This class is an util class to facilitate the command registration.
 *
 * @author Blockhaus2000
 */
public class CommandRegistrationUtil {
    /**
     * Registers all commands in the given {@link Class} with the given
     * {@link Object} for the given {@link Plugin}.
     *
     * @param clazz
     *            The {@link Class} where the commands are placed.
     * @param obj
     *            An {@link Object} of the {@link Class} where the commands are
     *            placed (only for non-static methods, can be <code>null</code>
     *            ).
     * @param plugin
     *            The {@link Plugin} that is associated with the commands.
     * @see com.blockhaus2000.minecraft.util.command.CommandManager#register(Class,
     *      Object)
     * @see com.blockhaus2000.bukkit.util.DynamicPluginCommandManager#register(java.util.Collection)
     */
    public static void registerCommands(final Class<?> clazz, final Object obj, final Plugin plugin) {
        DynamicPluginCommandManagerFactory.newInstance(plugin).register(SimpleCommandManager.getInstance().register(clazz, obj));
    }

    /**
     * Registers commands. Will call
     * {@link CommandRegistrationUtil#registerCommands(Class, Object, Plugin)}
     * with <code>obj = null</code>.
     *
     * @param clazz
     *            The {@link Class} where the commands are placed.
     * @param plugin
     *            The {@link Plugin} that is associated with the commands.
     * @see com.blockhaus2000.util.CommandRegistrationUtil#registerCommands(java.lang.Class,
     *      java.lang.Object, org.bukkit.plugin.Plugin)
     */
    public static void registerCommands(final Class<?> clazz, final Plugin plugin) {
        CommandRegistrationUtil.registerCommands(clazz, null, plugin);
    }

    /**
     * Registers commands. Will call
     * {@link CommandRegistrationUtil#registerCommands(Class, Object, Plugin)}
     * with the {@link Class} of the given {@link Object}.
     *
     * @param obj
     *            The {@link Object} where the commands are placed.
     * @param plugin
     *            The {@link Plugin} that is associated with the commands.
     * @see com.blockhaus2000.util.CommandRegistrationUtil#registerCommands(java.lang.Class,
     *      java.lang.Object, org.bukkit.plugin.Plugin)
     */
    public static void registerCommands(final Object obj, final Plugin plugin) {
        CommandRegistrationUtil.registerCommands(obj.getClass(), obj, plugin);
    }
}
