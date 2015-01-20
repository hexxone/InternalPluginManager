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
package com.blockhaus2000.ipm.minecraft.bukkit.chunk;

import java.util.List;
import java.util.Set;

import com.blockhaus2000.ipm.minecraft.block.Block;
import com.blockhaus2000.ipm.minecraft.chunk.Chunk;
import com.blockhaus2000.ipm.minecraft.entity.Entity;
import com.blockhaus2000.ipm.minecraft.entity.Player;
import com.blockhaus2000.ipm.minecraft.util.WorldLocation;

/**
 * The Bukkit implementation of {@link Chunk}.
 *
 */
public class BukkitChunk implements Chunk {
    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.chunk.Chunk#getLocation()
     */
    @Override
    public WorldLocation getLocation() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.chunk.Chunk#isLoaded()
     */
    @Override
    public boolean isLoaded() {
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.chunk.Chunk#generate()
     */
    @Override
    public void generate() {
        // TODO Auto-generated method body.

    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.chunk.Chunk#load(boolean)
     */
    @Override
    public void load(final boolean generate) {
        // TODO Auto-generated method body.

    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.chunk.Chunk#load()
     */
    @Override
    public void load() {
        // TODO Auto-generated method body.

    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.chunk.Chunk#unload(boolean)
     */
    @Override
    public void unload(final boolean save) {
        // TODO Auto-generated method body.

    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.chunk.Chunk#unload()
     */
    @Override
    public void unload() {
        // TODO Auto-generated method body.

    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.chunk.Chunk#containsBlock(com.blockhaus2000.ipm.minecraft.block.Block)
     */
    @Override
    public boolean containsBlock(final Block block) {
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.chunk.Chunk#getEnities()
     */
    @Override
    public List<Entity> getEnities() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.chunk.Chunk#getPlayers()
     */
    @Override
    public Set<Player> getPlayers() {
        // TODO Auto-generated method body.
        return null;
    }
}
