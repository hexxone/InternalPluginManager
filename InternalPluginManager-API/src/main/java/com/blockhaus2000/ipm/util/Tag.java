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
package com.blockhaus2000.ipm.util;

import java.util.Collection;

/**
 * This class is a wrapper class to store different items in a
 * {@link Collection}.
 *
 * @param <T>
 *            The type of the stored data.
 */
public class Tag<T> {
    /**
     * The stored data.
     *
     */
    private T data;

    /**
     * Initilizes a new {@link Tag} with the given initial value.
     *
     * @param data
     *            The initial value for data.
     */
    public Tag(final T data) {
        this.data = data;
    }

    /**
     * Initilizes a new {@link Tag} without any initial value.
     *
     */
    public Tag() {
        this(null);
    }

    /**
     *
     * @return {@link Tag#data}.
     */
    public T getData() {
        return data;
    }

    /**
     *
     * @param data
     *            The {@link Tag#data} to set
     */
    public void setData(final T data) {
        this.data = data;
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
        result = prime * result + (data == null ? 0 : data.hashCode());
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
        if (!(obj instanceof Tag)) {
            return false;
        }
        final Tag<?> other = (Tag<?>) obj;
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getName() + "[data=" + data + "]";
    }
}
