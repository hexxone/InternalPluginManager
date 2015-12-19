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
package com.blockhaus2000.ipm.minecraft.bukkit.util.converter;

import com.blockhaus2000.ipm.minecraft.chunk.Chunk;

/**
 * A utility class to convert a Bukkit {@link org.bukkit.Chunk} into an IPM
 * {@link Chunk}s and the other way.
 *
 */
public class ChunkConverter {
    /**
     * Constructor of ChunkConverter.
     *
     */
    private ChunkConverter() {
        // Nothing to do.
    }

    /**
     * Converts the given Bukkit {@link org.bukkit.Chunk} into an IPM
     * {@link Chunk}.
     *
     * @param bukkitChunk
     *            The Bukkit {@link org.bukkit.Chunk} to convert.
     * @return The converted IPM {@link Chunk}.
     */
    public static Chunk convertToIpmChunk(final org.bukkit.Chunk bukkitChunk) {
        assert bukkitChunk != null : "BukkitChunk cannot be null!";

        return null; // TODO
    }

    /**
     * Converts the given IPM {@link Chunk} into an Bukkit {@link Chunk}.
     *
     * @param ipmChunk
     *            The converted IPM {@link Chunk}.
     * @return The Bukkit {@link org.bukkit.Chunk} to convert.
     */
    public static org.bukkit.Chunk convertToBukkitChunk(final Chunk ipmChunk) {
        assert ipmChunk != null : "IpmChunk cannot be null!";

        return null; // TODO
    }
}
