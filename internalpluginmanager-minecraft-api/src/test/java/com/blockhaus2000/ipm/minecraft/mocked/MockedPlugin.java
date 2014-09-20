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
package com.blockhaus2000.ipm.minecraft.mocked;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.logging.Logger;

import com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration;
import com.blockhaus2000.ipm.technical.plugin.Plugin;
import com.blockhaus2000.ipm.technical.plugin.PluginMeta;

/**
 * TODO: Add type description!
 *
 */
public class MockedPlugin implements Plugin {

    /**
     * {@inheritDoc}
     *
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        // Mock implementation.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#onDisable()
     */
    @Override
    public void onDisable() {
        // Mock implementation.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#onEnable()
     */
    @Override
    public void onEnable() {
        // Mock implementation.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#onLoad()
     */
    @Override
    public void onLoad() {
        // Mock implementation.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#getDataFolder()
     */
    @Override
    public File getDataFolder() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#getConfig()
     */
    @Override
    public AbstractFileConfiguration getConfig() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#getPropertyChangeSupport()
     */
    @Override
    public PropertyChangeSupport getPropertyChangeSupport() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#getLogger()
     */
    @Override
    public Logger getLogger() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#getPluginMeta()
     */
    @Override
    public PluginMeta getPluginMeta() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#getName()
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#setEnabled(boolean)
     */
    @Override
    public void setEnabled(final boolean enabled) {
        // Mock implementation.
    }
}
