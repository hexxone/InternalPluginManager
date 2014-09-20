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
package com.blockhaus2000.ipm.minecraft.canary.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.canarymod.Canary;

import com.blockhaus2000.ipm.minecraft.bukkit.entity.Player;
import com.blockhaus2000.ipm.minecraft.canary.command.CanaryPlayerCommandSender;

/**
 * The implementation of {@link Player} for Canary.
 *
 */
public class CanaryPlayer extends CanaryPlayerCommandSender implements Player {
    /**
     * The {@link net.canarymod.api.entity.living.humanoid.Player} that is
     * wrapped within this class.
     *
     */
    private final net.canarymod.api.entity.living.humanoid.Player player;

    /**
     * Constructor of BukkitPlayer.
     *
     * @param player
     *            The {@link net.canarymod.api.entity.living.humanoid.Player} to
     *            wrap.
     */
    private CanaryPlayer(final net.canarymod.api.entity.living.humanoid.Player player) {
        this.player = player;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.CommandSender#hasPermission(java.lang.String)
     */
    @Override
    public boolean hasPermission(final String permission) {
        if (permission == null || permission.isEmpty() || this.player.hasPermission(permission)) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.bukkit.entity.Player#getName()
     */
    @Deprecated
    @Override
    public String getName() {
        return this.player.getName().toLowerCase().trim();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.bukkit.entity.Player#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return this.player.getDisplayName();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.bukkit.entity.Player#getUniqueId()
     */
    @Override
    public UUID getUniqueId() {
        return this.player.getUUID();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.bukkit.entity.Player#isOnline()
     */
    @Override
    public boolean isOnline() {
        return this.player.isOnline();
    }

    /**
     * This factory for players caches them and manages that not more than one
     * instance for one player UUID exist.
     *
     */
    public static final class CanaryPlayerFactory {
        /**
         * The cache to manage player instantion (max. one CanaryPlayer per
         * UUID).
         *
         */
        private static final Map<UUID, CanaryPlayer> PLAYER_POOL = new HashMap<UUID, CanaryPlayer>();

        /**
         * Constructor of BukkitPlayerFactory.
         *
         */
        private CanaryPlayerFactory() {
            // Utility classes should not have a visible constructor.
        }

        /**
         * Creates of loads the CanaryPlayer with the given UUID.
         *
         * @param uuid
         *            The {@link UUID} of the CanaryPlayer.
         * @return The newly created or cached CanaryPlayer.
         */
        public static CanaryPlayer getCanaryPlayer(final UUID uuid) {
            CanaryPlayer result = CanaryPlayerFactory.PLAYER_POOL.get(uuid);
            if (result == null) {
                result = new CanaryPlayer(Canary.getServer().getPlayerFromUUID(uuid));
                CanaryPlayerFactory.PLAYER_POOL.put(uuid, result);
            }
            return result;
        }

        /**
         * Delegates to {@link CanaryPlayerFactory#getCanaryPlayer(UUID)} with
         * <code>uuid = player.getUniqueId()</code>.
         *
         * @param player
         *            Is passed to
         *            {@link CanaryPlayerFactory#getCanaryPlayer(UUID)}.
         * @return See {@link CanaryPlayerFactory#getCanaryPlayer(UUID)}.
         * @see com.blockhaus2000.ipm.minecraft.canary.entity.CanaryPlayer.CanaryPlayerFactory#getCanaryPlayer(java.util.UUID)
         */
        public static CanaryPlayer getCanaryPlayer(final net.canarymod.api.entity.living.humanoid.Player player) {
            return CanaryPlayerFactory.getCanaryPlayer(player.getUUID());
        }
    }
}
