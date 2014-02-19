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
package com.blockhaus2000.main.bukkit;

import org.bukkit.plugin.java.JavaPlugin;

import com.blockhaus2000.plugin.RwmPlugin;
import com.blockhaus2000.plugin.RwmPluginManager;
import com.blockhaus2000.util.CommandUtil;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.PluginUtil;
import com.blockhaus2000.util.resources.MainPluginResource;
import com.blockhaus2000.util.resources.ResourceManager;

/**
 * 
 * @author Blockhaus2000
 */
public class Main extends JavaPlugin {
    private static Main instance;

    public Main() {
        if (instance != null) {
            throw new IllegalStateException("Only bukkit initialize a new main plugin!");
        }

        instance = this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDisable() {
        RwmPluginManager.getInstance().unregisterAllPlugins();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnable() {
        try {
            ResourceManager.initializeResources(ExceptionHandler.class);
            ResourceManager.initializeResources(RwmPluginManager.getInstance());
        } catch (IllegalArgumentException ex) {
            ExceptionHandler.handle(ex);
            PluginUtil.disable(this);
        } catch (IllegalAccessException ex) {
            ExceptionHandler.handle(ex);
            PluginUtil.disable(this);
        }

        RwmPluginManager.getInstance().registerAllPlugins();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLoad() {
        new RwmPlugin() {
            // Nothing to do (only to load the class)
        };
    }

    public void registerCommands(final Class<?> clazz, final Object obj) {
        CommandUtil.registerCommands(clazz, obj, this);
    }

    public void registerCommands(final Class<?> clazz) {
        registerCommands(clazz, null);
    }

    public void registerCommands(final Object obj) {
        registerCommands(obj.getClass(), obj);
    }

    /**
     * Returns you an instance from the {@link Main} class.
     * 
     * @deprecated Use the annotation {@link MainPluginResource} and call
     *             {@link ResourceManager#initializeResources(Object)} instead!
     * @return An instance from the {@link Main} class.
     */
    @Deprecated
    public static Main getInstance() {
        if (instance == null) {
            return instance = new Main();
        }

        return instance;
    }
}
