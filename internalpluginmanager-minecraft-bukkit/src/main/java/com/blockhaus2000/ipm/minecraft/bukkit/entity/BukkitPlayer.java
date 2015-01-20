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
package com.blockhaus2000.ipm.minecraft.bukkit.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;

import com.blockhaus2000.ipm.minecraft.InternalPluginManager;
import com.blockhaus2000.ipm.minecraft.bukkit.BukkitOfflinePlayer;
import com.blockhaus2000.ipm.minecraft.bukkit.util.converter.InventoryConverter;
import com.blockhaus2000.ipm.minecraft.bukkit.util.converter.LocationConverter;
import com.blockhaus2000.ipm.minecraft.bukkit.util.converter.PlayerConverter;
import com.blockhaus2000.ipm.minecraft.command.CommandSenderType;
import com.blockhaus2000.ipm.minecraft.entity.Player;
import com.blockhaus2000.ipm.minecraft.inventory.Inventory;
import com.blockhaus2000.ipm.minecraft.util.WorldLocation;

/**
 * The Bukkit implementation of {@link Player}
 *
 */
public final class BukkitPlayer extends BukkitOfflinePlayer implements Player {
    /**
     * Constructor of BukkitPlayer.
     *
     * <p>
     * <b> NOTE: You must not call this constructor! Please use the
     * {@link PlayerConverter} instead. </b>
     * </p>
     *
     * @param bukkitPlayer
     *            The player to receive the information like {@link UUID} from.
     */
    public BukkitPlayer(final org.bukkit.entity.Player bukkitPlayer) {
        super(bukkitPlayer);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.entity.InventoryHolder#getInventory()
     */
    @Override
    public Inventory getInventory() {
        return InventoryConverter.convertToIpmInventory(this.getBukkitPlayer().getInventory());
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.command.CommandSender#getType()
     */
    @Override
    public CommandSenderType getType() {
        return CommandSenderType.PLAYER;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.CommandSender#hasPermission(java.lang.String)
     */
    @Override
    public boolean hasPermission(final String permission) {
        return this.getBukkitPlayer().hasPermission(permission);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.CommandSender#sendMessage(java.lang.String)
     */
    @Override
    public void sendMessage(final String message) {
        InternalPluginManager.getServer().getMessageManager().sendMessage(this, message);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.command.CommandSender#sendRawMessage(java.lang.String)
     */
    @Override
    public void sendRawMessage(final String message) {
        this.getBukkitPlayer().sendMessage(message);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.entity.Player#getLocation()
     */
    @Override
    public WorldLocation getLocation() {
        return LocationConverter.convertToIpmLocation(this.getBukkitPlayer().getLocation());
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.entity.Player#getIp()
     */
    @Override
    public String getIp() {
        return this.getBukkitPlayer().getAddress().getAddress().getHostAddress();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.entity.Player#isIpBanned()
     */
    @Override
    public boolean isIpBanned() {
        return InternalPluginManager.getServer().getIpBanlist().isBanned(this.getIp());
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.entity.Player#banIp(java.lang.String)
     */
    @Override
    public void banIp(final String reason) {
        InternalPluginManager.getServer().getIpBanlist().ban(this.getIp(), reason);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.entity.Player#unbanIp()
     */
    @Override
    public boolean unbanIp() {
        return InternalPluginManager.getServer().getIpBanlist().unban(this.getIp());
    }

    /**
     *
     * @return The wrapped Bukkit player.
     */
    private org.bukkit.entity.Player getBukkitPlayer() {
        return Bukkit.getServer().getPlayer(this.getUUID());
    }

    /**
     * The factory for players.
     *
     * @deprecated Please use the {@link PlayerConverter} instead.
     */
    @Deprecated
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
        private static final Map<UUID, Player> PLAYER_CACHE = new HashMap<UUID, Player>();

        /**
         * Constructor of BukkitPlayer.Factory.
         *
         */
        private Factory() {
            // Nothing to do.
        }

        /**
         * Creates a new {@link BukkitPlayer} that will be cached and is
         * wrapping the given {@link Player}.
         *
         * @param bukkitPlayer
         *            Bukkit's {@link org.bukkit.entity.Player} to wrap.
         * @return The newly created or loaded {@link BukkitPlayer} wrapping the
         *         given Bukkit player.
         */
        public static Player create(final org.bukkit.entity.Player bukkitPlayer) {
            assert bukkitPlayer != null : "BukkitPlayer cannot be null!";

            final UUID bukkitPlayerID = bukkitPlayer.getUniqueId();

            Player player = Factory.PLAYER_CACHE.get(bukkitPlayerID);
            if (player == null) {
                synchronized (Factory.LOCK) {
                    player = Factory.PLAYER_CACHE.get(bukkitPlayerID);
                    if (player == null) {
                        player = new BukkitPlayer(bukkitPlayer);
                        Factory.PLAYER_CACHE.put(bukkitPlayerID, player);
                    }
                }
            }

            return player;
        }

        /**
         * Fetches the player with the given {@link UUID} and delegates to
         * {@link BukkitPlayer.Factory#create(org.bukkit.entity.Player)}.
         *
         * @param bukkitPlayer
         *            The player {@link UUID}.
         * @return The return value of
         *         {@link BukkitPlayer.Factory#create(org.bukkit.entity.Player)}
         *         .
         * @see com.blockhaus2000.ipm.minecraft.bukkit.entity.BukkitPlayer.Factory#create(org.bukkit.entity.Player)
         */
        public static Player create(final UUID bukkitPlayer) {
            assert bukkitPlayer != null : "BukkitPlayer cannot be null!";

            return Factory.create(Bukkit.getServer().getPlayer(bukkitPlayer));
        }
    }
}
