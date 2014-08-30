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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

// TODO: Update all documentations!

/**
 * This class provides some static methods for the usage of arrays. See the
 * methods below for the full documentation what the specific method does.
 *
 */
public final class ArrayUtil {
    /**
     * Constructor of ArrayUtil.
     *
     */
    private ArrayUtil() {
        // Utility classes should not have a public, protected or visible
        // constructor.
    }

    /**
     * Converts the given {@link Collection} to an {@link String}[]. To convert
     * the content to a string this will call {@link Object#toString()} or, with
     * the Java dynamic method binding, on the given content types.
     *
     * @param col
     *            The {@link Collection} that has to be converted to a an
     *            {@link String}[].
     * @param <E>
     *            The data type of the given {@link Collection} that will be
     *            converted into {@link String}s.
     * @return The converted {@link String}[].
     */
    public static <E> String[] toStringArray(final Collection<E> col) {
        final String[] array = new String[col.size()];

        int i = 0;
        for (final Object target : col.toArray()) {
            if (target instanceof Tag) {
                array[i++] = ((Tag<?>) target).getData().toString();
            } else {
                array[i++] = target.toString();
            }
        }

        return array;
    }

    /**
     * Removes the given index from the given {@link String}[] and returns the
     * new {@link String}[].
     *
     * @param array
     *            The {@link String}[] that contains the index to remove.
     * @param index
     *            The index to remove.
     * @return A new {@link String}[] where the given index has been removed.
     * @throws ArrayIndexOutOfBoundsException
     *             Will be thrown if the given index is out of bounds of the
     *             array.
     */
    public static String[] removeFromArray(final String[] array, final int index) throws ArrayIndexOutOfBoundsException {
        final String[] edited = new String[array.length - 1];

        // boolean removed = false;
        // for (int i = 0; i < array.length; i++) {
        // if (!removed && i == index) {
        // continue;
        // } else if (!removed) {
        // edited[i] = array[i];
        // } else {
        // edited[i - 1] = array[i];
        // }
        // }

        boolean removed = false;
        for (int i = 0; i < array.length; i++) {
            if (i == index) {
                removed = true;
                continue;
            }

            edited[removed ? i - 1 : i] = array[i];
        }

        return edited;
    }

    /**
     * Converts the given {@link Collection} to a {@link Tag} {@link List}. The
     * given {@link Class} specifies the {@link List} that has to be used (for
     * example an {@link ArrayList} or a {@link LinkedList}).
     *
     * @param col
     *            The {@link Collection} that has to be converted in a
     *            {@link Tag} {@link List}.
     * @param listClass
     *            The {@link Class}
     * @param <T>
     *            The data type of the collection and the data type of the
     *            {@link Tag} {@link List} that will be returned.
     * @return A {@link Tag} {@link List} that contains the elements of the
     *         given {@link Collection}.
     */
    @SuppressWarnings("unchecked")
    public static <T> List<Tag<? extends T>> toTagList(final Collection<? extends T> col,
            @SuppressWarnings("rawtypes") final Class<? extends List> listClass) {
        final List<Tag<? extends T>> result;
        try {
            result = listClass.newInstance();
        } catch (final InstantiationException ex) {
            throw new RuntimeException(ex);
        } catch (final IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
        for (final Object target : col) {
            result.add(new Tag<T>((T) target));
        }
        return result;
    }
}
