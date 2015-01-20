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

import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;

import com.blockhaus2000.ipm.minecraft.IpBanlist;

/**
 * The Bukkit implementation of {@link IpBanlist}.
 *
 */
public class BukkitIpBanlist implements IpBanlist {
    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.IpBanlist#ban(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public void ban(final String ip, final String reason) {
        Bukkit.getServer().getBanList(BanList.Type.IP).addBan(ip, reason, null, null);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.IpBanlist#unban(java.lang.String)
     */
    @Override
    public boolean unban(final String ip) {
        if (this.isBanned(ip)) {
            Bukkit.getServer().getBanList(BanList.Type.IP).pardon(ip);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.IpBanlist#isBanned(java.lang.String)
     */
    @Override
    public boolean isBanned(final String ip) {
        return Bukkit.getServer().getBanList(BanList.Type.IP).isBanned(ip);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.IpBanlist#reload()
     */
    @Override
    public void reload() {
        // TODO Auto-generated method body.

    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.IpBanlist#save()
     */
    @Override
    public void save() {
        // TODO Auto-generated method body.

    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.IpBanlist#getBannedIps()
     */
    @Override
    public Set<String> getBannedIps() {
        final Set<String> result = new HashSet<String>();
        for (final BanEntry banEntry : Bukkit.getServer().getBanList(BanList.Type.IP).getBanEntries()) {
            result.add(banEntry.getTarget());
        }
        return result;
    }
}
