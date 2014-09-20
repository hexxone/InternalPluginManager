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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * The handler list id used to store {@link EventHandler}s easyly. This just
 * delegates to the methods of a {@link Set}.
 *
 */
public class HandlerList implements Iterable<EventHandler> {
    /**
     * The real list of handlers.
     *
     */
    private final Set<EventHandler> handlers = new HashSet<EventHandler>();

    /**
     * Constructor of HandlerList.
     *
     */
    public HandlerList() {
        // Nothing to do.
    }

    /**
     * Adds the given handler.
     *
     * @param handler
     *            The {@link EventHandler} to add.
     * @return <code>true</code> if it was added correctly, <code>false</code>
     *         if it already exist.
     */
    public boolean add(final EventHandler handler) {
        return this.handlers.add(handler);
    }

    /**
     * Removes the given handler.
     *
     * @param handler
     *            The {@link EventHandler} to remove.
     * @return <code>true</code> it was removed correctly, <code>false</code>if
     *         it does not exist.
     */
    public boolean remove(final EventHandler handler) {
        return this.handlers.remove(handler);
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<EventHandler> iterator() {
        return this.handlers.iterator();
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getName() + "[handlers=" + this.handlers + "]";
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
        result = prime * result + (this.handlers == null ? 0 : this.handlers.hashCode());
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
        if (!(obj instanceof HandlerList)) {
            return false;
        }
        final HandlerList other = (HandlerList) obj;
        if (this.handlers == null) {
            if (other.handlers != null) {
                return false;
            }
        } else if (!this.handlers.equals(other.handlers)) {
            return false;
        }
        return true;
    }
}
