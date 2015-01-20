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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;

import com.blockhaus2000.ipm.minecraft.bukkit.entity.BukkitPlayer;
import com.blockhaus2000.ipm.minecraft.entity.Player;

/**
 * A utility class to convert Bukkit {@link org.bukkit.entity.Player}s into IPM
 * {@link Player}s and the other way.
 *
 */
public class PlayerConverter {
    /**
     * The object to create locks on.
     *
     */
    private static final Object LOCK = new Object();

    /**
     * The player cache to speed up the whole process.
     *
     */
    private static final Map<UUID, Player> CACHE = new HashMap<UUID, Player>();

    /**
     * Constructor of PlayerConverter.
     *
     */
    private PlayerConverter() {
        // Nothing to do.
    }

    /**
     * Converts the given Bukkit {@link org.bukkit.entity.Player} into an IPM
     * {@link Player}.
     *
     * @param bukkitPlayer
     *            The Bukkit {@link org.bukkit.entity.Player} to convert.
     * @return The converted IPM {@link Player}.
     */
    public static Player convertToIpmPlayer(final org.bukkit.entity.Player bukkitPlayer) {
        assert bukkitPlayer != null : "BukkitPlayer cannot be null!";

        Player player = PlayerConverter.CACHE.get(bukkitPlayer.getUniqueId());
        if (player == null) {
            synchronized (PlayerConverter.LOCK) {
                player = PlayerConverter.CACHE.get(bukkitPlayer.getUniqueId());
                if (player == null) {
                    player = new BukkitPlayer(bukkitPlayer);
                    PlayerConverter.CACHE.put(bukkitPlayer.getUniqueId(), player);
                }
            }
        }
        return player;
    }

    /**
     * Converts the given IPM {@link Player} into a Bukkit
     * {@link org.bukkit.entity.Player}.
     *
     * @param ipmPlayer
     *            The IPM {@link Player} to convert.
     * @return The converted Bukkit {@link org.bukkit.entity.Player}.
     */
    public static org.bukkit.entity.Player convertToBukkitPlayer(final Player ipmPlayer) {
        assert ipmPlayer != null : "IpmPlayer cannot be null!";

        return Bukkit.getPlayer(ipmPlayer.getUUID());
    }
}
