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
package com.blockhaus2000.ipm.minecraft.bukkit.world;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;

import com.blockhaus2000.ipm.minecraft.GameRule;
import com.blockhaus2000.ipm.minecraft.block.Block;
import com.blockhaus2000.ipm.minecraft.bukkit.util.converter.BlockConverter;
import com.blockhaus2000.ipm.minecraft.bukkit.util.converter.WorldConverter;
import com.blockhaus2000.ipm.minecraft.chunk.Chunk;
import com.blockhaus2000.ipm.minecraft.util.Vector;
import com.blockhaus2000.ipm.minecraft.util.WorldLocation;
import com.blockhaus2000.ipm.minecraft.world.World;

/**
 * The Bukkit implementation of {@link World}.
 *
 */
public final class BukkitWorld implements World {
    /**
     * The {@link UUID} of this world.
     *
     */
    private final UUID uuid;
    /**
     * The name of this world.
     *
     */
    private final String name;

    /**
     * Constructs a new world that wraps the given Bukkit
     * {@link org.bukkit.World}.
     *
     * <p>
     * <b> NOTE: Do not invoke this constructor directly. Use the
     * {@link BukkitWorld.Factory} instead. </b>
     * </p>
     *
     * @param bukkitWorld
     *            The Bukkit {@link org.bukkit.World} to wrap.
     */
    private BukkitWorld(final org.bukkit.World bukkitWorld) {
        assert bukkitWorld != null : "BukkitWorld cannot be null!";

        this.uuid = bukkitWorld.getUID();
        this.name = bukkitWorld.getName().toLowerCase().trim();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.world.World#getUUID()
     */
    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.world.World#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.world.World#setBlockAt(com.blockhaus2000.ipm.minecraft.util.Vector,
     *      com.blockhaus2000.ipm.minecraft.block.Block)
     */
    @Override
    public void setBlockAt(final Vector vector, final Block block) {
        assert vector != null : "Vector cannot be null!";
        assert block != null : "Block cannot be null!";

        BlockConverter.convertToBukkitBlock(block, new WorldLocation(vector, this));
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.world.World#setBlockAt(int, int,
     *      int, com.blockhaus2000.ipm.minecraft.block.Block)
     */
    @Override
    public void setBlockAt(final int x, final int y, final int z, final Block block) {
        this.setBlockAt(new Vector(x, y, z), block);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.world.World#getBlockAt(int, int,
     *      int)
     */
    @Override
    public Block getBlockAt(final int x, final int y, final int z) {
        return BlockConverter.convertToIpmBlock(this.getBukkitWorld().getBlockAt(x, y, z));
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.world.World#getBlockAt(com.blockhaus2000.ipm.minecraft.util.Vector)
     */
    @Override
    public Block getBlockAt(final Vector vector) {
        assert vector != null : "Vector cannot be null!";

        return this.getBlockAt(vector.getX(), vector.getY(), vector.getZ());
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.world.World#getHighestBlockAt(int,
     *      int)
     */
    @Override
    public Block getHighestBlockAt(final int x, final int z) {
        return BlockConverter.convertToIpmBlock(this.getBukkitWorld().getHighestBlockAt(x, z));
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.world.World#getHighestBlockAt(com.blockhaus2000.ipm.minecraft.util.Vector)
     */
    @Override
    public Block getHighestBlockAt(final Vector vector) {
        assert vector != null : "Vector cannot be null!";

        return this.getHighestBlockAt(vector.getY(), vector.getZ());
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.world.World#getChunkContainingLocation(com.blockhaus2000.ipm.minecraft.util.Vector)
     */
    @Override
    public Chunk getChunkContainingLocation(final Vector vector) {
        assert vector != null : "Vector cannot be null!";

        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.world.World#getChunkAt(com.blockhaus2000.ipm.minecraft.util.Vector)
     */
    @Override
    public Chunk getChunkAt(final Vector vector) {
        assert vector != null : "Vector cannot be null!";

        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.world.World#getChunkAt(int, int)
     */
    @Override
    public Chunk getChunkAt(final int x, final int z) {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.world.World#getGameRules()
     */
    @Override
    public Set<GameRule<?>> getGameRules() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     *
     * @return The wrapped bukkit {@link org.bukkit.World}.
     */
    private org.bukkit.World getBukkitWorld() {
        return Bukkit.getServer().getWorld(this.getUUID());
    }

    /**
     * The factory for worlds.
     *
     * @deprecated Please use the {@link WorldConverter} instead.
     */
    @Deprecated
    public static final class Factory {
        /**
         * The object to use to create locks.
         *
         */
        private static final Object LOCK = new Object();

        /**
         * The cache for worlds to reduce memory usage.
         *
         */
        private static final Map<UUID, World> PLAYER_CACHE = new HashMap<UUID, World>();

        /**
         * Constructor of BukkitWorld.Factory.
         *
         */
        private Factory() {
            // Nothing to do.
        }

        /**
         * Creates a new {@link BukkitWorld} that will be cached and is wrapping
         * the given {@link org.bukkit.World}.
         *
         * @param bukkitWorld
         *            Bukkit's {@link org.bukkit.World} to wrap.
         * @return The newly created or loaded {@link BukkitWorld} wrapping the
         *         given Bukkit {@link org.bukkit.World}.
         */
        public static World create(final org.bukkit.World bukkitWorld) {
            assert bukkitWorld != null : "BukkitWorld cannot be null!";

            final UUID bukkitWorldID = bukkitWorld.getUID();

            World player = Factory.PLAYER_CACHE.get(bukkitWorldID);
            if (player == null) {
                synchronized (Factory.LOCK) {
                    player = Factory.PLAYER_CACHE.get(bukkitWorldID);
                    if (player == null) {
                        player = new BukkitWorld(bukkitWorld);
                        Factory.PLAYER_CACHE.put(bukkitWorldID, player);
                    }
                }
            }

            return player;
        }

        /**
         * Fetches the world with the given {@link UUID} and delegates to
         * {@link BukkitWorld.Factory#create(org.bukkit.World)}.
         *
         * @param bukkitWorld
         *            The world {@link UUID}.
         * @return The return value of
         *         {@link BukkitWorld.Factory#create(org.bukkit.World)} .
         * @see com.blockhaus2000.ipm.minecraft.bukkit.world.BukkitWorld.Factory#create(org.bukkit.World)
         */
        public static World create(final UUID bukkitWorld) {
            assert bukkitWorld != null : "BukkitWorld cannot be null!";

            return Factory.create(Bukkit.getServer().getWorld(bukkitWorld));
        }
    }
}
