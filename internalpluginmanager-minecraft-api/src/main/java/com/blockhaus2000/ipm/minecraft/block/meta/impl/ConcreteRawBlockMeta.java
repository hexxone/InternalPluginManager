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

import com.blockhaus2000.ipm.minecraft.block.meta.RawBlockMeta;

public class ConcreteRawBlockMeta implements RawBlockMeta {
    private final int lightLevel;
    private final double explosionResistance;
    private final double severity;
    private final boolean gravity;
    private final boolean solid;
    private final boolean transparent;

    public ConcreteRawBlockMeta(final int lightLevel, final double explosionResistance, final double severity,
            final boolean gravity, final boolean solid, final boolean transparent) {
        this.lightLevel = lightLevel;
        this.explosionResistance = explosionResistance;
        this.severity = severity;
        this.gravity = gravity;
        this.solid = solid;
        this.transparent = transparent;
    }

    public ConcreteRawBlockMeta(final RawBlockMeta rawBlockMeta) {
        this(rawBlockMeta.getLightLevel(), rawBlockMeta.getExplosionResistance(), rawBlockMeta.getSeverity(), rawBlockMeta
                .hasGravity(), rawBlockMeta.isSolid(), rawBlockMeta.isTransparent());
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.RawBlockMeta#getLightLevel()
     */
    @Override
    public int getLightLevel() {
        return this.lightLevel;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.RawBlockMeta#getExplosionResistance()
     */
    @Override
    public double getExplosionResistance() {
        return this.explosionResistance;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.RawBlockMeta#getSeverity()
     */
    @Override
    public double getSeverity() {
        return this.severity;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.RawBlockMeta#hasGravity()
     */
    @Override
    public boolean hasGravity() {
        return this.gravity;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.RawBlockMeta#isSolid()
     */
    @Override
    public boolean isSolid() {
        return this.solid;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.RawBlockMeta#isTransparent()
     */
    @Override
    public boolean isTransparent() {
        return this.transparent;
    }
}
