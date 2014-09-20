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
 * This class defines a regular, synchronized event that can be fired with the
 * {@link EventManager}.
 * <p>
 * To implement an extra event, simply follow these instructions:
 * <ol>
 * <li>Create a class extending this class ({@link AbstractEvent}).</li>
 * <li>Add a field named <code>HANDLERS</code> with the type {@link HandlerList}
 * that has the modifiers <code>private</code>, <code>static</code> (very
 * important!) and <code>final</code>.</li>
 * <li>Implement the method {@link AbstractEvent#getHandlers()} and simply return the
 * field described above.</li>
 * </ol>
 * </p>
 * <p>
 * <b> NOTE: Do NOT create the {@link HandlerList}-field in a non-static
 * context! This WILL break the functionality of the event system! </b>
 * </p>
 *
 */
public abstract class AbstractEvent {
    /**
     *
     * @return A {@link HandlerList} containing all {@link EventHandler}s for
     *         this event.
     */
    public abstract HandlerList getHandlers();
}
