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
package com.blockhaus2000.bukkit.mock;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.RegisteredListener;

import com.blockhaus2000.main.bukkit.IpmMain;
import com.blockhaus2000.plugin.IpmPlugin;
import com.blockhaus2000.plugin.IpmPluginManager;
import com.blockhaus2000.plugin.SimpleIpmPluginLoader;
import com.blockhaus2000.plugin.SimpleIpmPluginManager;
import com.blockhaus2000.plugin.exception.PluginException;

/**
 * This class is a mock implementation of {@link PluginLoader}.
 *
 */
public class MockPluginLoader implements PluginLoader {
    private static final PluginLoader instance = new MockPluginLoader();

    private MockPluginLoader() {
        // nothing to do (only to provide singleton)
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.plugin.PluginLoader#loadPlugin(java.io.File)
     * @see com.blockhaus2000.plugin.IpmPluginLoader#load(java.io.File)
     */
    @Override
    public Plugin loadPlugin(final File file) {
        try {
            return SimpleIpmPluginLoader.getInstance().load(file);
        } catch (PluginException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @return <code>null</code>
     * @see org.bukkit.plugin.PluginLoader#getPluginDescription(java.io.File)
     */
    @Override
    public PluginDescriptionFile getPluginDescription(final File file) throws InvalidDescriptionException {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> This is a mock implementation! </b>
     * </p>
     *
     * @return <code>null</code>
     * @see org.bukkit.plugin.PluginLoader#getPluginFileFilters()
     */
    @Override
    public Pattern[] getPluginFileFilters() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> This is a mock implementation! </b>
     * </p>
     *
     * @return <code>null</code>
     * @see org.bukkit.plugin.PluginLoader#createRegisteredListeners(org.bukkit.event.Listener,
     *      org.bukkit.plugin.Plugin)
     */
    @SuppressWarnings("deprecation")
    @Override
    public Map<Class<? extends Event>, Set<RegisteredListener>> createRegisteredListeners(final Listener listener,
            final Plugin plugin) {
        return IpmMain.getInstance().getPluginLoader().createRegisteredListeners(listener, plugin);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.plugin.PluginLoader#enablePlugin(org.bukkit.plugin.Plugin)
     * @see com.blockhaus2000.plugin.IpmPluginManager#enable(com.blockhaus2000.plugin.IpmPlugin)
     */
    @Override
    public void enablePlugin(final Plugin plugin) {
        SimpleIpmPluginManager.getInstance().enable(SimpleIpmPluginManager.getInstance().getPlugin(plugin.getName()));
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This mock implementation and will call
     * {@link IpmPluginManager#disable(IpmPlugin)}. </b>
     * </p>
     *
     * @see org.bukkit.plugin.PluginLoader#disablePlugin(org.bukkit.plugin.Plugin)
     * @see com.blockhaus2000.plugin.IpmPluginManager#disable(com.blockhaus2000.plugin.IpmPlugin)
     */
    @Override
    public void disablePlugin(final Plugin plugin) {
        SimpleIpmPluginManager.getInstance().disable(SimpleIpmPluginManager.getInstance().getPlugin(plugin.getName()));
    }

    /**
     *
     * @return An instance of the {@link MockPluginLoader}.
     */
    public static PluginLoader getInstance() {
        return MockPluginLoader.instance;
    }
}
