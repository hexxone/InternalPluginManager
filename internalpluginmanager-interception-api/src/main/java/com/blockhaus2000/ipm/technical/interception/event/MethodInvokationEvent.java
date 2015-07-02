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
package com.blockhaus2000.ipm.technical.interception.event;

import com.blockhaus2000.ipm.technical.event.HandlerList;

public class MethodInvokationEvent extends AbstractMethodEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    public MethodInvokationEvent(final String invokedClassName, final String invokedMethodName, final Object[] parameters) {
        super(invokedClassName, invokedMethodName, parameters);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.event.AbstractEvent#getHandlers()
     */
    @Override
    public HandlerList getHandlers() {
        return MethodInvokationEvent.HANDLERS;
    }
}
