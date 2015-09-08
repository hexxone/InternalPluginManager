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

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class MapUtil {
    private MapUtil() {
        // Nothing to do.
    }

    public static <K, V> void removeOthers(final Map<K, V> map, final K key) {
        assert map != null : "Map must not be null!";

        for (final Iterator<Entry<K, V>> iterator = map.entrySet().iterator(); iterator.hasNext();) {
            final Map.Entry<K, V> entry = iterator.next();
            if (entry == null) {
                if (key != null) {
                    iterator.remove();
                }
            } else {
                if (!entry.equals(key)) {
                    iterator.remove();
                }
            }
        }
    }
}
