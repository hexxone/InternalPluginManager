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
package com.blockhaus2000.ipm.technical.plugin.scheduler;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.blockhaus2000.ipm.base.NullsafeMap;
import com.blockhaus2000.ipm.technical.plugin.Plugin;
import com.blockhaus2000.ipm.technical.scheduler.SimpleScheduler;
import com.blockhaus2000.ipm.technical.scheduler.task.Task;

/**
 * The default implementation of the {@link PluginScheduler}.
 *
 */
public class SimplePluginScheduler extends SimpleScheduler implements PluginScheduler, PropertyChangeListener {
    /**
     * All registered tasks.
     *
     */
    private final Map<String, Set<UUID>> tasks = new NullsafeMap<String, Set<UUID>>(new HashSet<UUID>());

    /**
     * Constructor of SimplePluginScheduler.
     *
     */
    public SimplePluginScheduler() {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.scheduler.PluginScheduler#run(com.blockhaus2000.ipm.technical.plugin.Plugin,
     *      com.blockhaus2000.ipm.technical.scheduler.task.Task)
     */
    @Override
    public UUID run(final Plugin plugin, final Task task) {
        assert plugin != null : "Plugin cannot be null!";
        assert task != null : "Task has to be null!";

        plugin.getPropertyChangeSupport().addPropertyChangeListener(this);

        final UUID uuid = super.run(task);
        this.tasks.get(plugin.getName()).add(uuid);
        return uuid;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        assert event != null : "Event cannot be null!";

        if (event.getPropertyName().equals(Plugin.ENABLED_PROPERTY_NAME) && !((Boolean) event.getNewValue())) {
            if (!(event.getSource() instanceof Plugin)) {
                throw new IllegalArgumentException("The source (\"" + event.getSource() + "\") of the property change event \""
                        + event + "\" has to be an instance of \"" + Plugin.class.getName() + "\"!");
            }

            final String pluginName = ((Plugin) event.getSource()).getName().toLowerCase().trim();

            for (final UUID uuid : this.tasks.get(pluginName)) {
                this.stop(uuid);
            }

            this.tasks.remove(pluginName);
        }
    }
}
