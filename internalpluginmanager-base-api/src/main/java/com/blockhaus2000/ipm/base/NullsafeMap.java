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
package com.blockhaus2000.ipm.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * The {@link NullsafeMap} is an extension of the {@link HashMap} that has a
 * default value for the elements and does not return <code>null</code> any time
 * when receiving a value.
 *
 * @param <K>
 *            The type of the key.
 * @param <V>
 *            The type of the value.
 */
public class NullsafeMap<K, V> extends HashMap<K, V> {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = 2452269166868218213L;

    /**
     * The default value.
     *
     */
    private final V defValue;

    /**
     * Constructor of NullsafeMap.
     *
     * @param defValue
     *            The default value that can be used if <code>null</code> is
     *            detected. If this value is cloneable, it will be cloned every
     *            time, <code>null</code> has to be replaced with the default
     *            value.
     */
    public NullsafeMap(final V defValue) {
        assert defValue != null : "DefValue cannot be null!";

        this.defValue = defValue;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * This does never every return <code>null</code>.
     * </p>
     *
     * @see java.util.HashMap#get(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public V get(final Object key) {
        if (super.get(key) == null) {
            super.put((K) key, this.getDefaultValue());
        }

        return super.get(key);
    }

    /**
     * {@inheritDoc}
     *
     * @see java.util.HashMap#clone()
     */
    @Override
    public Object clone() {
        return new NullsafeMap<K, V>(this.getDefaultValue());
    }

    /**
     *
     * @return The default value. If the default value is cloneabled, this
     *         returns a clone. Otherwise, it will return exactly the same
     *         reference.
     */
    private V getDefaultValue() {
        final V defValueClone = this.cloneDefaultValue();
        if (defValueClone == null) {
            return this.defValue;
        }
        return defValueClone;
    }

    /**
     * Clones the default value via reflection.
     *
     * @return The cloned default value or, if the clone was not successful,
     *         <code>null</code>.
     */
    private V cloneDefaultValue() {
        @SuppressWarnings("unchecked")
        final Class<V> clazz = (Class<V>) this.defValue.getClass();

        V result = null;
        try {
            final Method method = clazz.getDeclaredMethod("clone");

            // Save accessible state to reset it.
            final boolean accessible = method.isAccessible();

            method.setAccessible(true);

            try {
                result = clazz.cast(method.invoke(this.defValue));
            } catch (final IllegalAccessException ex) {
                // Should not happen.
            } catch (final InvocationTargetException ex) {
                // Can happen if clone is not supported.
            }

            // Reset the accessible state.
            method.setAccessible(accessible);
        } catch (final NoSuchMethodException ex) {
            // clone() is defined in java.lang.Object, so it have to exist.
        }

        // If any error occurres, null is returned.
        return result;
    }
}
