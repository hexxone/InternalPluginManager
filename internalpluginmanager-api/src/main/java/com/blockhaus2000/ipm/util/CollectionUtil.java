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

import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;

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
}
