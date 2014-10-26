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

import com.blockhaus2000.ipm.minecraft.Direction;
import com.blockhaus2000.ipm.minecraft.block.meta.BlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.RawBlockMeta;

public class ConcreteBlockMeta extends ConcreteRawBlockMeta implements BlockMeta {
    private final boolean redstonePowered;
    private final boolean indirectRedstonePowered;
    private final Direction direction;

    public ConcreteBlockMeta(final RawBlockMeta rawBlockMeta, final boolean redstonePowered,
            final boolean indirectRedstonePowered, final Direction direction) {
        super(rawBlockMeta);

        this.redstonePowered = redstonePowered;
        this.indirectRedstonePowered = indirectRedstonePowered;
        this.direction = direction;
    }

    public ConcreteBlockMeta(final BlockMeta blockMeta) {
        this(blockMeta, blockMeta.isRedstonePowered(), blockMeta.isIndirectRedstonePowered(), blockMeta.getDirection());
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.BlockMeta#isRedstonePowered()
     */
    @Override
    public boolean isRedstonePowered() {
        return this.redstonePowered;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.BlockMeta#isIndirectRedstonePowered()
     */
    @Override
    public boolean isIndirectRedstonePowered() {
        return this.indirectRedstonePowered;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.BlockMeta#getDirection()
     */
    @Override
    public Direction getDirection() {
        return this.direction;
    }
}
