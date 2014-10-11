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

import com.blockhaus2000.ipm.technical.event.SimpleEventHandler;
import com.blockhaus2000.ipm.technical.plugin.Plugin;

public class PluginEventHandler extends SimpleEventHandler {
    private final Plugin plugin;

    public PluginEventHandler(final Plugin plugin, final Object listenerObject, final Method listenerMethod) {
        super(listenerObject, listenerMethod);

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
