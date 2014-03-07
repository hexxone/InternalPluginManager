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
package com.blockhaus2000.main.bukkit;

import org.bukkit.plugin.java.JavaPlugin;

import com.blockhaus2000.plugin.SimpleIpmPluginLoader;
import com.blockhaus2000.plugin.SimpleIpmPluginManager;
import com.blockhaus2000.plugin.exception.PluginException;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.PluginUtil;
import com.blockhaus2000.util.resources.MainPluginResource;
import com.blockhaus2000.util.resources.ResourceManager;

/**
 * 
 * @author Blockhaus2000
 */
public class IpmMain extends JavaPlugin {
    private static IpmMain instance;

    // Only for IpmTestPlugin
    // private final IpmPlugin ipmTestPlugin = new IpmTestMain();

    public IpmMain() {
        ResourceManager.getInstance().registerResource(MainPluginResource.class, this);

        if (IpmMain.instance != null) {
            throw new IllegalStateException("Only bukkit initialize a new main plugin!");
        }

        IpmMain.instance = this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDisable() {
        SimpleIpmPluginManager.getInstance().disableAll();
        SimpleIpmPluginManager.getInstance().unregisterAll();

        // Only for IpmTestPlugin
        // ipmTestPlugin.onDisable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnable() {
        try {
            ResourceManager.initializeResources(ExceptionHandler.class);
        } catch (IllegalArgumentException ex) {
            ExceptionHandler.handle(ex);
            PluginUtil.disable(this);
        } catch (IllegalAccessException ex) {
            ExceptionHandler.handle(ex);
            PluginUtil.disable(this);
        }

        try {
            SimpleIpmPluginManager.getInstance().register(SimpleIpmPluginLoader.getInstance().loadAll());
        } catch (PluginException ex) {
            ExceptionHandler.handle(ex);
        }

        SimpleIpmPluginManager.getInstance().enableAll();

        // Only for IpmTestPlugin
        // ipmTestPlugin.onEnable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLoad() {
        // Only for IpmTestPlugin
        // ipmTestPlugin.onLoad();
    }

    /**
     * Returns you an instance from the {@link IpmMain} class.
     * 
     * @deprecated Use the annotation {@link MainPluginResource} and call
     *             {@link ResourceManager#initializeResources(Object)} instead!
     * @return An instance from the {@link IpmMain} class.
     */
    @Deprecated
    public static IpmMain getInstance() {
        if (IpmMain.instance == null) {
            return IpmMain.instance = new IpmMain();
        }

        return IpmMain.instance;
    }
}
