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
package com.blockhaus2000.ipm.minecraft.block;

import com.blockhaus2000.ipm.minecraft.Direction;
import com.blockhaus2000.ipm.minecraft.block.meta.BlockMeta;
import com.blockhaus2000.ipm.minecraft.world.World;

/**
 * A block in a {@link World}.
 *
 */
public interface Block {
    /**
     *
     * @return The {@link BlockMeta} of this block. This {@link BlockMeta} is an
     *         object of the class that is returned by
     *         {@link Block#getMaterial()}
     *         {@link BlockMaterial#getBlockMetaClass()}.
     */
    BlockMeta getMeta();

    /**
     *
     * @return The direction this block points to.
     */
    Direction getFaceDirection();

    /**
     *
     * @return The {@link BlockMaterial} of this block.
     */
    BlockMaterial getMaterial();
}
