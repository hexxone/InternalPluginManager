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
package com.blockhaus2000.ipm.technical.command.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * The {@link CommandEventManager} manages the fireing of {@link CommandEvent}.
 * See there of explenetation of {@link CommandEvent}s.
 *
 * @see com.blockhaus2000.ipm.technical.command.event.CommandEvent
 */
public class CommandEventManager {
    /**
     * The singleton instance of the {@link CommandEventManager}.
     *
     */
    private static final CommandEventManager INSTANCE = new CommandEventManager();

    /**
     * This map contains all listeners that has to be triggered whilest fireing
     * an event. A map is used to make it possible to remove listeners without
     * implementing an extra method for a listener. A class is identical enough.
     *
     */
    private final Map<Class<? extends CommandEventListener>, CommandEventListener> listeners = new HashMap<Class<? extends CommandEventListener>, CommandEventListener>();

    /**
     * Fires the given {@link CommandEvent}.
     *
     * @param event
     *            The {@link CommandEvent} to fire.
     */
    public void fire(final CommandEvent event) {
        for (final Entry<Class<? extends CommandEventListener>, CommandEventListener> entry : listeners.entrySet()) {
            entry.getValue().onCommandEvent(event);
        }
    }

    /**
     * Registers the given {@link CommandEventListener} to listen on event
     * fireing.
     *
     * @param listener
     *            The {@link CommandEventListener} to register.
     */
    public void register(final CommandEventListener listener) {
        listeners.put(listener.getClass(), listener);
    }

    /**
     * Unregisters the {@link CommandEventListener} that is associated with the
     * given {@link Class}.
     *
     * @param listenerClass
     *            The listener class to remove.
     * @return <code>true</code> if a value was removed, <code>false</code> if
     *         no value was removed.
     */
    public boolean unregister(final Class<? extends CommandEventListener> listenerClass) {
        return listeners.remove(listenerClass) != null;
    }

    /**
     * Unregisters the given {@link CommandEventListener}.
     *
     * @param listener
     *            The listener to remove.
     * @return <code>true</code> if a value was removed, <code>false</code> if
     *         no value was removed.
     */
    public boolean unregister(final CommandEventListener listener) {
        return unregister(listener.getClass());
    }

    /**
     *
     * @return The values of {@link CommandEventManager#listeners}.
     */
    public Set<CommandEventListener> getListeners() {
        return new HashSet<CommandEventListener>(listeners.values());
    }

    /**
     *
     * @return An {@link CommandEventManager} instance.
     */
    public static CommandEventManager getInstance() {
        return CommandEventManager.INSTANCE;
    }
}
