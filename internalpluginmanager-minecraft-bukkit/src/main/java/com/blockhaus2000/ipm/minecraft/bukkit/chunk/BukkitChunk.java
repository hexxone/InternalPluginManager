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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.blockhaus2000.ipm.minecraft.InternalPluginManager;
import com.blockhaus2000.ipm.minecraft.block.Block;
import com.blockhaus2000.ipm.minecraft.bukkit.util.converter.ChunkConverter;
import com.blockhaus2000.ipm.minecraft.bukkit.util.converter.EntityConverter;
import com.blockhaus2000.ipm.minecraft.bukkit.util.converter.WorldConverter;
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
     * The wrapped Bukkit chunk.
     *
     */
    private final org.bukkit.Chunk bukkitChunk;

    /**
     * Constructor of BukkitChunk.
     *
     * <p>
     * <b> NOTE: You must not call this method! Please use the
     * {@link ChunkConverter} instead. </b>
     * </p>
     *
     * @param bukkitChunk
     *            The Bukkit chunk to use.
     */
    public BukkitChunk(final org.bukkit.Chunk bukkitChunk) {
        assert bukkitChunk != null : "BukkitChunk cannot be null!";

        this.bukkitChunk = bukkitChunk;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.chunk.Chunk#getLocation()
     */
    @Override
    public WorldLocation getLocation() {
        return new WorldLocation(this.bukkitChunk.getX(), -1, this.bukkitChunk.getZ(),
                WorldConverter.convertToIpmWorld(this.bukkitChunk.getWorld()));
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.chunk.Chunk#isLoaded()
     */
    @Override
    public boolean isLoaded() {
        return this.bukkitChunk.isLoaded();
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
        this.bukkitChunk.load(generate);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.chunk.Chunk#load()
     */
    @Override
    public void load() {
        this.load(true);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.chunk.Chunk#unload(boolean)
     */
    @Override
    public void unload(final boolean save) {
        this.bukkitChunk.unload(save);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.chunk.Chunk#unload()
     */
    @Override
    public void unload() {
        this.unload(true);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.chunk.Chunk#containsBlock(com.blockhaus2000.ipm.minecraft.block.Block)
     */
    @SuppressWarnings("deprecation")
    @Override
    public boolean containsBlock(final Block block) {
        final int id = block.getMaterial().getMaterialId();
        final byte data = block.getMaterial().getMaterialData();
        for (int x = 0; x <= 15; x++) {
            for (int y = 0; y <= 127; y++) {
                for (int z = 0; z <= 15; z++) {
                    if (this.bukkitChunk.getBlock(x, y, z).getTypeId() == id
                            && this.bukkitChunk.getBlock(x, y, z).getData() == data) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.chunk.Chunk#getEnities()
     */
    @Override
    public List<Entity> getEnities() {
        final List<Entity> result = new ArrayList<Entity>();
        for (final org.bukkit.entity.Entity entity : this.bukkitChunk.getEntities()) {
            result.add(EntityConverter.convertToIpmEntity(entity));
        }
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.chunk.Chunk#getPlayers()
     */
    @Override
    public Set<Player> getPlayers() {
        final Set<Player> result = new HashSet<Player>();
        for (final Player player : InternalPluginManager.getServer().getPlayerManager().getPlayers()) {
            // TODO: This can only be implemented if the IPM player has the
            // possibility to get the location of the player.
        }
        return result;
    }
}
