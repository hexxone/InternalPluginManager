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

import com.blockhaus2000.ipm.minecraft.OfflinePlayer;
import com.blockhaus2000.ipm.minecraft.Whitelist;

/**
 * The Canary implementation of {@link Whitelist}.
 *
 */
public class CanaryWhitelist implements Whitelist {
    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Whitelist#add(com.blockhaus2000.ipm.minecraft.OfflinePlayer)
     */
    @Override
    public void add(final OfflinePlayer player) {
        // TODO Auto-generated method body.

    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Whitelist#remove(com.blockhaus2000.ipm.minecraft.OfflinePlayer)
     */
    @Override
    public boolean remove(final OfflinePlayer player) {
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Whitelist#isWhitelisted(com.blockhaus2000.ipm.minecraft.OfflinePlayer)
     */
    @Override
    public boolean isWhitelisted(final OfflinePlayer player) {
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Whitelist#reload()
     */
    @Override
    public void reload() {
        // TODO Auto-generated method body.
        Canary.whitelist().reload();
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
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Whitelist#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Whitelist#setEnabled(boolean)
     */
    @Override
    public void setEnabled(final boolean enabled) {
        // TODO Auto-generated method body.

    }
}
