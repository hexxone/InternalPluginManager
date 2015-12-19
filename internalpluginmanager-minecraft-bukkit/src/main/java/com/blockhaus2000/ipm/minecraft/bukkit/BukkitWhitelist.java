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

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;

import com.blockhaus2000.ipm.minecraft.OfflinePlayer;
import com.blockhaus2000.ipm.minecraft.Whitelist;
import com.blockhaus2000.ipm.minecraft.bukkit.util.converter.OfflinePlayerConverter;

/**
 * The Bukkit implementation of {@link Whitelist}.
 *
 */
public class BukkitWhitelist implements Whitelist {
    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Whitelist#add(com.blockhaus2000.ipm.minecraft.OfflinePlayer)
     */
    @Override
    public void add(final OfflinePlayer player) {
        OfflinePlayerConverter.convertToBukkitPlayer(player).setWhitelisted(true);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Whitelist#remove(com.blockhaus2000.ipm.minecraft.OfflinePlayer)
     */
    @Override
    public boolean remove(final OfflinePlayer player) {
        if (this.isWhitelisted(player)) {
            OfflinePlayerConverter.convertToBukkitPlayer(player).setWhitelisted(false);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Whitelist#isWhitelisted(com.blockhaus2000.ipm.minecraft.OfflinePlayer)
     */
    @Override
    public boolean isWhitelisted(final OfflinePlayer player) {
        return OfflinePlayerConverter.convertToBukkitPlayer(player).isWhitelisted();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Whitelist#reload()
     */
    @Override
    public void reload() {
        Bukkit.getServer().reloadWhitelist();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Whitelist#save()
     */
    @Override
    public void save() {
        // TODO Auto-generated method body.

    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Whitelist#getWhitelistedPlayers()
     */
    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers() {
        final Set<OfflinePlayer> result = new HashSet<OfflinePlayer>();
        for (final org.bukkit.OfflinePlayer player : Bukkit.getServer().getWhitelistedPlayers()) {
            result.add(OfflinePlayerConverter.convertToIpmPlayer(player));
        }
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Whitelist#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return Bukkit.getServer().hasWhitelist();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Whitelist#setEnabled(boolean)
     */
    @Override
    public void setEnabled(final boolean enabled) {
        Bukkit.getServer().setWhitelist(enabled);
    }
}
