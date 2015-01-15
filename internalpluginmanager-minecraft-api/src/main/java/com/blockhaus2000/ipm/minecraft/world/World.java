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
package com.blockhaus2000.ipm.minecraft.world;

import java.util.Set;
import java.util.UUID;

import com.blockhaus2000.ipm.minecraft.GameRule;
import com.blockhaus2000.ipm.minecraft.block.Block;
import com.blockhaus2000.ipm.minecraft.chunk.Chunk;
import com.blockhaus2000.ipm.minecraft.util.Vector;

/**
 * Represents a Minecraft world build of {@link Block}s.
 *
 */
public interface World {
    /**
     *
     * @return The {@link UUID} of this world.
     */
    UUID getUUID();

    /**
     *
     * @return The name of this world.
     */
    String getName();

    /**
     * Sets the given {@link Block} at the given vector.
     *
     * @param vector
     *            The vector where to set the {@link Block}.
     * @param block
     *            The block that will be setted at the given vector.
     */
    void setBlockAt(final Vector vector, final Block block);

    /**
     * Sets the given {@link Block} at the given coordinates.
     *
     * @param x
     *            The x coordinate.
     * @param y
     *            The y coordinate.
     * @param z
     *            The z coordinate.
     * @param block
     *            The block that will be setted at the given coordinates.
     */
    void setBlockAt(final int x, final int y, final int z, final Block block);

    /**
     *
     * @param vector
     *            The {@link Vector} to get the {@link Block} from.
     * @return The {@link Block} that is placed at the given {@link Vector}.
     */
    Block getBlockAt(final Vector vector);

    /**
     *
     * @param x
     *            The x coordinate
     * @param y
     *            The y coordinate
     * @param z
     *            The z coordinate
     * @return The {@link Block} that is placed at the given coordinates.
     */
    Block getBlockAt(final int x, final int y, final int z);

    /**
     * Gets the highest non-air {@link Block} at the given {@link Vector}.
     *
     * @param vector
     *            The {@link Vector} from where to find the highest
     *            {@link Block}.
     * @return The highest block at the given {@link Vector}.
     */
    Block getHighestBlockAt(final Vector vector);

    /**
     * Gets the highest non-air {@link Block} at the given coordinate.
     *
     * @param x
     *            The x coordinate.
     * @param z
     *            The z coordinate.
     * @return The highest block at the given {@link Vector}.
     */
    Block getHighestBlockAt(final int x, final int z);

    /**
     * Searchs for the chunk that contains the given {@link Vector}.
     *
     * @param vector
     *            The {@link Vector} to search for.
     * @return The {@link Chunk} that contains the given {@link Vector}.
     */
    Chunk getChunkContainingLocation(final Vector vector);

    /**
     *
     * @param vector
     *            The {@link Vector} of the {@link Chunk}.
     * @return The {@link Chunk} that represents the given {@link Vector}.
     */
    Chunk getChunkAt(final Vector vector);

    /**
     *
     * @param x
     *            The x coordinate.
     * @param z
     *            The z coordinate.
     * @return The {@link Chunk} that represents the given coordinates.
     */
    Chunk getChunkAt(final int x, final int z);

    /**
     *
     * @return All {@link GameRule}s that are setted for this world.
     */
    Set<GameRule<?>> getGameRules();
}
