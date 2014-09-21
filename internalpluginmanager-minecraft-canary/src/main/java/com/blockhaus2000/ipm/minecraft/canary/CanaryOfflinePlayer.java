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
package com.blockhaus2000.ipm.minecraft.canary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.stream.Location;

import net.canarymod.Canary;

import com.blockhaus2000.ipm.minecraft.InternalPluginManager;
import com.blockhaus2000.ipm.minecraft.OfflinePlayer;
import com.blockhaus2000.ipm.minecraft.bukkit.entity.Player;

public class CanaryOfflinePlayer implements OfflinePlayer {
    private static final SimpleDateFormat DEFAULT_SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    private final net.canarymod.api.OfflinePlayer offlinePlayer;

    private CanaryOfflinePlayer(final net.canarymod.api.OfflinePlayer offlinePlayer) {
        this.offlinePlayer = offlinePlayer;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#getUniqueId()
     */
    @Override
    public UUID getUniqueId() {
        return this.offlinePlayer.getUUID();
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
        return InternalPluginManager.getServer().getPlayer(this.offlinePlayer.getUUID());
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#getFirstPlayed()
     */
    @Override
    public long getFirstPlayed() {
        try {
            return CanaryOfflinePlayer.DEFAULT_SIMPLE_DATE_FORMAT.parse(this.offlinePlayer.getFirstJoined()).getTime();
        } catch (final ParseException dummy) {
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#getLastplayed()
     */
    @Override
    public long getLastplayed() {
        try {
            return CanaryOfflinePlayer.DEFAULT_SIMPLE_DATE_FORMAT.parse(this.offlinePlayer.getFirstJoined()).getTime();
        } catch (final ParseException dummy) {
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#isNew()
     */
    @Override
    public boolean isNew() {
        // TODO Auto-generated method body.
        return false;
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
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#isWhitelisted()
     */
    @Override
    public boolean isWhitelisted() {
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#setBanned(boolean)
     */
    @SuppressWarnings("deprecation")
    @Override
    public void setBanned(final boolean banned) {
        // TODO Auto-generated method body.

    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#setWhitelisted(boolean)
     */
    @Override
    public void setWhitelisted(final boolean whitelisted) {
        // TODO Auto-generated method body.

    }

    /**
     * This factory for players caches them and manages that not more than one
     * instance for one player UUID exist.
     *
     */
    public static final class CanaryOfflinePlayerFactory {
        /**
         * An object to create locks on.
         *
         */
        private static final Object LOCK = new Object();

        /**
         * The cache to manage player instantion (max. one CanaryOfflinePlayer
         * per name).
         *
         */
        private static final Map<String, CanaryOfflinePlayer> PLAYER_POOL = new HashMap<String, CanaryOfflinePlayer>();

        /**
         * Constructor of CanaryOfflinePlayerFactory.
         *
         */
        private CanaryOfflinePlayerFactory() {
            // Utility classes should not have a visible constructor.
        }

        /**
         * Creates of loads the CanaryOfflinePlayer with the given UUID.
         *
         * @param name
         *            The name of the CanaryOfflinePlayer.
         * @return The newly created or cached CanaryOfflinePlayer.
         */
        public static CanaryOfflinePlayer getCanaryOfflinePlayer(final String name) {
            CanaryOfflinePlayer result = CanaryOfflinePlayerFactory.PLAYER_POOL.get(name);
            if (result == null) {
                synchronized (CanaryOfflinePlayerFactory.LOCK) {
                    result = CanaryOfflinePlayerFactory.PLAYER_POOL.get(name);
                    if (result == null) {
                        result = new CanaryOfflinePlayer(Canary.getServer().getOfflinePlayer(name));
                        CanaryOfflinePlayerFactory.PLAYER_POOL.put(name, result);
                    }
                }
            }
            return result;
        }

        /**
         * Delegates to
         * {@link CanaryOfflinePlayerFactory#getCanaryOfflinePlayer(String)}
         * with <code>uuid = player.getUniqueId()</code>.
         *
         * @param player
         *            Is passed to
         *            {@link CanaryOfflinePlayerFactory#getCanaryOfflinePlayer(String)}
         *            .
         * @return See
         *         {@link CanaryOfflinePlayerFactory#getCanaryOfflinePlayer(String)}
         *         .
         * @see com.blockhaus2000.ipm.minecraft.canary.CanaryOfflinePlayer.CanaryOfflinePlayerFactory
         *      #getCanaryOfflinePlayer(java.lang.String)
         */
        public static CanaryOfflinePlayer getCanaryOfflinePlayer(final net.canarymod.api.OfflinePlayer player) {
            return CanaryOfflinePlayerFactory.getCanaryOfflinePlayer(player.getName());
        }
    }
}
