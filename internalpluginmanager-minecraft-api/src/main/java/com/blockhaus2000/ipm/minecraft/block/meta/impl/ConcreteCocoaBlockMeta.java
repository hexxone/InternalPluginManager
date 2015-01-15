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
import com.blockhaus2000.ipm.minecraft.block.meta.CocoaBlockMeta;
import com.blockhaus2000.ipm.minecraft.state.grow.CocaoBeanGrowState;

/**
 * Concrete {@link CocoaBlockMeta} implementation.
 *
 */
public class ConcreteCocoaBlockMeta extends ConcreteBlockMeta implements CocoaBlockMeta {
    /**
     * <code>growState</code>
     *
     */
    private final CocaoBeanGrowState growState;

    /**
     * Constructor of AbstractWeightedPressurePlateBlockMeta.
     *
     * @param blockMeta
     *            The {@link BlockMeta} that contains common information about
     *            this block (meta).
     * @param growState
     *            <code>growState</code>
     */
    public ConcreteCocoaBlockMeta(final BlockMeta blockMeta, final CocaoBeanGrowState growState) {
        super(blockMeta);

        this.growState = growState;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.CocoaBlockMeta#getGrowState()
     */
    @Override
    public CocaoBeanGrowState getGrowState() {
        return this.growState;
    }
}
