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
 */
package com.blockhaus2000.ipm.minecraft.chunk;

import java.util.List;
import java.util.Set;

import com.blockhaus2000.ipm.minecraft.block.Block;
import com.blockhaus2000.ipm.minecraft.entity.Entity;
import com.blockhaus2000.ipm.minecraft.entity.Player;
import com.blockhaus2000.ipm.minecraft.util.WorldLocation;
import com.blockhaus2000.ipm.minecraft.world.World;

/**
 * Represents a {@link Chunk} in a {@link World}.
 *
 */
public interface Chunk {
    /**
     *
     * @return The {@link WorldLocation} of this chunk.
     */
    WorldLocation getLocation();

    /**
     *
     * @return Whether this chunk is loaded or not.
     */
    boolean isLoaded();

    /**
     * Generates this chunk, if it was not generated yet.
     *
     */
    void generate();

    /**
     * Loads this chunk.
     *
     * @param generate
     *            Whether to generate this chunk, if it was not generated yet.
     */
    void load(final boolean generate);

    /**
     * Delegates to {@link Chunk#load(boolean)} with
     * <code>generate = true</code>.
     *
     */
    void load();

    /**
     * Unloads this chunk.
     *
     * @param save
     *            Whether to save this chunk to disk before unloading.
     */
    void unload(final boolean save);

    /**
     * Delegates to {@link Chunk#load(boolean)} with <code>save = true</code>.
     *
     */
    void unload();

    /**
     *
     * @param block
     *            The chunk to detect whether it is within this chunk or not.
     * @return Whether this chunk contains the given block or not.
     */
    boolean containsBlock(final Block block);

    /**
     *
     * @return A list of all entities that are contained by this chunk.
     */
    List<Entity> getEnities();

    /**
     *
     * @return A set of all players that are contained by this chunk.
     */
    Set<Player> getPlayers();
}
