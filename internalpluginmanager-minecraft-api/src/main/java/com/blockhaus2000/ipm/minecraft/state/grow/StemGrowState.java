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
package com.blockhaus2000.ipm.minecraft.state.grow;

/**
 * The grow states of a melon/pumpkin stem.
 *
 */
public enum StemGrowState implements GrowState {
    /**
     * The stem was just seeded.
     *
     */
    SEED(0),
    /**
     * The stem is in stage 1.
     *
     */
    STAGE_1(1),
    /**
     * The stem is in stage 2.
     *
     */
    STAGE_2(2),
    /**
     * The stem is in stage 3.
     *
     */
    STAGE_3(3),
    /**
     * The stem is in stage 4.
     *
     */
    STAGE_4(4),
    /**
     * The stem is in stage 5.
     *
     */
    STAGE_5(5),
    /**
     * The stem is in stage 6.
     *
     */
    STAGE_6(6),
    /**
     * The stem is ripe (pumpinks/melons start spreading).
     *
     */
    RIPE(7);

    /**
     * The grow state ID.
     *
     */
    private final int stateId;

    /**
     * Constructor of StemGrowState.
     *
     * @param stateId
     *            The grow state ID.
     */
    private StemGrowState(final int stateId) {
        this.stateId = stateId;
    }

    /**
     * Searchs for the grow state with the give state ID.
     *
     * @param stateId
     *            The state ID to search for.
     * @return The grow state associated with the given ID.
     */
    public static StemGrowState getById(final int stateId) {
        for (final StemGrowState growState : StemGrowState.values()) {
            if (growState.getStateId() == stateId) {
                return growState;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.state.grow.GrowState#getStateId()
     */
    @Override
    public int getStateId() {
        return this.stateId;
    }
}
