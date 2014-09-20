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
package com.blockhaus2000.ipm.technical.plugin.command;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.blockhaus2000.ipm.technical.command.SimpleCommandManager;
import com.blockhaus2000.ipm.technical.command.util.CommandInfo;
import com.blockhaus2000.ipm.technical.plugin.Plugin;

/**
 * An implementation of the {@link PluginCommandManager}, using the main
 * functionality of the {@link SimpleCommandManager}.
 *
 */
public class SimplePluginCommandManager extends SimpleCommandManager implements PluginCommandManager, PropertyChangeListener {
    /**
     * A {@link Map} containing all command classes that was registered with the
     * {@link PluginCommandManager}. This is used for auto-unregistering.
     *
     * <p>
     * <ul>
     * <li>Key ({@link String}): The plugin name that the command classes are
     * associated with.</li>
     * <li>Value ({@link List}): A {@link List} of classes (containing commands)
     * that are associated with the plugin.</li>
     * </ul>
     * </p>
     *
     */
    private final Map<String, List<Class<?>>> classes = new HashMap<String, List<Class<?>>>();

    /**
     * Constructor of SimplePluginCommandManager.
     *
     */
    public SimplePluginCommandManager() {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.command.PluginCommandManager#
     *      register(com.blockhaus2000.ipm.technical.plugin.Plugin,
     *      java.lang.Class, java.lang.Object)
     */
    @Override
    public <T> Set<CommandInfo> register(final Plugin plugin, final Class<T> clazz, final T obj) {
        assert plugin != null : "Plugin cannot be null!";

        final Set<CommandInfo> result = this.register(clazz, obj);

        if (!result.isEmpty()) {
            // If nothing was registered, it is faster to NOT execute this and
            // spam unnecessarry stuff to the map.

            // Add clazz to class list.
            final String pluginName = plugin.getName().toLowerCase().trim();
            if (this.classes.get(pluginName) == null) {
                this.classes.put(pluginName, new ArrayList<Class<?>>());
            }
            this.classes.get(pluginName).add(clazz);

            // Register to the property change listener to enable
            // auto-unregistration of plugins.
            plugin.getPropertyChangeSupport().addPropertyChangeListener(this);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.command.PluginCommandManager#
     *      register(com.blockhaus2000.ipm.technical.plugin.Plugin,
     *      java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> Set<CommandInfo> register(final Plugin plugin, final T obj) {
        assert obj != null : "Obj cannot be null!";

        return this.register(plugin, (Class<T>) obj.getClass(), obj);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.command.PluginCommandManager#
     *      register(com.blockhaus2000.ipm.technical.plugin.Plugin,
     *      java.lang.Class)
     */
    @Override
    public <T> Set<CommandInfo> register(final Plugin plugin, final Class<T> clazz) {
        return this.register(plugin, clazz, null);
    }

    /**
     * {@inheritDoc}
     *
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        if (event.getPropertyName().equals(Plugin.ENABLED_PROPERTY_NAME) && !((Boolean) event.getNewValue())) {
            if (!(event.getSource() instanceof Plugin)) {
                throw new IllegalArgumentException("The source (\"" + event.getSource() + "\") of the property change event \""
                        + event + "\" has to be an instance of \"" + Plugin.class.getName() + "\"!");
            }

            // Get the lower cased, trimmed plugin name.
            final String pluginName = ((Plugin) event.getSource()).getName().toLowerCase().trim();

            // Unregister all classes associated with that plugin name.
            for (final Class<?> clazz : this.classes.get(pluginName)) {
                super.unregister(clazz);
            }

            // Remove all classes for that plugin name.
            this.classes.remove(pluginName);
        }
    }
}
