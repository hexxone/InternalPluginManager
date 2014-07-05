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
package com.blockhaus2000.plugin;

import java.util.Set;

/**
 * The plugin manager that manages all enable, disable, register and unregister
 * logics.
 *
 */
public interface IpmPluginManager {
    /**
     *
     * @param pluginName
     *            The plugin name to search for.
     * @return The {@link IpmPlugin} that is associated with the given plugin
     *         name.
     */
    public IpmPlugin getPlugin(final String pluginName);

    /**
     * Disables the given plugin.
     *
     * @param plugin
     *            The plugin to disable.
     */
    public void disable(final IpmPlugin plugin);

    /**
     * Disables the given plugins.
     *
     * @param plugins
     *            The plugins to disable.
     */
    public void disable(final Set<IpmPlugin> plugins);

    /**
     * Disables all registered plugins.
     *
     */
    public void disableAll();

    /**
     * Enables the given plugin.
     *
     * @param plugin
     *            The plugin to enable.
     */
    public void enable(final IpmPlugin plugin);

    /**
     * Enables the given plugins.
     *
     * @param plugins
     *            The plugins to enable.
     */
    public void enable(final Set<IpmPlugin> plugins);

    /**
     * Enables all registered plugins.
     *
     */
    public void enableAll();

    /**
     * Disables, Unregisters, Registers and Enables the given plugin.
     *
     * @param plugin
     *            The plugin to reload.
     */
    public void reload(final IpmPlugin plugin);

    /**
     * Disables, Unregisters, Registers and Enables the given plugins.
     *
     * @param plugins
     *            The plugins to reload.
     */
    public void reload(final Set<IpmPlugin> plugins);

    /**
     * Disables and Enables the given plugin.
     *
     * @param plugin
     *            The plugin to soft reload.
     */
    public void softReload(final IpmPlugin plugin);

    /**
     * Disables and Enables the given plugins.
     *
     * @param plugins
     *            The plugins to soft reload.
     */
    public void softReload(final Set<IpmPlugin> plugins);

    /**
     * Unregisters the given plugin.
     *
     * @param plugin
     *            The plugin to unregister.
     */
    public void unregister(final IpmPlugin plugin);

    /**
     * Unregisters the given plugins.
     *
     * @param plugins
     *            The plugins to unregister.
     */
    public void unregister(final Set<IpmPlugin> plugins);

    /**
     * Unregisters all registered plugins.
     *
     */
    public void unregisterAll();

    /**
     * Registers the given plugin.
     *
     * @param plugin
     *            The plugin to register.
     */
    public void register(final IpmPlugin plugin);

    /**
     * Registers the given plugins.
     *
     * @param plugins
     *            The plugins to register.
     */
    public void register(final Set<IpmPlugin> plugins);

    /**
     *
     * @return All registered plugins.
     */
    public Set<IpmPlugin> getPlugins();
}
