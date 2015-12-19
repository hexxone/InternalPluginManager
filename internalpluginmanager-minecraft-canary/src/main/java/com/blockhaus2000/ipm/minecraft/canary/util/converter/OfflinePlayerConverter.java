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
package com.blockhaus2000.ipm.minecraft.canary.util.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.canarymod.Canary;

import com.blockhaus2000.ipm.minecraft.OfflinePlayer;
import com.blockhaus2000.ipm.minecraft.canary.CanaryOfflinePlayer;

/**
 * This class is used to convert Canary {@link net.canarymod.api.OfflinePlayer}s
 * into IPM {@link OfflinePlayer}s.
 *
 */
public class OfflinePlayerConverter {
    /**
     * The object to create locks on.
     *
     */
    private static final Object LOCK = new Object();

    /**
     * The cache for offline players.
     *
     */
    private static final Map<UUID, OfflinePlayer> PLAYER_CACHE = new HashMap<UUID, OfflinePlayer>();

    /**
     * Converts the given Canary {@link net.canarymod.api.OfflinePlayer} into an
     * IPM {@link OfflinePlayer}.
     *
     * @param canaryOfflinePlayer
     *            The Canary {@link net.canarymod.api.OfflinePlayer} to convert.
     * @return The converted IPM {@link OfflinePlayer}.
     */
    public static OfflinePlayer convertToIpmOfflinePlayer(final net.canarymod.api.OfflinePlayer canaryOfflinePlayer) {
        assert canaryOfflinePlayer != null : "CanaryOfflinePlayer cannot be null!";

        final UUID uuid = canaryOfflinePlayer.getUUID();
        if (OfflinePlayerConverter.PLAYER_CACHE.get(canaryOfflinePlayer.getUUID()) == null) {
            synchronized (OfflinePlayerConverter.LOCK) {
                if (OfflinePlayerConverter.PLAYER_CACHE.get(canaryOfflinePlayer.getUUID()) == null) {
                    final OfflinePlayer offlinePlayer = new CanaryOfflinePlayer(canaryOfflinePlayer);
                    OfflinePlayerConverter.PLAYER_CACHE.put(uuid, offlinePlayer);
                }
            }
        }
        return OfflinePlayerConverter.PLAYER_CACHE.get(canaryOfflinePlayer.getUUID());
    }

    /**
     * Converts the given IPM {@link OfflinePlayer} into a Canary
     * {@link net.canarymod.api.OfflinePlayer}.
     *
     * @param ipmOfflinePlayer
     *            The IPM {@link OfflinePlayer} to convert.
     * @return The converted Canary {@link net.canarymod.api.OfflinePlayer}.
     */
    public static net.canarymod.api.OfflinePlayer convertToCanaryOfflinePlayer(final OfflinePlayer ipmOfflinePlayer) {
        return Canary.getServer().getOfflinePlayer(ipmOfflinePlayer.getName());
    }
}
