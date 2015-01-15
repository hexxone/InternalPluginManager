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
package com.blockhaus2000.ipm.technical.plugin.event;

import java.lang.reflect.Method;

import com.blockhaus2000.ipm.technical.event.EventHandler;
import com.blockhaus2000.ipm.technical.event.SimpleEventHandler;
import com.blockhaus2000.ipm.technical.plugin.Plugin;

/**
 * A special extension of the {@link SimpleEventHandler} to delegate the
 * {@link EventHandler#isEnabled()} call to {@link Plugin#isEnabled()}.
 *
 */
public class PluginEventHandler extends SimpleEventHandler {
    /**
     * The plugin that is used to get the enabled-status.
     *
     */
    private final Plugin plugin;

    /**
     * Constructor of PluginEventHandler.
     *
     * @param plugin
     *            The plugin that is the event listener holder.
     * @param listenerObject
     *            Is used withing <code>super</code>-call.
     * @param listenerMethod
     *            Is used withing <code>super</code>-call.
     */
    public PluginEventHandler(final Plugin plugin, final Object listenerObject, final Method listenerMethod) {
        super(listenerObject, listenerMethod);

        assert plugin != null : "Plugin cannot be null!";

        this.plugin = plugin;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.event.SimpleEventHandler#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return this.plugin.isEnabled();
    }
}
