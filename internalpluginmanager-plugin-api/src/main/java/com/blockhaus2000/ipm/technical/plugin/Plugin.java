/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014-2015 Fabian Damken
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
 */
package com.blockhaus2000.ipm.technical.plugin;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.logging.Logger;

import com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration;

/**
 * The plugin interface. To implement a plugin, extend the {@link SimplePlugin}.
 * A plugin can be loaded by the {@link PluginManager} and extends the
 * functionality of can implement completly new features.
 *
 */
public interface Plugin extends PropertyChangeListener {
    /**
     * The name of the enabled-field. This is used whilest fireing
     * {@link PropertyChangeEvent}s with the {@link PropertyChangeSupport}.
     *
     */
    String ENABLED_PROPERTY_NAME = "enabled";

    /**
     * If the enabled flag is set to <code>false</code>, this will be callen.
     *
     */
    void onDisable();

    /**
     * If the enabled flag is set to <code>true</code>, this will be callen.
     *
     */
    void onEnable();

    /**
     * This method will be callen after loading the plugin, but before enabling
     * it.
     *
     * <p>
     * <b> NOTE: The loading does NOT take care of the dependencies. This method
     * is called in the loading order. </b>
     * </p>
     *
     */
    void onLoad();

    /**
     *
     * @return The folder where the plugin can store its internal files. Do
     *         NEVER use an other directory to store files!
     */
    File getDataFolder();

    /**
     *
     * @return The configuration for this plugin.
     */
    AbstractFileConfiguration getConfig();

    /**
     *
     * @return The {@link PropertyChangeSupport} that is used to fire property
     *         change events.
     */
    PropertyChangeSupport getPropertyChangeSupport();

    /**
     *
     * @return The {@link Logger} for this plugin.
     */
    Logger getLogger();

    /**
     *
     * @return The {@link PluginMeta}, that contains some information about this
     *         plugin.
     */
    PluginMeta getPluginMeta();

    /**
     *
     * @return The name of the plugin.
     */
    String getName();

    /**
     *
     * @return <code>true</code> if the plugin is enabled, <code>false</code>
     *         otherwise.
     */
    boolean isEnabled();

    /**
     * Sets the enabled-flag. Will automaticly call {@link Plugin#onEnable()}
     * and {@link Plugin#onDisable()} when the enable flag changes its state.
     *
     * @param enabled
     *            The new enabled flag.
     */
    void setEnabled(final boolean enabled);
}
