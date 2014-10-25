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
package com.blockhaus2000.ipm.minecraft.block.meta.concrete;

import com.blockhaus2000.ipm.minecraft.Direction;
import com.blockhaus2000.ipm.minecraft.block.meta.BlockMeta;

public class ConcreteBlockMeta implements BlockMeta {
    private final int lightLevel;
    private final int explosionResistance;
    private final int severity;

    private final boolean gravity;
    private final boolean solid;
    private final boolean redstonePowered;
    private final boolean indirectRedstonePowered;

    private final Direction direction;

    public ConcreteBlockMeta(final int lightLevel, final int explosionResistance, final int severity, final boolean gravity,
            final boolean solid, final boolean redstonePowered, final boolean indirectRedstonePowered, final Direction direction) {
        this.lightLevel = lightLevel;
        this.explosionResistance = explosionResistance;
        this.severity = severity;
        this.gravity = gravity;
        this.solid = solid;
        this.redstonePowered = redstonePowered;
        this.indirectRedstonePowered = indirectRedstonePowered;
        this.direction = direction;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.BlockMeta#getLightLevel()
     */
    @Override
    public int getLightLevel() {
        return this.lightLevel;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.BlockMeta#getExplosionResistance()
     */
    @Override
    public int getExplosionResistance() {
        return this.explosionResistance;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.BlockMeta#getSeverity()
     */
    @Override
    public int getSeverity() {
        return this.severity;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.BlockMeta#hasGravity()
     */
    @Override
    public boolean hasGravity() {
        return this.gravity;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.BlockMeta#isSolid()
     */
    @Override
    public boolean isSolid() {
        return this.solid;
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
