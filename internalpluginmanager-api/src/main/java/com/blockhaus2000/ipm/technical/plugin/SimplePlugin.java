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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import com.blockhaus2000.ipm.technical.command.SimpleCommandManager;
import com.blockhaus2000.ipm.technical.configuration.FileConfiguration;
import com.blockhaus2000.ipm.technical.configuration.HrTssConfiguration;
import com.blockhaus2000.ipm.technical.plugin.util.exception.PluginException;
import com.blockhaus2000.ipm.util.CommonConstants;

/**
 * An implementation of {@link Plugin}. Extend this to make your plugin
 * loadable.
 *
 */
public class SimplePlugin implements Plugin {
    /**
     * The name of the enabled-field. This is used whilest fireing
     * {@link PropertyChangeEvent}s with the {@link PropertyChangeSupport}.
     *
     */
    protected static final String ENABLED_PROPERTY_NAME = "enabled";

    /**
     * The {@link PropertyChangeSupport} for this class.
     *
     */
    protected final PropertyChangeSupport PROPERTY_CHANGE_SUPPORT = new PropertyChangeSupport(this);

    /**
     * This set contains all (command) classes that are registered with
     * {@link Plugin#registerCommands(Class, Object)}. This is used to
     * unregister these commands on disable.
     *
     */
    private final Set<Class<?>> registeredCommandClasses = new HashSet<Class<?>>();

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
    private FileConfiguration config;
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
        this.PROPERTY_CHANGE_SUPPORT.addPropertyChangeListener(SimplePlugin.ENABLED_PROPERTY_NAME, this);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#registerCommands(java.lang.Class,
     *      java.lang.Object)
     */
    @Override
    public <T> void registerCommands(final Class<T> clazz, final T obj) {
        assert clazz != null : "Clazz cannot be null!";
        assert obj == null || clazz.equals(obj.getClass()) : "Obj has to be null or an object of clazz!";

        SimpleCommandManager.getInstance().register(clazz, obj);
        this.registeredCommandClasses.add(clazz);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#registerCommands(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> void registerCommands(final T obj) {
        assert obj != null : "Obj cannot be null!";

        this.registerCommands((Class<T>) obj.getClass(), obj);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#registerCommands(java.lang.Class)
     */
    @Override
    public <T> void registerCommands(final Class<T> clazz) {
        this.registerCommands(clazz, null);
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
    public FileConfiguration getConfig() {
        return this.config;
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
    public void setEnabled(final boolean enabled) {
        final boolean oldVal = this.enabled;
        final boolean newVal = enabled;
        this.enabled = enabled;

        if (oldVal != newVal) {
            this.PROPERTY_CHANGE_SUPPORT.firePropertyChange(SimplePlugin.ENABLED_PROPERTY_NAME, oldVal, newVal);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        if ((Boolean) event.getNewValue()) {
            this.onEnable();
        } else {
            this.onDisable();
            for (final Class<?> clazz : this.registeredCommandClasses) {
                SimpleCommandManager.getInstance().unregister(clazz);
            }
        }
    }

    /**
     * Initilizes this plugin. Should be callen by the {@link PluginLoader}
     * only.
     *
     * @param pluginMeta
     *            The {@link PluginMeta}.
     */
    void init(final PluginMeta pluginMeta) {
        this.pluginMeta = pluginMeta;
        this.logger = Logger.getLogger(CommonConstants.INTERNALPLUGINMANAGER_SYSTEM_LOGGER_NAME + "." + pluginMeta.getName());
        this.dataFolder = new File(SimplePluginManager.getClassInstance().getPluginDirectory(), pluginMeta.getName()
                .toLowerCase());
        this.dataFolder.mkdir();
        try {
            this.config = new HrTssConfiguration(new File(this.dataFolder, "config.hrtss"));
        } catch (final IOException cause) {
            throw new PluginException("Cannot create plugin configuration file!", cause);
        }
    }
}
