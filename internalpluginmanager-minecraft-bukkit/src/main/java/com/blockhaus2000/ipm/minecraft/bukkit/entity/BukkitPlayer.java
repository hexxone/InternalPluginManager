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
package com.blockhaus2000.ipm.minecraft.bukkit.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;

import com.blockhaus2000.ipm.minecraft.bukkit.command.BukkitPlayerCommandSender;

/**
 * The implementation of {@link Player} for Bukkit.
 *
 */
public class BukkitPlayer extends BukkitPlayerCommandSender implements Player {
    /**
     * The {@link org.bukkit.entity.Player} that is wrapped within this class.
     *
     */
    private final org.bukkit.entity.Player craftBukkitPlayer;

    /**
     * Constructor of BukkitPlayer.
     *
     * @param craftBukkitPlayer
     *            The {@link org.bukkit.entity.Player} to wrap.
     */
    private BukkitPlayer(final org.bukkit.entity.Player craftBukkitPlayer) {
        this.craftBukkitPlayer = craftBukkitPlayer;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.CommandSender#hasPermission(java.lang.String)
     */
    @Override
    public boolean hasPermission(final String permission) {
        if (permission == null || permission.isEmpty() || this.craftBukkitPlayer.hasPermission(permission)) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @deprecated See {@link Player#getName()}
     * @see com.blockhaus2000.ipm.minecraft.bukkit.entity.Player#getName()
     */
    @Deprecated
    @Override
    public String getName() {
        return this.craftBukkitPlayer.getName().toLowerCase().trim();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.bukkit.entity.Player#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return this.craftBukkitPlayer.getDisplayName();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.bukkit.entity.Player#getUniqueId()
     */
    @Override
    public UUID getUniqueId() {
        return this.craftBukkitPlayer.getUniqueId();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.bukkit.entity.Player#isOnline()
     */
    @Override
    public boolean isOnline() {
        return this.craftBukkitPlayer.isOnline();
    }

    public static final class BukkitPlayerFactory {
        private static final Map<UUID, BukkitPlayer> PLAYER_POOL = new HashMap<UUID, BukkitPlayer>();

        /**
         * Constructor of BukkitPlayerFactory.
         *
         */
        private BukkitPlayerFactory() {
            // Utility classes should not have a visible constructor.
        }

        public static BukkitPlayer getBukkitPlayer(final UUID uuid) {
            BukkitPlayer result = BukkitPlayerFactory.PLAYER_POOL.get(uuid);
            if (result == null) {
                result = new BukkitPlayer(Bukkit.getServer().getPlayer(uuid));
            }
            return result;
        }

        public static BukkitPlayer getBukkitPlayer(final org.bukkit.entity.Player player) {
            return BukkitPlayerFactory.getBukkitPlayer(player.getUniqueId());
        }
    }
}
