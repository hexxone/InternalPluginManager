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

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This utility class simplifies the use of {@link Collection}s.
 *
 */
public final class CollectionUtil {
    /**
     * Constructor of CollectionUtil.
     *
     */
    private CollectionUtil() {
        // Utility classes should not have a visible constructor.
    }

    /**
     * Gets the index of the first occurrence of the given regex.
     *
     * @param regex
     *            The regex to search for.
     * @param list
     *            The list that may contains the given regex.
     * @return The index of the first occurrence. If not found, <code>-1</code>.
     */
    public static int indexOf(final String regex, final List<String> list) {
        assert regex != null : "Regex cannot be null!";
        assert list != null : "List cannot be null!";

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).matches(regex)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Converts the given {@link Collection} into a {@link Collection}
     * containing all elements of the collection, encapsulated in a {@link Tag}
     * object.
     *
     * @param <T>
     *            The type of the tags that will be added to the returned
     *            collection.
     * @param col
     *            The {@link Collection} containing the elements to encapsulate.
     * @param listType
     *            The type of the {@link Collection} to use. Like
     *            {@link java.util.ArrayList}, or {@link java.util.HashSet}. Has
     *            to be a valid implementation and no interface.
     * @return An object of the given {@link Collection} type containing the
     *         encapsulated fields of the given {@link Collection}.
     */
    @SuppressWarnings("unchecked")
    public static <T> Collection<Tag<T>> toTagCollection(final Collection<? extends T> col,
            @SuppressWarnings("rawtypes") final Class<? extends Collection> listType) {
        assert col != null : "Col cannot be null!";
        assert listType != null : "ListType cannot be null!";
        assert !Modifier.isAbstract(listType.getModifiers()) : "ListType cannot be abstract!";
        assert !listType.isInterface() : "ListType cannot be an interface!";

        Collection<Tag<T>> result = null;
        try {
            result = listType.newInstance();

            for (final T element : col) {
                result.add(new Tag<T>(element));
            }
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    /**
     * Converts the given array to a set with the given type. Works like
     * {@link Arrays#asList(Object...)}.
     *
     * @param <T>
     *            The type of the set values.
     * @param setType
     *            The set type to return.
     * @param arr
     *            The array to convert into a set.
     * @return An object of the given set or, if, and only if, an error
     *         occurred, <code>null</code>.
     */
    @SuppressWarnings("unchecked")
    public static <T> Set<T> toSet(@SuppressWarnings("rawtypes") final Class<? extends Set> setType, final T... arr) {
        assert setType != null : "SetType cannot be null!";
        assert !Modifier.isAbstract(setType.getModifiers()) : "SetType cannot be abstract!";
        assert !setType.isInterface() : "SetType cannot be an interface!";
        assert arr != null : "Arr cannot be null!";

        Set<T> result = null;
        try {
            result = setType.newInstance();

            for (final T t : arr) {
                result.add(t);
            }
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    /**
     *
     * @param <T>
     *            The type of the set values.
     * @param s0
     *            Set <code>a</code>.
     * @param s1
     *            Set <code>b</code>.
     * @return Returns all value that are present in set a, but also not in set
     *         b.
     */
    public static <T> Set<T> getDifferenceSet(final Set<T> s0, final Set<T> s1) {
        assert s0 != null : "S0 cannot be null!";
        assert s1 != null : "S1 cannot be null!";

        final Set<T> result = new HashSet<T>(s0);
        result.removeAll(s1);
        return result;
    }
}
