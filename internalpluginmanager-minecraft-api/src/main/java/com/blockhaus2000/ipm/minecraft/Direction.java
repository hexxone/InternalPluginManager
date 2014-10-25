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
package com.blockhaus2000.ipm.minecraft;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Direction {
    NORTH(0),
    NORTH_NORTH_EAST(1),
    NORTH_EAST(2),
    NORTH_EAST_EAST(3),
    EAST(4),
    EAST_EAST_SOUTH(5),
    EAST_SOUTH(6),
    EAST_SOUTH_SOUTH(7),
    SOUTH(8),
    SOUTH_SOUTH_WEST(9),
    SOUTH_WEST(10),
    SOUTH_WEST_WEST(11),
    WEST(12),
    WEST_WEST_NORTH(13),
    WEST_NORTH(14),
    WEST_NORTH_NORTH(15);

    private static final Map<Integer, Direction> LOOKUP_MAP;
    private static final Map<Double, Direction> DEGREES_LOOKUP_MAP;

    private final int id;
    private final double degrees;

    public static void main(final String[] args) {
        for (final Direction d : Direction.values()) {
            System.out.println(d + " (" + d.getId() + "): " + d.getDegrees() + "°");
        }
    }

    static {
        final Map<Integer, Direction> lookupMap = new HashMap<Integer, Direction>();
        final Map<Double, Direction> degreesLookupMap = new HashMap<Double, Direction>();

        for (final Direction direction : Direction.values()) {
            lookupMap.put(direction.getId(), direction);
            degreesLookupMap.put(direction.getDegrees(), direction);
        }

        LOOKUP_MAP = Collections.unmodifiableMap(lookupMap);
        DEGREES_LOOKUP_MAP = Collections.unmodifiableMap(degreesLookupMap);
    }

    private Direction(final int id) {
        this.id = id;
        this.degrees = 360 / 16.0 * id;
    }

    public static Direction getById(final int id) {
        return Direction.LOOKUP_MAP.get(id);
    }

    public static Direction getByDegrees(final double degrees) {
        return Direction.DEGREES_LOOKUP_MAP.get(degrees);
    }

    public int getId() {
        return this.id;
    }

    public double getDegrees() {
        return this.degrees;
    }
}
