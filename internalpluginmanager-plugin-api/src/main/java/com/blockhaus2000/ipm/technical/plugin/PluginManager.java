/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014 Fabian Damken
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.blockhaus2000.ipm.technical.plugin;

import java.io.File;

/**
 * The {@link PluginManager} manages all plugins. Enables/disable and loads
 * them. It is the heart of the plugin system.
 *
 */
public interface PluginManager {
    /**
     * Starts the plugin manager for the given directory. Once it is started, it
     * cannot be started against.
     *
     * @param directory
     *            The directory where to create the directopry structure.
     */
    void start(final File directory);

    /**
     * Removes/unloads the given plugin.
     *
     * @param plugin
     *            The {@link Plugin} to remove/unload.
     */
    void remove(final Plugin plugin);

    /**
     * Removed/unloads and deletes the given plugin.
     *
     * @param plugin
     *            The {@link Plugin} to remove/unload and delete.
     */
    void delete(final Plugin plugin);

    /**
     * Disables the given plugin.
     *
     * @param plugin
     *            The {@link Plugin} to disable.
     */
    void disable(final Plugin plugin);

    /**
     * Disables all loaded plugins.
     *
     */
    void disableAll();

    /**
     * Enables the given plugin.
     *
     * @param plugin
     *            The {@link Plugin} to enable.
     */
    void enable(final Plugin plugin);

    /**
     * Enables all loaded plugins.
     *
     */
    void enableAll();

    /**
     *
     * @param name
     *            The plugin name to search for. Is case-insensitive.
     * @return The {@link Plugin} associated with the given name. Returns
     *         <code>null</code> if not found.
     */
    Plugin getPlugin(final String name);
}
