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
 *  package com.blockhaus2000.bukkit.util;
 */
package com.blockhaus2000.util;

import org.bukkit.plugin.Plugin;

import com.blockhaus2000.bukkit.util.DynamicPluginCommandManager;
import com.blockhaus2000.minecraft.util.command.CommandManager;

/**
 * 
 * @author Blockhaus2000
 */
public class CommandRegistrationUtil {
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
