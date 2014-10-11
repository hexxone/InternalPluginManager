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
package com.blockhaus2000.ipm.technical.plugin.event;

import java.lang.reflect.Method;

import com.blockhaus2000.ipm.technical.event.SimpleEventManager;
import com.blockhaus2000.ipm.technical.plugin.Plugin;

public class SimplePluginEventManager extends SimpleEventManager implements PluginEventManager {
    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.event.PluginEventManager#register(com.blockhaus2000.ipm.technical.plugin.Plugin,
     *      java.lang.Class, java.lang.Object)
     */
    @Override
    public <T> void register(final Plugin plugin, final Class<T> clazz, final T obj) {
        assert plugin != null : "Plugin cannot be null!";
        assert clazz != null : "Clazz cannot be null!";

        for (final Method method : clazz.getDeclaredMethods()) {
            this.register(new PluginEventHandler(plugin, obj, method), clazz);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.event.PluginEventManager#register(com.blockhaus2000.ipm.technical.plugin.Plugin,
     *      java.lang.Class)
     */
    @Override
    public <T> void register(final Plugin plugin, final Class<T> clazz) {
        this.register(plugin, clazz, null);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.event.PluginEventManager#register(com.blockhaus2000.ipm.technical.plugin.Plugin,
     *      java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> void register(final Plugin plugin, final T obj) {
        assert obj != null : "Obj cannot be null!";

        this.register(plugin, (Class<T>) obj.getClass(), obj);
    }
}
