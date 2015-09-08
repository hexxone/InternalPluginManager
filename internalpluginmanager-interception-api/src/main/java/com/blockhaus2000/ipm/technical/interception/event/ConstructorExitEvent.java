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
 * This event is fired if a constructor exists normally (by a
 * <code>return</code> statement).
 *
 */
public class ConstructorExitEvent extends AbstractConstructorEvent {
    /**
     * The event handlers.
     *
     */
    private static final HandlerList HANDLERS = new HandlerList();

    /**
     * The created object (<code>this</code>).
     *
     */
    private final Object obj;

    /**
     * Constructor of ConstructorExitEvent.
     *
     * @param invokedClassName
     *            The name of the class the invoked constructor is contained by.
     * @param obj
     *            The created object (<code>this</code>).
     * @param parameters
     *            The parameters which where used whilst the method invocation.
     */
    public ConstructorExitEvent(final String invokedClassName, final Object obj, final Object[] parameters) {
        super(invokedClassName, parameters);

        this.obj = obj;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.event.AbstractEvent#getHandlers()
     */
    @Override
    public HandlerList getHandlers() {
        return ConstructorExitEvent.HANDLERS;
    }

    /**
     *
     * @return {@link ConstructorExitEvent#obj}.
     */
    public Object getObj() {
        return this.obj;
    }
}
