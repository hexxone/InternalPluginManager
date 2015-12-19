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
package com.blockhaus2000.ipm.minecraft.block.concrete;

import com.blockhaus2000.ipm.minecraft.Direction;
import com.blockhaus2000.ipm.minecraft.block.Block;
import com.blockhaus2000.ipm.minecraft.block.BlockMaterial;
import com.blockhaus2000.ipm.minecraft.block.meta.BlockMeta;

/**
 * Concrete {@link Block} implementation.
 *
 */
public class ConcreteBlock implements Block {
    /**
     * <code>meta</code>
     *
     */
    private final BlockMeta meta;
    /**
     * <code>faceDirection</code>
     *
     */
    private final Direction faceDirection;
    /**
     * <code>material</code>
     *
     */
    private final BlockMaterial material;

    /**
     * Constructor of ConcreteBlock.
     *
     * @param meta
     *            <code>meta</code>
     * @param faceDirection
     *            <code>faceDirection</code>
     * @param material
     *            <code>material</code>
     */
    public ConcreteBlock(final BlockMeta meta, final Direction faceDirection, final BlockMaterial material) {
        assert meta != null : "Meta cannot be null!";
        assert material != null : "Material cannot be null!";
        assert meta.getClass().equals(material.getBlockMetaClass()) : "The class of meta has to equals the "
                + "block meta class from material!";

        this.meta = meta;
        this.faceDirection = faceDirection;
        this.material = material;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.Block#getMeta()
     */
    @Override
    public BlockMeta getMeta() {
        return this.meta;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.Block#getFaceDirection()
     */
    @Override
    public Direction getFaceDirection() {
        return this.faceDirection;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.Block#getMaterial()
     */
    @Override
    public BlockMaterial getMaterial() {
        return this.material;
    }
}
