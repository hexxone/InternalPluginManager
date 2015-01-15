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
package com.blockhaus2000.ipm.minecraft.state.grow;

/**
 * The grow states of nether warts.
 *
 */
public enum NetherWartGrowState implements GrowState {
    /**
     * The nether wart is just seeded.
     *
     */
    SEED(0),
    /**
     * The nether wart is in stage 1.
     *
     */
    STATE_ONE(1),
    /**
     * The nether wart is in stage 2.
     *
     */
    STATE_TWO(2),
    /**
     * The nether wart is ripe.
     *
     */
    RIPE(3);

    /**
     * The grow state ID.
     *
     */
    private final int stateId;

    /**
     * Constructor of NetherWartGrowState.
     *
     * @param stateId
     *            The grow state ID.
     */
    private NetherWartGrowState(final int stateId) {
        this.stateId = stateId;
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
