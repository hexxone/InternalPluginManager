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
package com.blockhaus2000.ipm.minecraft.bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;

import com.blockhaus2000.ipm.minecraft.InternalPluginManager;
import com.blockhaus2000.ipm.minecraft.OfflinePlayer;

/**
 * The Bukkit implementation of {@link OfflinePlayer}.
 *
 */
public class BukkitOfflinePlayer implements OfflinePlayer {
    /**
     * The name of this player.
     *
     */
    private final String name;
    /**
     * The {@link UUID} of this player.
     *
     */
    private final UUID uuid;

    /**
     * Constructs a new pffline player and wraps the given offline player from
     * Bukkit.
     *
     * <p>
     * <b> NOTE: Do not invoke this constructor directly. Use the
     * {@link BukkitOfflinePlayer.Factory} methods instead. </b>
     * </p>
     *
     * @param bukkitOfflinePlayer
     *            The offline player from Bukkit to wrap.
     */
    protected BukkitOfflinePlayer(final org.bukkit.OfflinePlayer bukkitOfflinePlayer) {
        this.name = bukkitOfflinePlayer.getName().toLowerCase().trim();
        this.uuid = bukkitOfflinePlayer.getUniqueId();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#getUUID()
     */
    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#isWhitelisted()
     */
    @Override
    public boolean isWhitelisted() {
        return InternalPluginManager.getServer().getWhitelist().isWhitelisted(this);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#setWhitelisted(boolean)
     */
    @Override
    public void setWhitelisted(final boolean whitelisted) {
        if (whitelisted) {
            InternalPluginManager.getServer().getWhitelist().add(this);
        } else {
            InternalPluginManager.getServer().getWhitelist().remove(this);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#isBanned()
     */
    @Override
    public boolean isBanned() {
        return InternalPluginManager.getServer().getBanlist().isBanned(this);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#ban(java.lang.String)
     */
    @Override
    public void ban(final String reason) {
        InternalPluginManager.getServer().getBanlist().ban(this, reason);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#unban()
     */
    @Override
    public boolean unban() {
        return InternalPluginManager.getServer().getBanlist().unban(this);
    }

    /**
     * The factory for offline players.
     *
     */
    public static final class Factory {
        /**
         * The object to use to create locks.
         *
         */
        private static final Object LOCK = new Object();

        /**
         * The cache for player objects to reduce memory usage.
         *
         */
        private static final Map<UUID, OfflinePlayer> PLAYER_CACHE = new HashMap<UUID, OfflinePlayer>();

        /**
         * Constructor of BukkitOfflinePlayer.Factory.
         *
         */
        private Factory() {
            // Nothing to do.
        }

        /**
         * Creates a new {@link BukkitOfflinePlayer} that will be cached and is
         * wrapping the given {@link OfflinePlayer}.
         *
         * @param bukkitPlayer
         *            Bukkit's {@link org.bukkit.OfflinePlayer} to wrap.
         * @return The newly created or loaded {@link BukkitOfflinePlayer}
         *         wrapping the given Bukkit offline player.
         */
        public static OfflinePlayer create(final org.bukkit.OfflinePlayer bukkitPlayer) {
            final UUID bukkitPlayerID = bukkitPlayer.getUniqueId();

            OfflinePlayer player = Factory.PLAYER_CACHE.get(bukkitPlayerID);
            if (player == null) {
                synchronized (Factory.LOCK) {
                    player = Factory.PLAYER_CACHE.get(bukkitPlayerID);
                    if (player == null) {
                        player = new BukkitOfflinePlayer(bukkitPlayer);
                        Factory.PLAYER_CACHE.put(bukkitPlayerID, player);
                    }
                }
            }

            return player;
        }

        /**
         * Fetches the offline player with the given {@link UUID} and delegates
         * to
         * {@link BukkitOfflinePlayer.Factory#create(org.bukkit.OfflinePlayer)}.
         *
         * @param bukkitPlayer
         *            The offline player {@link UUID}.
         * @return The return value of
         *         {@link BukkitOfflinePlayer.Factory#create(org.bukkit.OfflinePlayer)}
         *         .
         * @see com.blockhaus2000.ipm.minecraft.bukkit.BukkitOfflinePlayer.Factory#create(org.bukkit.OfflinePlayer)
         */
        public static OfflinePlayer create(final UUID bukkitPlayer) {
            return Factory.create(Bukkit.getServer().getPlayer(bukkitPlayer));
        }
    }
}
