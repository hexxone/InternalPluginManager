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

public enum Slot3x3 {
    /**
     * <code>
     * X - -
     * - - -
     * - - -
     * </code>
     *
     */
    TOP_LEFT(0),
    /**
     * <code>
     * - X -
     * - - -
     * - - -
     * </code>
     *
     */
    TOP_MIDDLE(1),
    /**
     * <code>
     * - - X
     * - - -
     * - - -
     * </code>
     *
     */
    TOP_RIGHT(2),

    /**
     * <code>
     * - - -
     * X - -
     * - - -
     * </code>
     *
     */
    MIDDLE_LEFT(3),
    /**
     * <code>
     * - - -
     * - X -
     * - - -
     * </code>
     *
     */
    MIDDLE_MIDDLE(4),
    /**
     * <code>
     * - - -
     * - - X
     * - - -
     * </code>
     *
     */
    MIDDLE_RIGHT(5),

    /**
     * <code>
     * - - -
     * - - -
     * X - -
     * </code>
     *
     */
    BOTTOM_LEFT(6),
    /**
     * <code>
     * - - -
     * - - -
     * - X -
     * </code>
     *
     */
    BOTTOM_MIDDLE(7),
    /**
     * <code>
     * - - -
     * - - -
     * - - X
     * </code>
     *
     */
    BOTTOM_RIGHT(8);

    private final int id;

    private Slot3x3(final int id) {
        this.id = id;
    }

    public static Slot3x3 getById(final int id) {
        for (final Slot3x3 slot : Slot3x3.values()) {
            if (slot.getId() == id) {
                return slot;
            }
        }
        return null;
    }

    public int getId() {
        return this.id;
    }
}
