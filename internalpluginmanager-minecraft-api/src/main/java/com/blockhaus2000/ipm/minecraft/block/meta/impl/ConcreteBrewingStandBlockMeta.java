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
import com.blockhaus2000.ipm.minecraft.block.meta.BrewingStandBlockMeta;
import com.blockhaus2000.ipm.minecraft.inventory.Inventory;

public class ConcreteBrewingStandBlockMeta extends ConcreteBlockMeta implements BrewingStandBlockMeta {
    private final Inventory inventory;
    private final int remainingBrewingTime;
    private final boolean brewing;

    public ConcreteBrewingStandBlockMeta(final BlockMeta blockMeta, final Inventory inventory, final int remainingBrewingTime,
            final boolean brewing) {
        super(blockMeta);

        this.inventory = inventory;
        this.remainingBrewingTime = remainingBrewingTime;
        this.brewing = brewing;
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
     * @see com.blockhaus2000.ipm.minecraft.block.meta.BrewingStandBlockMeta#getRemainingBrewingTime()
     */
    @Override
    public long getRemainingBrewingTime() {
        return this.remainingBrewingTime;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.BrewingStandBlockMeta#isBrewing()
     */
    @Override
    public boolean isBrewing() {
        return this.brewing;
    }
}
