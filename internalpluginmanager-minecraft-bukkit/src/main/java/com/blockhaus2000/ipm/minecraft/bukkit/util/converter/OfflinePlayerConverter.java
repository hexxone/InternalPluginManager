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

import com.blockhaus2000.ipm.minecraft.OfflinePlayer;
import com.blockhaus2000.ipm.minecraft.bukkit.BukkitOfflinePlayer;

/**
 * A utility class to convert Bukkit {@link org.bukkit.OfflinePlayer}s into IPM
 * {@link OfflinePlayer}s and the other way.
 *
 */
public class OfflinePlayerConverter {
    /**
     * The object to create locks on.
     *
     */
    private static final Object LOCK = new Object();

    /**
     * The (offline) player cache to speed up the whole process.
     *
     */
    private static final Map<UUID, OfflinePlayer> CACHE = new HashMap<UUID, OfflinePlayer>();

    /**
     * Constructor of OfflinePlayerConverter.
     *
     */
    private OfflinePlayerConverter() {
        // Nothing to do.
    }

    /**
     * Converts the given Bukkit {@link org.bukkit.OfflinePlayer} into an IPM
     * {@link OfflinePlayer}.
     *
     * @param bukkitPlayer
     *            The Bukkit {@link org.bukkit.OfflinePlayer} to convert.
     * @return The converted IPM {@link OfflinePlayer}.
     */
    public static OfflinePlayer convertToIpmPlayer(final org.bukkit.OfflinePlayer bukkitPlayer) {
        OfflinePlayer player = OfflinePlayerConverter.CACHE.get(bukkitPlayer.getUniqueId());
        if (player == null) {
            synchronized (OfflinePlayerConverter.LOCK) {
                player = OfflinePlayerConverter.CACHE.get(bukkitPlayer.getUniqueId());
                if (player == null) {
                    player = BukkitOfflinePlayer.createNew(bukkitPlayer);
                    OfflinePlayerConverter.CACHE.put(bukkitPlayer.getUniqueId(), player);
                }
            }
        }
        return player;
    }

    /**
     * Converts the given IPM {@link OfflinePlayer} into a Bukkit
     * {@link org.bukkit.OfflinePlayer}.
     *
     * @param ipmPlayer
     *            The IPM {@link OfflinePlayer} to convert.
     * @return The converted Bukkit {@link org.bukkit.OfflinePlayer}.
     */
    public static org.bukkit.OfflinePlayer convertToBukkitPlayer(final OfflinePlayer ipmPlayer) {
        return Bukkit.getOfflinePlayer(ipmPlayer.getUUID());
    }
}
