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

import net.canarymod.Canary;

import com.blockhaus2000.ipm.minecraft.Banlist;
import com.blockhaus2000.ipm.minecraft.OfflinePlayer;

/**
 * The Canary implementation of {@link Banlist}.
 *
 */
public class CanaryBanlist implements Banlist {
    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Banlist#ban(com.blockhaus2000.ipm.minecraft.OfflinePlayer,
     *      java.lang.String)
     */
    @Override
    public void ban(final OfflinePlayer player, final String reason) {
        // TODO Auto-generated method body.

    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Banlist#unban(com.blockhaus2000.ipm.minecraft.OfflinePlayer)
     */
    @Override
    public boolean unban(final OfflinePlayer player) {
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Banlist#isBanned(com.blockhaus2000.ipm.minecraft.OfflinePlayer)
     */
    @Override
    public boolean isBanned(final OfflinePlayer player) {
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Banlist#reload()
     */
    @Override
    public void reload() {
        Canary.bans().reload();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Banlist#save()
     */
    @Override
    public void save() {
        // TODO Auto-generated method body.

    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Banlist#getBannedPlayers()
     */
    @Override
    public Set<OfflinePlayer> getBannedPlayers() {
        // TODO Auto-generated method body.
        return null;
    }
}
