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

import javax.xml.stream.Location;

import org.bukkit.Bukkit;

import com.blockhaus2000.ipm.minecraft.OfflinePlayer;
import com.blockhaus2000.ipm.minecraft.bukkit.entity.BukkitPlayer.BukkitPlayerFactory;
import com.blockhaus2000.ipm.minecraft.bukkit.entity.Player;

public class BukkitOfflinePlayer implements OfflinePlayer {
    private final org.bukkit.OfflinePlayer offlinePlayer;

    private BukkitOfflinePlayer(final org.bukkit.OfflinePlayer offlinePlayer) {
        this.offlinePlayer = offlinePlayer;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#getUniqueId()
     */
    @Override
    public UUID getUniqueId() {
        return this.offlinePlayer.getUniqueId();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#getName()
     */
    @Deprecated
    @Override
    public String getName() {
        return this.offlinePlayer.getName().toLowerCase().trim();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#getPlayer()
     */
    @Override
    public Player getPlayer() {
        return BukkitPlayerFactory.getBukkitPlayer(this.offlinePlayer.getPlayer());
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#getFirstPlayed()
     */
    @Override
    public long getFirstPlayed() {
        return this.offlinePlayer.getFirstPlayed();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#getLastplayed()
     */
    @Override
    public long getLastplayed() {
        return this.offlinePlayer.getLastPlayed();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#isNew()
     */
    @Override
    public boolean isNew() {
        return !this.offlinePlayer.hasPlayedBefore();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#getBedSpawnLoaction()
     */
    @Override
    public Location getBedSpawnLoaction() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#isOnline()
     */
    @Override
    public boolean isOnline() {
        return this.offlinePlayer.isOnline();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#isBanned()
     */
    @Override
    public boolean isBanned() {
        return this.offlinePlayer.isBanned();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#isWhitelisted()
     */
    @Override
    public boolean isWhitelisted() {
        return this.offlinePlayer.isWhitelisted();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#setBanned(boolean)
     */
    @SuppressWarnings("deprecation")
    @Override
    public void setBanned(final boolean banned) {
        this.offlinePlayer.setBanned(true);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#setWhitelisted(boolean)
     */
    @Override
    public void setWhitelisted(final boolean whitelisted) {
        this.offlinePlayer.setWhitelisted(whitelisted);
    }

    /**
     * This factory for players caches them and manages that not more than one
     * instance for one player UUID exist.
     *
     */
    public static final class BukkitOfflinePlayerFactory {
        /**
         * An object to create locks on.
         *
         */
        private static final Object LOCK = new Object();

        /**
         * The cache to manage player instantion (max. one BukkitOfflinePlayer
         * per UUID).
         *
         */
        private static final Map<UUID, BukkitOfflinePlayer> PLAYER_POOL = new HashMap<UUID, BukkitOfflinePlayer>();

        /**
         * Constructor of BukkitOfflinePlayerFactory.
         *
         */
        private BukkitOfflinePlayerFactory() {
            // Utility classes should not have a visible constructor.
        }

        /**
         * Creates of loads the BukkitOfflinePlayer with the given UUID.
         *
         * @param uuid
         *            The {@link UUID} of the BukkitOfflinePlayer.
         * @return The newly created or cached BukkitOfflinePlayer.
         */
        public static BukkitOfflinePlayer getBukkitOfflinePlayer(final UUID uuid) {
            BukkitOfflinePlayer result = BukkitOfflinePlayerFactory.PLAYER_POOL.get(uuid);
            if (result == null) {
                synchronized (BukkitOfflinePlayerFactory.LOCK) {
                    result = BukkitOfflinePlayerFactory.PLAYER_POOL.get(uuid);
                    if (result == null) {
                        result = new BukkitOfflinePlayer(Bukkit.getServer().getPlayer(uuid));
                        BukkitOfflinePlayerFactory.PLAYER_POOL.put(uuid, result);
                    }
                }
            }
            return result;
        }

        /**
         * Delegates to
         * {@link BukkitOfflinePlayerFactory#getBukkitOfflinePlayer(UUID)} with
         * <code>uuid = player.getUniqueId()</code>.
         *
         * @param player
         *            Is passed to
         *            {@link BukkitOfflinePlayerFactory #getBukkitOfflinePlayer(UUID)}
         *            .
         * @return See
         *         {@link BukkitOfflinePlayerFactory #getBukkitOfflinePlayer(UUID)}
         *         .
         * @see com.blockhaus2000.ipm.minecraft.bukkit.BukkitOfflinePlayer.BukkitOfflinePlayerFactory
         *      #getBukkitOfflinePlayer(java.util.UUID)
         */
        public static BukkitOfflinePlayer getBukkitOfflinePlayer(final org.bukkit.OfflinePlayer player) {
            return BukkitOfflinePlayerFactory.getBukkitOfflinePlayer(player.getUniqueId());
        }
    }
}
