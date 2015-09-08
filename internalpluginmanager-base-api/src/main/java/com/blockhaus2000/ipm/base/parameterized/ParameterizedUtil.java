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
package com.blockhaus2000.ipm.base.parameterized;

import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blockhaus2000.ipm.base.MapUtil;

public final class ParameterizedUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterizedUtil.class);

    private ParameterizedUtil() {
        // Nothing to do.
    }

    public static <T extends Parameterized> T calculateMostMatching(final T[] parameterizeds, final Class<?>[] testParameterTypes) {
        final SortedMap<Integer, T> matchingParameterized = new TreeMap<Integer, T>();
        for (final T parameterized : parameterizeds) {
            final int matchPropability = parameterized.calculateMatchPropability(testParameterTypes);
            if (matchPropability > 0) {
                // Must not concern about no-way-matchings.

                matchingParameterized.put(matchPropability, parameterized);
            }
        }

        final int mostMatchingKey = matchingParameterized.lastKey();
        MapUtil.removeOthers(matchingParameterized, mostMatchingKey);

        final T result;
        if (matchingParameterized.size() <= 0) {
            // No match found: Return null.

            result = null;
        } else if (matchingParameterized.size() > 1) {
            // Problem: More than one matching method found. Use the first one.

            result = matchingParameterized.get(matchingParameterized.firstKey());

            ParameterizedUtil.LOGGER.warn(
                    "More than one matching method found for {} using parameters {}! Using first one ({}) ...",
                    Arrays.toString(parameterizeds), Arrays.toString(testParameterTypes), result);
        } else {
            result = matchingParameterized.get(mostMatchingKey);
        }

        return result;
    }
}
