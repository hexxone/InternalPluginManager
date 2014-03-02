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
 * 
 * @author Blockhaus2000
 */
public class DynamicPluginCommandManagerFactory {
    @MainPluginResource
    private static IpmMain main;

    static {
        try {
            ResourceManager.initializeResources(DynamicPluginCommandManagerFactory.class);
        } catch (IllegalArgumentException ex) {
            ExceptionHandler.handle(ex);
        } catch (IllegalAccessException ex) {
            ExceptionHandler.handle(ex);
        }
    }

    public static DynamicPluginCommandManager newInstance(final Plugin plugin, final CommandExecutor executor) {
        return new SimpleDynamicPluginCommandManager(plugin, executor);
    }

    public static DynamicPluginCommandManager newInstance(final Plugin plugin, final boolean useCommandSystem) {
        return DynamicPluginCommandManagerFactory.newInstance(plugin, useCommandSystem ? SimpleCommandManager.getInstance()
                : plugin);
    }

    public static DynamicPluginCommandManager newInstance(final Plugin plugin) {
        return DynamicPluginCommandManagerFactory.newInstance(plugin, true);
    }

    public static DynamicPluginCommandManager newInstance(final CommandExecutor executor) {
        return DynamicPluginCommandManagerFactory.newInstance(DynamicPluginCommandManagerFactory.main, executor);
    }

    public static DynamicPluginCommandManager newInstance() {
        return DynamicPluginCommandManagerFactory.newInstance(DynamicPluginCommandManagerFactory.main,
                SimpleCommandManager.getInstance());
    }
}
