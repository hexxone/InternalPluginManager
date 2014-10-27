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
package com.blockhaus2000.ipm.minecraft.util;

/**
 * An enum to locate a field in a 3x3 matrix.
 *
 */
public enum Slot3x3 {
    /**
     * <code>
     * ######### <br />
     * # X - - # <br />
     * # - - - # <br />
     * # - - - # <br />
     * ######### <br />
     * </code>
     *
     */
    TOP_LEFT(0),
    /**
     * <code>
     * ######### <br />
     * # - X - # <br />
     * # - - - # <br />
     * # - - - # <br />
     * ######### <br />
     * </code>
     *
     */
    TOP_MIDDLE(1),
    /**
     * <code>
     * ######### <br />
     * # - - X # <br />
     * # - - - # <br />
     * # - - - # <br />
     * ######### <br />
     * </code>
     *
     */
    TOP_RIGHT(2),

    /**
     * <code>
     * ######### <br />
     * # - - - # <br />
     * # X - - # <br />
     * # - - - # <br />
     * ######### <br />
     * </code>
     *
     */
    MIDDLE_LEFT(3),
    /**
     * <code>
     * ######### <br />
     * # - - - # <br />
     * # - X - # <br />
     * # - - - # <br />
     * ######### <br />
     * </code>
     *
     */
    MIDDLE_MIDDLE(4),
    /**
     * <code>
     * ######### <br />
     * # - - - # <br />
     * # - - X # <br />
     * # - - - # <br />
     * ######### <br />
     * </code>
     *
     */
    MIDDLE_RIGHT(5),

    /**
     * <code>
     * ######### <br />
     * # - - - # <br />
     * # - - - # <br />
     * # X - - # <br />
     * ######### <br />
     * </code>
     *
     */
    BOTTOM_LEFT(6),
    /**
     * <code>
     * ######### <br />
     * # - - - # <br />
     * # - - - # <br />
     * # - X - # <br />
     * ######### <br />
     * </code>
     *
     */
    BOTTOM_MIDDLE(7),
    /**
     * <code>
     * ######### <br />
     * # - - - # <br />
     * # - - - # <br />
     * # - - X # <br />
     * ######### <br />
     * </code>
     *
     */
    BOTTOM_RIGHT(8);

    /**
     * The ID of this slot.
     *
     */
    private final int id;

    /**
     * Constructor of Slot3x3.
     *
     * @param id
     *            The ID of this slot.
     */
    private Slot3x3(final int id) {
        this.id = id;
    }

    /**
     * Searchs for the {@link Slot3x3} with the given ID.
     *
     * @param id
     *            The ID to search for.
     * @return The {@link Slot3x3} that is associated with the given ID. If
     *         nothing was found, this returns <code>null</code>.
     */
    public static Slot3x3 getById(final int id) {
        for (final Slot3x3 slot : Slot3x3.values()) {
            if (slot.getId() == id) {
                return slot;
            }
        }
        return null;
    }

    /**
     *
     * @return {@link Slot3x3#id}
     */
    public int getId() {
        return this.id;
    }
}
