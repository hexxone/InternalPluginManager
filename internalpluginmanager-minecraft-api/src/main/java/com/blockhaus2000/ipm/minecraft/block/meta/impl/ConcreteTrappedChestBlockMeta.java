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
import com.blockhaus2000.ipm.minecraft.block.meta.TrappedChestBlockMeta;
import com.blockhaus2000.ipm.minecraft.inventory.Inventory;

public class ConcreteTrappedChestBlockMeta extends ConcreteBlockMeta implements TrappedChestBlockMeta {
    private final boolean open;
    private final Inventory inventory;
    private final int outputStrength;

    public ConcreteTrappedChestBlockMeta(final BlockMeta blockMeta, final boolean open, final Inventory inventory,
            final int outputStrength) {
        super(blockMeta);

        this.open = open;
        this.inventory = inventory;
        this.outputStrength = outputStrength;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.ChestBlockMeta#isOpen()
     */
    @Override
    public boolean isOpen() {
        return this.open;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.Container#getInventory()
     */
    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.TrappedChestBlockMeta#getOutputStrength()
     */
    @Override
    public int getOutputStrength() {
        return this.outputStrength;
    }
}
