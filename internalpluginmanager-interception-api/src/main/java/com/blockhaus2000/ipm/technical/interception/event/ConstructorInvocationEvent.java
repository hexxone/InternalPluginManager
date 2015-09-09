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

/**
 * THis event is fired when a constructor is being invoked.
 *
 */
public class ConstructorInvocationEvent extends AbstractConstructorEvent {
    /**
     * The event handlers.
     *
     */
    private static final HandlerList HANDLERS = new HandlerList();

    /**
     * Constructor of ConstructorInvokationEvent.
     *
     * @param invokedClassName
     *            The name of the class the invoked constructor is contained by.
     * @param parameters
     *            The parameters which where used whilst the method invocation.
     */
    public ConstructorInvocationEvent(final String invokedClassName, final Object[] parameters) {
        super(invokedClassName, parameters);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.event.AbstractEvent#getHandlers()
     */
    @Override
    public HandlerList getHandlers() {
        return ConstructorInvocationEvent.HANDLERS;
    }
}
