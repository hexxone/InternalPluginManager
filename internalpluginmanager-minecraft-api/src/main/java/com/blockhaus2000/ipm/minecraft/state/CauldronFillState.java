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
package com.blockhaus2000.ipm.minecraft.state;

/**
 * The fill states of a cauldron.
 *
 */
public enum CauldronFillState {
    /**
     * The couldron is empty.
     *
     * <p>
     * <code>
     * | - | <br />
     * | - | <br />
     * | - | <br />
     * |===| <br />
     * </code>
     * </p>
     */
    EMPTY(0),
    /**
     * The cauldron is third-full.
     *
     * <p>
     * <code>
     * | - | <br />
     * | - | <br />
     * |ZZZ| <br />
     * |===| <br />
     * </code>
     * </p>
     */
    THIRD_FULL(1),
    /**
     * The cauldron is three-thrid full.
     *
     * <p>
     * <code>
     * | - | <br />
     * |ZZZ| <br />
     * |ZZZ| <br />
     * |===| <br />
     * </code>
     * </p>
     */
    TWO_THIRD_FULL(2),
    /**
     * The cauldron is full.
     *
     * <p>
     * <code>
     * |ZZZ| <br />
     * |ZZZ| <br />
     * |ZZZ| <br />
     * |===| <br />
     * </code>
     * </p>
     */
    FULL(3);

    /**
     * The ID of this fill state.
     *
     */
    private final int id;

    /**
     * Constructor of CauldronFillState.
     *
     * @param id
     *            The ID of this fill state.
     */
    private CauldronFillState(final int id) {
        this.id = id;
    }

    /**
     * Searchs for the fill state with the give ID.
     *
     * @param id
     *            The ID to search for.
     * @return The fill state associated with the given ID.
     */
    public static CauldronFillState getById(final int id) {
        for (final CauldronFillState fillState : CauldronFillState.values()) {
            if (fillState.getId() == id) {
                return fillState;
            }
        }
        return null;
    }

    /**
     *
     * @return {@link CauldronFillState#id}
     */
    public int getId() {
        return this.id;
    }
}
