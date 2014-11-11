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
package com.blockhaus2000.ipm.minecraft.block.meta.impl;

import com.blockhaus2000.ipm.minecraft.block.meta.BlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.TripwireHookBlockMeta;

/**
 * Concrete {@link TripwireHookBlockMeta} implementation.
 *
 */
public class ConcreteTripwireHookBlockMeta extends ConcreteBlockMeta implements TripwireHookBlockMeta {
    /**
     * <code>tensioned</code>
     *
     */
    private final boolean tensioned;
    /**
     * <code>active</code>
     *
     */
    private final boolean active;

    /**
     * Constructor of AbstractWeightedPressurePlateBlockMeta.
     *
     * @param blockMeta
     *            The {@link BlockMeta} that contains common information about
     *            this block (meta).
     * @param tensioned
     *            <code>tensioned</code>
     * @param active
     *            <code>active</code>
     */
    public ConcreteTripwireHookBlockMeta(final BlockMeta blockMeta, final boolean tensioned, final boolean active) {
        super(blockMeta);

        this.tensioned = tensioned;
        this.active = active;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.TripwireHookBlockMeta#isTensioned()
     */
    @Override
    public boolean isTensioned() {
        return this.tensioned;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.TripwireHookBlockMeta#isActive()
     */
    @Override
    public boolean isActive() {
        return this.active;
    }
}