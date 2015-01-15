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
import com.blockhaus2000.ipm.minecraft.block.meta.ChestBlockMeta;
import com.blockhaus2000.ipm.minecraft.inventory.Inventory;

/**
 * Concrete {@link ChestBlockMeta} implementation.
 *
 */
public class ConcreteChestBlockMeta extends ConcreteBlockMeta implements ChestBlockMeta {
    /**
     * <code>inventory</code>
     *
     */
    private final Inventory inventory;
    /**
     * <code>open</code>
     *
     */
    private final boolean open;

    /**
     * Constructor of AbstractWeightedPressurePlateBlockMeta.
     *
     * @param blockMeta
     *            The {@link BlockMeta} that contains common information about
     *            this block (meta).
     * @param inventory
     *            <code>inventory</code>
     * @param open
     *            <code>open</code>
     */
    public ConcreteChestBlockMeta(final BlockMeta blockMeta, final Inventory inventory, final boolean open) {
        super(blockMeta);

        this.inventory = inventory;
        this.open = open;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.entity.InventoryHolder#getInventory()
     */
    @Override
    public Inventory getInventory() {
        return this.inventory;
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
}
