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
package com.blockhaus2000.ipm.minecraft.block.meta.impl;

import com.blockhaus2000.ipm.minecraft.block.meta.BlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.RedstoneComparatorBlockMeta;
import com.blockhaus2000.ipm.minecraft.state.RedstoneComparatorState;

/**
 * Concrete {@link RedstoneComparatorBlockMeta} implementation.
 *
 */
public class ConcreteRedstoneComparatorBlockMeta extends ConcreteBlockMeta implements RedstoneComparatorBlockMeta {
    /**
     * <code>comparatorState</code>
     *
     */
    private final RedstoneComparatorState comparatorState;

    /**
     * Constructor of AbstractWeightedPressurePlateBlockMeta.
     *
     * @param blockMeta
     *            The {@link BlockMeta} that contains common information about
     *            this block (meta).
     * @param comparatorState
     *            <code>comparatorState</code>
     */
    public ConcreteRedstoneComparatorBlockMeta(final BlockMeta blockMeta, final RedstoneComparatorState comparatorState) {
        super(blockMeta);

        this.comparatorState = comparatorState;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.RedstoneComparatorBlockMeta#getComparatorState()
     */
    @Override
    public RedstoneComparatorState getComparatorState() {
        return this.comparatorState;
    }
}
