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

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Blockhaus2000
 */
public class SimpleIpmPluginManager implements IpmPluginManager {
    private static final IpmPluginManager instance = new SimpleIpmPluginManager();

    private final Set<IpmPlugin> plugins = new HashSet<IpmPlugin>();

    private SimpleIpmPluginManager() {
        // nothing to do (only to provide singleton pattern)
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPluginManager#getPlugin(java.lang.String)
     */
    @Override
    public IpmPlugin getPlugin(final String pluginName) {
        for (IpmPlugin target : plugins) {
            if (target.getDescription().getName().equalsIgnoreCase(pluginName)) {
                return target;
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPluginManager#disable(com.blockhaus2000.plugin.IpmPlugin)
     */
    @Override
    public void disable(final IpmPlugin plugin) {
        plugin.setEnabled(false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPluginManager#disable(java.util.Set)
     */
    @Override
    public void disable(final Set<IpmPlugin> plugins) {
        for (IpmPlugin target : plugins) {
            disable(target);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPluginManager#disableAll()
     */
    @Override
    public void disableAll() {
        disable(plugins);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPluginManager#enable(com.blockhaus2000.plugin.IpmPlugin)
     */
    @Override
    public void enable(final IpmPlugin plugin) {
        plugin.setEnabled(true);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPluginManager#enable(java.util.Set)
     */
    @Override
    public void enable(final Set<IpmPlugin> plugins) {
        for (IpmPlugin target : plugins) {
            enable(target);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPluginManager#enableAll()
     */
    @Override
    public void enableAll() {
        enable(plugins);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPluginManager#unregister(com.blockhaus2000.plugin.IpmPlugin)
     */
    @Override
    public void unregister(final IpmPlugin plugin) {
        plugins.remove(plugin.getDescription().getName().toLowerCase());
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPluginManager#unregister(java.util.Set)
     */
    @Override
    public void unregister(final Set<IpmPlugin> plugins) {
        for (IpmPlugin target : plugins) {
            unregister(target);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPluginManager#unregisterAll()
     */
    @Override
    public void unregisterAll() {
        unregister(plugins);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPluginManager#register(com.blockhaus2000.plugin.IpmPlugin)
     */
    @Override
    public void register(final IpmPlugin plugin) {
        plugins.add(plugin);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPluginManager#register(java.util.Set)
     */
    @Override
    public void register(final Set<IpmPlugin> plugins) {
        for (IpmPlugin target : plugins) {
            register(target);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.blockhaus2000.plugin.IpmPluginManager#getPlugins()
     */
    @Override
    public Set<IpmPlugin> getPlugins() {
        return plugins;
    }

    /**
     * Will provide singleton.
     * 
     * @return An instance of {@link SimpleIpmServer}.
     */
    public static IpmPluginManager getInstance() {
        return SimpleIpmPluginManager.instance;
    }
}
