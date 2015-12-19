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
package com.blockhaus2000.ipm.minecraft.bukkit;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;

import com.blockhaus2000.ipm.minecraft.OfflinePlayer;
import com.blockhaus2000.ipm.minecraft.PlayerManager;
import com.blockhaus2000.ipm.minecraft.bukkit.entity.BukkitPlayer;
import com.blockhaus2000.ipm.minecraft.bukkit.util.converter.OfflinePlayerConverter;
import com.blockhaus2000.ipm.minecraft.bukkit.util.converter.PlayerConverter;
import com.blockhaus2000.ipm.minecraft.entity.Player;

/**
 * The Bukkit implementation of {@link PlayerManager}.
 *
 */
public class BukkitPlayerManager implements PlayerManager {
    /**
     * Constructor of BukkitPlayerManager.
     *
     */
    public BukkitPlayerManager() {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.PlayerManager#getPlayer(java.util.UUID)
     */
    @Override
    public Player getPlayer(final UUID uuid) {
        assert uuid != null : "Uuid cannot be null!";

        return PlayerConverter.convertToIpmPlayer(Bukkit.getServer().getPlayer(uuid));
    }

    /**
     * {@inheritDoc}
     *
     * @deprecated Names are not longer unique since Minecraft <code>1.8</code>.
     * @see com.blockhaus2000.ipm.minecraft.PlayerManager#getPlayer(java.lang.String)
     */
    @Deprecated
    @Override
    public Player getPlayer(final String name) {
        assert name != null : "Name cannot be null!";

        return BukkitPlayer.Factory.create(Bukkit.getServer().getPlayer(name));
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.PlayerManager#getPlayers()
     */
    @Override
    public Set<Player> getPlayers() {
        final Set<Player> players = new HashSet<Player>();
        for (final org.bukkit.entity.Player player : Bukkit.getServer().getOnlinePlayers()) {
            players.add(PlayerConverter.convertToIpmPlayer(player));
        }
        return Collections.unmodifiableSet(players);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.PlayerManager#getOfflinePlayer(java.util.UUID)
     */
    @Override
    public OfflinePlayer getOfflinePlayer(final UUID uuid) {
        assert uuid != null : "Uuid cannot be null!";

        return OfflinePlayerConverter.convertToIpmPlayer(Bukkit.getOfflinePlayer(uuid));
    }

    /**
     * {@inheritDoc}
     *
     * @deprecated Names are not longer unique since Minecraft <code>1.8</code>.
     * @see com.blockhaus2000.ipm.minecraft.PlayerManager#getOfflinePlayer(java.lang.String)
     */
    @Deprecated
    @Override
    public OfflinePlayer getOfflinePlayer(final String name) {
        assert name != null : "Name cannot be null!";

        return BukkitOfflinePlayer.Factory.create(Bukkit.getServer().getOfflinePlayer(name));
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.PlayerManager#getOfflinePlayers()
     */
    @Override
    public Set<OfflinePlayer> getOfflinePlayers() {
        final Set<OfflinePlayer> offlinePlayers = new HashSet<OfflinePlayer>();
        for (final org.bukkit.OfflinePlayer offlinePlayer : Bukkit.getServer().getOfflinePlayers()) {
            offlinePlayers.add(OfflinePlayerConverter.convertToIpmPlayer(offlinePlayer));
        }
        return Collections.unmodifiableSet(offlinePlayers);
    }
}
