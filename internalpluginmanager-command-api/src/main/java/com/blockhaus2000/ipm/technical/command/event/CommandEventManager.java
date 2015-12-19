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
package com.blockhaus2000.ipm.technical.command.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link CommandEventManager} manages the fireing of {@link CommandEvent}.
 * See there of explenetation of {@link CommandEvent}s.
 *
 * @see com.blockhaus2000.ipm.technical.command.event.CommandEvent
 */
public final class CommandEventManager {
    /**
     * The Logger for this class.
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandEventManager.class);

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
    private final Map<String, CommandEventListener> listeners = new HashMap<String, CommandEventListener>();

    /**
     * Constructor of CommandEventManager.
     *
     */
    private CommandEventManager() {
        // Nothing to do (only to provide singleton).
    }

    /**
     * Fires the given {@link CommandEvent}.
     *
     * @param event
     *            The {@link CommandEvent} to fire.
     */
    public void fire(final CommandEvent event) {
        assert event != null : "Event cannot be null!";

        CommandEventManager.LOGGER.debug("Firing command event " + event);

        for (final CommandEventListener listener : this.listeners.values()) {
            CommandEventManager.LOGGER.debug("Fired for " + listener);

            listener.onCommandEvent(event);
        }

        CommandEventManager.LOGGER.debug("Finished command event firing");
    }

    /**
     * Registers the given {@link CommandEventListener} to listen on event
     * fireing.
     *
     * @param listener
     *            The {@link CommandEventListener} to register.
     */
    public synchronized void register(final CommandEventListener listener) {
        assert listener != null : "Listener cannot be null!";

        CommandEventManager.LOGGER.debug("Registering command event listener " + listener);

        this.listeners.put(listener.getClass().getName(), listener);
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
    public synchronized boolean unregister(final Class<? extends CommandEventListener> listenerClass) {
        assert listenerClass != null : "ListenerClass cannot be null!";

        CommandEventManager.LOGGER.debug("Unregistering command event listener class " + listenerClass);

        return this.listeners.remove(listenerClass.getName()) != null;
    }

    /**
     * Unregisters the given {@link CommandEventListener}.
     *
     * @param listener
     *            The listener to remove.
     * @return <code>true</code> if a value was removed, <code>false</code> if
     *         no value was removed.
     */
    public synchronized boolean unregister(final CommandEventListener listener) {
        assert listener != null : "Listener cannot be null!";

        CommandEventManager.LOGGER.debug("Unregistering command event listener " + listener);

        return this.unregister(listener.getClass());
    }

    /**
     *
     * @return The values of {@link CommandEventManager#listeners}.
     */
    public Set<CommandEventListener> getListeners() {
        return new HashSet<CommandEventListener>(this.listeners.values());
    }

    /**
     *
     * @return An {@link CommandEventManager} instance.
     */
    public static CommandEventManager getInstance() {
        return CommandEventManager.INSTANCE;
    }
}
