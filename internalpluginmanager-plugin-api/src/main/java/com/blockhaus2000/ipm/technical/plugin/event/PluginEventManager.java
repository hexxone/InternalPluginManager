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

import com.blockhaus2000.ipm.technical.event.EventManager;
import com.blockhaus2000.ipm.technical.plugin.Plugin;

public interface PluginEventManager extends EventManager {
    public <T> void register(final Plugin plugin, final Class<T> clazz, final T obj);

    public <T> void register(final Plugin plugin, final Class<T> clazz);

    public <T> void register(final Plugin plugin, final T obj);

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.event.EventManager#register(java.lang.Class,
     *      java.lang.Object)
     */
    @Deprecated
    @Override
    public <T> void register(final Class<T> clazz, T obj);

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.event.EventManager#register(java.lang.Class)
     */
    @Deprecated
    @Override
    public <T> void register(final Class<T> clazz);

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.event.EventManager#register(java.lang.Object)
     */
    @Deprecated
    @Override
    public <T> void register(final T obj);
}
