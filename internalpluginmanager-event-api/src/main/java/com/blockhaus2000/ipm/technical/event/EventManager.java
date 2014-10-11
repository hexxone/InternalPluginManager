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

/**
 * An {@link EventManager} handles the fireing and registration of events and
 * event listeners. It is the main access point to fire events or listen to
 * them.
 * <p>
 * The system is based on annotations. To listen for an specific event, simply
 * tag your listener method with the {@link EventListener} annotation and
 * specify the event to listen on. Then call one of the register methods of this
 * class.
 * </p>
 *
 */
public interface EventManager {
    /**
     * Searchs for command listeners in the given class. If found, registers
     * them.
     *
     * <p>
     * <b> NOTE: A method can not be non-static in a static context (
     * <code>obj = null</code>). </b>
     * </p>
     *
     * @param clazz
     *            The class that contains the methods/listeners to register.
     * @param obj
     *            An object of the given class. Can be <code>null</code> if all
     *            methods to register are static.
     */
    <T> void register(final Class<T> clazz, final T obj);

    /**
     * Delegates to {@link EventManager#register(Plugin, Class, Object)} with
     * <code>clazz = clazz</code> and <code>obj = null</code>.
     *
     * <p>
     * <b> NOTE: Only use this method if no non-static listener methods are
     * present in the given class. </b>
     * </p>
     *
     * @param clazz
     *            Is passed into
     *            {@link EventManager#register(Plugin, Class, Object)}.
     *
     * @see com.blockhaus2000.ipm.technical.event.EventManager#register(Plugin,
     *      java.lang.Class, java.lang.Object)
     */
    <T> void register(final Class<T> clazz);

    /**
     * Delegates to {@link EventManager#register(Plugin, Class, Object)} with
     * <code>clazz = obj.getClass()</code> and <code>obj = obj</code>.
     *
     * @param obj
     *            Is passed into
     *            {@link EventManager#register(Plugin, Class, Object)}.
     *
     * @see com.blockhaus2000.ipm.technical.event.EventManager#register(Plugin,
     *      java.lang.Class, java.lang.Object)
     */
    <T> void register(final T obj);

    /**
     * Fires the given event.
     *
     * @param event
     *            The event to fire.
     */
    void fire(final AbstractEvent event);
}
