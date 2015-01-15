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
 */
package com.blockhaus2000.ipm.minecraft;

/**
 * All available directions.
 *
 */
public enum Direction {
    /**
     * <code>
     * - / \ <br />
     * -- |  <br />
     * -- |  <br />
     * </code>
     *
     */
    UP(-4),
    /**
     * <code>
     * -- |  <br />
     * -- |  <br />
     * - \ / <br />
     * </code>
     *
     */
    DOWN(-12),

    /**
     * <code>
     * ################# <br />
     * # - - - - - - - # <br />
     * # - - - | - - - # <br />
     * # - - - | - - - # <br />
     * # - - - X - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * ################# <br />
     * </code>
     *
     */
    NORTH(0),
    /**
     * <code>
     * ################# <br />
     * # - - - - - - - # <br />
     * # - - - - | - - # <br />
     * # - - - - | - - # <br />
     * # - - - X - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * ################# <br />
     * </code>
     *
     */
    NORTH_NORTH_EAST(1),
    /**
     * <code>
     * ################# <br />
     * # - - - - - - - # <br />
     * # - - - - - / - # <br />
     * # - - - - / - - # <br />
     * # - - - X - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * ################# <br />
     * </code>
     *
     */
    NORTH_EAST(2),
    /**
     * <code>
     * ################# <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - -~~~~~- # <br />
     * # - - - X - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * ################# <br />
     * </code>
     *
     */
    NORTH_EAST_EAST(3),
    /**
     * <code>
     * ################# <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - X~~~~~- # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * ################# <br />
     * </code>
     *
     */
    EAST(4),
    /**
     * <code>
     * ################# <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - X - - - # <br />
     * # - - - -~~~~~- # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * ################# <br />
     * </code>
     *
     */
    EAST_EAST_SOUTH(5),
    /**
     * <code>
     * ################# <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - X - - - # <br />
     * # - - - - \ - - # <br />
     * # - - - - - \ - # <br />
     * # - - - - - - - # <br />
     * ################# <br />
     * </code>
     *
     */
    EAST_SOUTH(6),
    /**
     * <code>
     * ################# <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - X - - - # <br />
     * # - - - - | - - # <br />
     * # - - - - | - - # <br />
     * # - - - - - - - # <br />
     * ################# <br />
     * </code>
     *
     */
    EAST_SOUTH_SOUTH(7),
    /**
     * <code>
     * ################# <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - X - - - # <br />
     * # - - - | - - - # <br />
     * # - - - | - - - # <br />
     * # - - - - - - - # <br />
     * ################# <br />
     * </code>
     *
     */
    SOUTH(8),
    /**
     * <code>
     * ################# <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - X - - - # <br />
     * # - - | - - - - # <br />
     * # - - | - - - - # <br />
     * # - - - - - - - # <br />
     * ################# <br />
     * </code>
     *
     */
    SOUTH_SOUTH_WEST(9),
    /**
     * <code>
     * ################# <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - X - - - # <br />
     * # - - / - - - - # <br />
     * # - / - - - - - # <br />
     * # - - - - - - - # <br />
     * ################# <br />
     * </code>
     *
     */
    SOUTH_WEST(10),
    /**
     * <code>
     * ################# <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - X - - - # <br />
     * # -~~~~~- - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * ################# <br />
     * </code>
     *
     */
    SOUTH_WEST_WEST(11),
    /**
     * <code>
     * ################# <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # -~~~~~X - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * ################# <br />
     * </code>
     *
     */
    WEST(12),
    /**
     * <code>
     * ################# <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # -~~~~~- - - - # <br />
     * # - - - X - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * ################# <br />
     * </code>
     *
     */
    WEST_WEST_NORTH(13),
    /**
     * <code>
     * ################# <br />
     * # - - - - - - - # <br />
     * # - \ - - - - - # <br />
     * # - - \ - - - - # <br />
     * # - - - X - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * ################# <br />
     * </code>
     *
     */
    WEST_NORTH(14),
    /**
     * <code>
     * ################# <br />
     * # - - - - - - - # <br />
     * # - - | - - - - # <br />
     * # - - | - - - - # <br />
     * # - - - X - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * # - - - - - - - # <br />
     * ################# <br />
     * </code>
     *
     */
    WEST_NORTH_NORTH(15);

    /**
     * 360°
     *
     */
    private static final int CIRLCE_360_DEGREES = 360;
    /**
     * Direction count.
     *
     */
    private static final double DIRECTION_COUNT = 16.0;

    /**
     * The ID of this direction.
     *
     */
    private final int id;
    /**
     * The degrees of this direction.
     *
     */
    private final double degrees;

    /**
     * Constructor of Direction.
     *
     * <p>
     * NOTE: The degrees will be calculated with <code>360 / 16.0 * id</code>.
     * </p>
     *
     * @param id
     *            The ID of this direction.
     */
    private Direction(final int id) {
        this.id = id;
        this.degrees = Direction.CIRLCE_360_DEGREES / Direction.DIRECTION_COUNT * id;
    }

    /**
     * Searchs for a {@link Direction} with the given ID.
     *
     * @param id
     *            The ID to search for.
     * @return The {@link Direction} with the given ID, if found. Otherwise
     *         <code>null</code>.
     */
    public static Direction getById(final int id) {
        for (final Direction direction : Direction.values()) {
            if (direction.getId() == id) {
                return direction;
            }
        }
        return null;
    }

    /**
     * Searchs for a {@link Direction} with the given degrees.
     *
     * @param degrees
     *            The degrees to search for.
     * @return The {@link Direction} with the given degrees, if found. Otherwise
     *         <code>null</code>.
     */
    public static Direction getByDegrees(final double degrees) {
        for (final Direction direction : Direction.values()) {
            if (direction.getDegrees() == degrees) {
                return direction;
            }
        }
        return null;
    }

    /**
     *
     * @return {@link Direction#id}
     */
    public int getId() {
        return this.id;
    }

    /**
     *
     * @return {@link Direction#degrees}
     */
    public double getDegrees() {
        return this.degrees;
    }
}
