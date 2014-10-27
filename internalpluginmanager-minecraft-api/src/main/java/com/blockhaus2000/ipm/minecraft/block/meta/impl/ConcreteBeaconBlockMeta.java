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

import com.blockhaus2000.ipm.minecraft.block.meta.BeaconBlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.BlockMeta;
import com.blockhaus2000.ipm.minecraft.effect.Effect;
import com.blockhaus2000.ipm.minecraft.inventory.Inventory;

/**
 * Concrete {@link BeaconBlockMeta} implementation.
 *
 */
public class ConcreteBeaconBlockMeta extends ConcreteBlockMeta implements BeaconBlockMeta {
    /**
     * <code>inventory</code>
     *
     */
    private final Inventory inventory;
    /**
     * <code>radius</code>
     *
     */
    private final int radius;
    /**
     * <code>effects</code>
     *
     */
    private final Effect[] effects;

    /**
     * Constructor of AbstractWeightedPressurePlateBlockMeta.
     *
     * @param blockMeta
     *            The {@link BlockMeta} that contains common information about
     *            this block (meta).
     * @param inventory
     *            <code>inventory</code>
     * @param radius
     *            <code>radius</code>
     * @param effects
     *            <code>effects</code>
     */
    public ConcreteBeaconBlockMeta(final BlockMeta blockMeta, final Inventory inventory, final int radius, final Effect[] effects) {
        super(blockMeta);

        this.inventory = inventory;
        this.radius = radius;
        this.effects = effects;
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
     * @see com.blockhaus2000.ipm.minecraft.block.meta.BeaconBlockMeta#getRadius()
     */
    @Override
    public int getRadius() {
        return this.radius;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.BeaconBlockMeta#getEffects()
     */
    @Override
    public Effect[] getEffects() {
        return this.effects;
    }
}
