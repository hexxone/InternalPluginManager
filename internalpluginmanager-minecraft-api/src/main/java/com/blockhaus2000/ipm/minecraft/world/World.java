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
package com.blockhaus2000.ipm.minecraft.world;

import java.util.Set;
import java.util.UUID;

import com.blockhaus2000.ipm.minecraft.GameRule;
import com.blockhaus2000.ipm.minecraft.block.Block;
import com.blockhaus2000.ipm.minecraft.chunk.Chunk;
import com.blockhaus2000.ipm.minecraft.util.Vector;

public interface World {
    UUID getUUID();

    String getName();

    void setBlock(final Block block);

    Block getBlockAt(final Vector loc);

    Block getBlockAt(final int x, final int y, final int z);

    Block getHighestBlockAt(final Vector loc);

    Block getHighestBlockAt(final int x, final int z);

    Chunk getChunkContainingLocation(final Vector loc);

    Chunk getChunkAt(final Vector loc);

    Chunk getChunkAt(final int x, final int z);

    Chunk getChunkAt(final Block block);

    Set<GameRule> getGameRules();
}
