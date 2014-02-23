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
package com.blockhaus2000.plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Blockhaus2000
 */
public class SimpleIpmPluginManager implements IpmPluginManager {
    private static final IpmPluginManager instance = new SimpleIpmPluginManager();

    private final Map<String, IpmPlugin> plugins = new HashMap<String, IpmPlugin>();

    private SimpleIpmPluginManager() {
        // nothing to do (only to provide singleton pattern)
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPluginManager#getPlugin(java.lang.String)
     */
    @Override
    public IpmPlugin getPlugin(final String pluginName) {
        return plugins.get(pluginName);
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPluginManager#disable(com.blockhaus2000.plugin.IpmPlugin)
     */
    @Override
    public void disable(final IpmPlugin plugin) {
        plugin.setEnabled(false);
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPluginManager#enable(com.blockhaus2000.plugin.IpmPlugin)
     */
    @Override
    public void enable(final IpmPlugin plugin) {
        plugin.setEnabled(true);
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPluginManager#unregister(com.blockhaus2000.plugin.IpmPlugin)
     */
    @Override
    public void unregister(final IpmPlugin plugin) {
        plugins.remove(plugin.getDescription().getName().toLowerCase());
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPluginManager#register(com.blockhaus2000.plugin.IpmPlugin)
     */
    @Override
    public void register(final IpmPlugin plugin, final IpmPluginDescription description) {
        plugin.init(description);

        plugins.put(plugin.getDescription().getName().toLowerCase(), plugin);
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPluginManager#getPlugins()
     */
    @Override
    public Map<String, IpmPlugin> getPlugins() {
        return plugins;
    }

    public static IpmPluginManager getInstance() {
        return SimpleIpmPluginManager.instance;
    }
}
