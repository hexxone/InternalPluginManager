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

/**
 * An event context only contains an event and is used as the first and only
 * argument of event listeners. This is jurt to simplify the event listening.
 *
 * @param <T>
 *            The specific event.
 */
public class EventContext<T extends AbstractEvent> {
    /**
     * The stored event.
     *
     */
    private final T event;

    /**
     * Constructor of EventContext.
     *
     * @param event
     *            The event to store.
     */
    public EventContext(final T event) {
        this.event = event;
    }

    /**
     *
     * @return The saved event.
     */
    public T getEvent() {
        return this.event;
    }
}
