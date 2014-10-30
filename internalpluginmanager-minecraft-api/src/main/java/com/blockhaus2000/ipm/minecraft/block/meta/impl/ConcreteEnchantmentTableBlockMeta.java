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
import com.blockhaus2000.ipm.minecraft.block.meta.CarrotAndPotatoBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.EnchantmentTableBlockMeta;
import com.blockhaus2000.ipm.minecraft.enchantment.Enchantment;
import com.blockhaus2000.ipm.minecraft.inventory.Inventory;

/**
 * Concrete {@link CarrotAndPotatoBlockMeta} implementation.
 *
 */
public class ConcreteEnchantmentTableBlockMeta extends ConcreteBlockMeta implements EnchantmentTableBlockMeta {
    /**
     * <code>inventory</code>
     *
     */
    private final Inventory inventory;
    /**
     * <code>currentEnchantments</code>
     *
     */
    private final Enchantment[] currentEnchantments;

    /**
     * Constructor of AbstractWeightedPressurePlateBlockMeta.
     *
     * @param blockMeta
     *            The {@link BlockMeta} that contains common information about
     *            this block (meta).
     * @param inventory
     *            <code>inventory</code>
     * @param currentEnchantments
     *            <code>currentEnchantments</code>
     */
    public ConcreteEnchantmentTableBlockMeta(final BlockMeta blockMeta, final Inventory inventory,
            final Enchantment[] currentEnchantments) {
        super(blockMeta);

        this.inventory = inventory;
        this.currentEnchantments = currentEnchantments;
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
     * @see com.blockhaus2000.ipm.minecraft.block.meta.EnchantmentTableBlockMeta#getCurrentEnchantments()
     */
    @Override
    public Enchantment[] getCurrentEnchantments() {
        return this.currentEnchantments;
    }
}
