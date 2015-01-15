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
package com.blockhaus2000.ipm.technical.plugin.event;

import com.blockhaus2000.ipm.technical.event.EventManager;
import com.blockhaus2000.ipm.technical.plugin.Plugin;

/**
 * A special extension for the normal {@link EventManager} to enable
 * auto-disabling of event listeners that are holded by disabled plugins.
 *
 */
public interface PluginEventManager extends EventManager {
    /**
     * Registers the event handlers of the given class for the given plugin. The
     * plugin is used to auto-disable event listeners for plugins that where
     * disabled.
     *
     * @param <T>
     *            The type of the class/object.
     * @param plugin
     *            The plugin to use for the auto-disabling.
     * @param clazz
     *            The class that contains the event listeners.
     * @param obj
     *            An object of the given class. Can only be <code>null</code> if
     *            all event listeners are static methods.
     * @see com.blockhaus2000.ipm.technical.event.EventManager#register(java.lang.Class,
     *      java.lang.Object)
     */
    <T> void register(final Plugin plugin, final Class<T> clazz, final T obj);

    /**
     * Delegates to {@link PluginEventManager#register(Plugin, Class, Object)}
     * with <code>plugin = plugin</code>, <code>clazz = clazz</code> and
     * <code>obj = null</code>.
     *
     * @param <T>
     *            The type of the class.
     * @param plugin
     *            Is passed into
     *            {@link PluginEventManager#register(Plugin, Class, Object)}.
     * @param clazz
     *            Is passed into
     *            {@link PluginEventManager#register(Plugin, Class, Object)}.
     */
    <T> void register(final Plugin plugin, final Class<T> clazz);

    /**
     * Delegates to {@link PluginEventManager#register(Plugin, Class, Object)}
     * with <code>plugin = plugin</code>, <code>clazz = obj.getClass()</code>
     * and <code>obj = obj</code>.
     *
     * @param <T>
     *            The type of the object.
     * @param plugin
     *            Is passed into
     *            {@link PluginEventManager#register(Plugin, Class, Object)}.
     * @param obj
     *            Is passed into
     *            {@link PluginEventManager#register(Plugin, Class, Object)}.
     */
    <T> void register(final Plugin plugin, final T obj);

    /**
     * {@inheritDoc}
     *
     * @deprecated Use
     *             {@link PluginEventManager#register(Plugin, Class, Object)},
     *             {@link PluginEventManager#register(Plugin, Class)} or
     *             {@link PluginEventManager#register(Plugin, Object)} instead.
     * @see com.blockhaus2000.ipm.technical.event.EventManager#register(java.lang.Class,
     *      java.lang.Object)
     */
    @Deprecated
    @Override
    <T> void register(final Class<T> clazz, T obj);

    /**
     * {@inheritDoc}
     *
     * @deprecated Use
     *             {@link PluginEventManager#register(Plugin, Class, Object)},
     *             {@link PluginEventManager#register(Plugin, Class)} or
     *             {@link PluginEventManager#register(Plugin, Object)} instead.
     * @see com.blockhaus2000.ipm.technical.event.EventManager#register(java.lang.Class)
     */
    @Deprecated
    @Override
    <T> void register(final Class<T> clazz);

    /**
     * {@inheritDoc}
     *
     * @deprecated Use
     *             {@link PluginEventManager#register(Plugin, Class, Object)},
     *             {@link PluginEventManager#register(Plugin, Class)} or
     *             {@link PluginEventManager#register(Plugin, Object)} instead.
     * @see com.blockhaus2000.ipm.technical.event.EventManager#register(java.lang.Object)
     */
    @Deprecated
    @Override
    <T> void register(final T obj);
}
