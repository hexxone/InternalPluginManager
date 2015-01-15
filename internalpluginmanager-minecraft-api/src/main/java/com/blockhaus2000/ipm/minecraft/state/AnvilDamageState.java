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
package com.blockhaus2000.ipm.minecraft.state;

/**
 * The damage states of an anvil.
 *
 */
public enum AnvilDamageState {
    /**
     * The anvil is not damaged.
     *
     */
    NOT_DAMAGED(0),
    /**
     * The anvil is damaged.
     *
     */
    DAMAGED(1),
    /**
     * The anvil is very damaged.
     *
     */
    VERY_DAMAGED(2);

    /**
     * The ID of the damage state.
     *
     */
    private final int id;

    /**
     * Constructor of AnvilDamageState.
     *
     * @param id
     *            The ID of the damage state.
     */
    private AnvilDamageState(final int id) {
        this.id = id;
    }

    /**
     * Searchs for the {@link AnvilDamageState} with the given ID.
     *
     * @param id
     *            The ID to search for.
     * @return The {@link AnvilDamageState}, if found. Otherwise
     *         <code>null</code>.
     */
    public static AnvilDamageState getById(final int id) {
        for (final AnvilDamageState state : AnvilDamageState.values()) {
            if (state.getId() == id) {
                return state;
            }
        }
        return null;
    }

    /**
     *
     * @return {@link AnvilDamageState#id}
     */
    public int getId() {
        return this.id;
    }
}
