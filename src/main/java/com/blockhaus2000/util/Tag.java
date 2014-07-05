/* This file is part of InternalPluginManager
 *
 * Copyright 2014 Blockhaus2000
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  see the License for the specific language governing permissions and
 *  Limitations under the License.
 */
package com.blockhaus2000.util;

import java.util.Collection;

/**
 * This class represents a tag. A tag is a util type that can be used to put
 * different types (for example {@link Integer} and {@link String}) into a
 * {@link Collection}.
 *
 * @param <T>
 *            The type that has to be used for data storage.
 */
public class Tag<T> {
    private T data;

    /**
     * Instances a new {@link Tag} with the given data.
     *
     * @param data
     *            The data that has to be stored.
     */
    public Tag(final T data) {
        this.data = data;
    }

    /**
     * Instances a new {@link Tag} without any date.
     *
     */
    public Tag() {
        this(null);
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

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return 31 + (data == null ? 0 : data.hashCode());
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

        if (obj == null || !(obj instanceof Tag)) {
            return false;
        }

        final Tag<?> other = (Tag<?>) obj;

        if (data == null) {
            if (other.getData() != null) {
                return false;
            }
        } else if (!data.equals(other.getData())) {
            return false;
        }

        return true;
    }

    /**
     *
     * @return The stored data.
     */
    public T getData() {
        return data;
    }

    /**
     *
     * @param data
     *            The data that has to be stored.
     */
    public void setData(final T data) {
        this.data = data;
    }
}
