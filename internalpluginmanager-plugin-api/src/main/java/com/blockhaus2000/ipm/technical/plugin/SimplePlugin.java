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
package com.blockhaus2000.ipm.technical.plugin;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import com.blockhaus2000.ipm.base.CommonConstants;
import com.blockhaus2000.ipm.technical.configuration.AbstractFileConfiguration;
import com.blockhaus2000.ipm.technical.configuration.HrTssConfiguration;
import com.blockhaus2000.ipm.technical.plugin.util.exception.PluginException;

/**
 * An implementation of {@link Plugin}. Extend this to make your plugin
 * loadable.
 *
 */
public class SimplePlugin implements Plugin {
    /**
     * The {@link PropertyChangeSupport} for this class.
     *
     */
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * The plugin {@link Logger}.
     *
     */
    private Logger logger;
    /**
     * The plugin data folder.
     *
     */
    private File dataFolder;
    /**
     * The plugin file configuration.
     *
     */
    private AbstractFileConfiguration config;
    /**
     * The {@link PluginMeta} containing main information about this plugin.
     *
     */
    private PluginMeta pluginMeta;

    /**
     * The enabled flag.
     *
     */
    private boolean enabled;

    /**
     * Constructor of SimplePlugin.
     *
     * <p>
     * <b> NOTE: This constructor should NOT be called from others than a
     * {@link PluginManager}. </b>
     * </p>
     *
     */
    protected SimplePlugin() {
        this.propertyChangeSupport.addPropertyChangeListener(this);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#onDisable()
     */
    @Override
    public void onDisable() {
        // Implemented empty.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#onEnable()
     */
    @Override
    public void onEnable() {
        // Implemented empty.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#onLoad()
     */
    @Override
    public void onLoad() {
        // Implemented empty.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#getDataFolder()
     */
    @Override
    public File getDataFolder() {
        return this.dataFolder;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#getConfig()
     */
    @Override
    public AbstractFileConfiguration getConfig() {
        return this.config;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#getPropertyChangeSupport()
     */
    @Override
    public PropertyChangeSupport getPropertyChangeSupport() {
        return this.propertyChangeSupport;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#getLogger()
     */
    @Override
    public Logger getLogger() {
        return this.logger;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#getPluginMeta()
     */
    @Override
    public PluginMeta getPluginMeta() {
        return this.pluginMeta;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#getName()
     */
    @Override
    public String getName() {
        return this.pluginMeta.getName();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#setEnabled(boolean)
     */
    @Override
    public synchronized void setEnabled(final boolean enabled) {
        final boolean oldVal = this.enabled;
        final boolean newVal = enabled;
        this.enabled = enabled;

        if (oldVal != newVal) {
            this.propertyChangeSupport.firePropertyChange(Plugin.ENABLED_PROPERTY_NAME, oldVal, newVal);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        assert event != null : "Event cannot be null!";

        if (event.getPropertyName().equals(Plugin.ENABLED_PROPERTY_NAME)) {
            if ((Boolean) event.getNewValue()) {
                this.onEnable();
            } else {
                this.onDisable();
            }
        }
    }

    /**
     * Initilizes this plugin. Should be callen by the {@link PluginLoader}
     * only.
     *
     * @param initPluginMeta
     *            The {@link PluginMeta}.
     */
    synchronized void init(final PluginMeta initPluginMeta) {
        assert this.pluginMeta != null : "InitPluginMeta cannot be null!";

        if (this.pluginMeta != null) {
            throw new IllegalStateException("Already initilized!");
        }

        this.pluginMeta = initPluginMeta;
        this.logger = Logger.getLogger(CommonConstants.INTERNALPLUGINMANAGER_SYSTEM_LOGGER_NAME + "." + initPluginMeta.getName());
        this.dataFolder = new File(PluginManager.getInstance().getConfigDirectory(), initPluginMeta.getName().toLowerCase());
        this.dataFolder.mkdir();
        try {
            this.config = new HrTssConfiguration(new File(this.dataFolder, "config.hrtss"));
        } catch (final IOException cause) {
            throw new PluginException("Cannot create plugin configuration file!", cause);
        }
    }
}
