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
package com.blockhaus2000.ipm.technical.event;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * The {@link SimpleEventHandler} stores data that is used to invoke an event
 * listener.
 *
 */
public class SimpleEventHandler implements EventHandler {
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
    public SimpleEventHandler(final Object listenerObject, final Method listenerMethod) {
        assert listenerMethod != null : "ListenerMethod cannot be null!";
        assert listenerObject != null || Modifier.isStatic(listenerMethod.getModifiers()) : "ListenerMethod can not"
                + " be non-static if listenerObject is null!";
        assert listenerObject == null || listenerMethod.getDeclaringClass().equals(listenerObject.getClass()) : "ListenerMethod "
                + "has to be defined by the class of listenerObject!";

        this.listenerObject = listenerObject;
        this.listenerMethod = listenerMethod;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getName() + "[listenerObject=" + this.listenerObject + ", listenerMethod=" + this.listenerMethod
                + "]";
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.listenerMethod == null ? 0 : this.listenerMethod.hashCode());
        result = prime * result + (this.listenerObject == null ? 0 : this.listenerObject.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SimpleEventHandler)) {
            return false;
        }
        final SimpleEventHandler other = (SimpleEventHandler) obj;
        if (this.listenerMethod == null) {
            if (other.listenerMethod != null) {
                return false;
            }
        } else if (!this.listenerMethod.equals(other.listenerMethod)) {
            return false;
        }
        if (this.listenerObject == null) {
            if (other.listenerObject != null) {
                return false;
            }
        } else if (!this.listenerObject.equals(other.listenerObject)) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.event.EventHandler#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.event.EventHandler#getListenerObject()
     */
    @Override
    public Object getListenerObject() {
        return this.listenerObject;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.event.EventHandler#getListenerMethod()
     */
    @Override
    public Method getListenerMethod() {
        return this.listenerMethod;
    }
}
