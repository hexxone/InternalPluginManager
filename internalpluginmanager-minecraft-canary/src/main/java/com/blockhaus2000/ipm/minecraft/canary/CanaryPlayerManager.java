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
package com.blockhaus2000.ipm.minecraft.canary;

import java.util.Set;
import java.util.UUID;

import net.canarymod.Canary;

import com.blockhaus2000.ipm.minecraft.OfflinePlayer;
import com.blockhaus2000.ipm.minecraft.PlayerManager;
import com.blockhaus2000.ipm.minecraft.canary.util.converter.OfflinePlayerConverter;
import com.blockhaus2000.ipm.minecraft.entity.Player;

/**
 * The Canary implementation of {@link PlayerManager}.
 *
 */
public class CanaryPlayerManager implements PlayerManager {
    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.PlayerManager#getPlayer(java.util.UUID)
     */
    @Override
    public Player getPlayer(final UUID uuid) {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.PlayerManager#getPlayer(java.lang.String)
     */
    @Deprecated
    @Override
    public Player getPlayer(final String name) {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.PlayerManager#getPlayers()
     */
    @Override
    public Set<Player> getPlayers() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.PlayerManager#getOfflinePlayer(java.util.UUID)
     */
    @Override
    public OfflinePlayer getOfflinePlayer(final UUID uuid) {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.PlayerManager#getOfflinePlayer(java.lang.String)
     */
    @Deprecated
    @Override
    public OfflinePlayer getOfflinePlayer(final String name) {
        return OfflinePlayerConverter.convertToIpmOfflinePlayer(Canary.getServer().getOfflinePlayer(name));
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.PlayerManager#getOfflinePlayers()
     */
    @Override
    public Set<OfflinePlayer> getOfflinePlayers() {
        // TODO Auto-generated method body.
        return null;
    }
}
