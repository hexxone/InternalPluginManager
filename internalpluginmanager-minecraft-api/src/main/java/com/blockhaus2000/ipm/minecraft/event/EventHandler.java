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
package com.blockhaus2000.ipm.minecraft.event;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * The {@link EventHandler} stores data that is used to invoke an event
 * listener.
 *
 */
public class EventHandler {
    /**
     * The object that is used to invoke the method defined below.
     *
     */
    private final Object listenerObject;
    /**
     * The event listener method. Will be invoked with the object defined above.
     *
     */
    private final Method listenerMethod;

    /**
     *
     * Constructor of EventHandler.
     *
     * @param listenerObject
     *            The object the given method has to be invoked with. Can be
     *            <code>null</code>.
     * @param listenerMethod
     *            The method that represents the event listener.
     */
    public EventHandler(final Object listenerObject, final Method listenerMethod) {
        assert listenerMethod != null : "ListenerMethod cannot be null!";
        assert listenerObject != null || Modifier.isStatic(listenerMethod.getModifiers()) : "ListenerMethod can not"
                + " be non-static if listenerObject is null!";
        assert listenerObject == null || listenerMethod.getDeclaringClass().equals(listenerObject.getClass()) : "ListenerMethod "
                + "has to be defined by the class of listenerObject!";

        this.listenerObject = listenerObject;
        this.listenerMethod = listenerMethod;
    }

    /**
     *
     * @return {@link EventHandler#listenerObject}.
     */
    public Object getListenerObject() {
        return this.listenerObject;
    }

    /**
     *
     * @return {@link EventHandler#listenerMethod}.
     */
    public Method getListenerMethod() {
        return this.listenerMethod;
    }
}
