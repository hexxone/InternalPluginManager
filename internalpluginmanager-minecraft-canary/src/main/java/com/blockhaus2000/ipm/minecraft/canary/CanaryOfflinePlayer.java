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

import java.util.UUID;

import com.blockhaus2000.ipm.minecraft.InternalPluginManager;
import com.blockhaus2000.ipm.minecraft.OfflinePlayer;

/**
 * The Canary implementation of {@link OfflinePlayer}.
 *
 */
public class CanaryOfflinePlayer implements OfflinePlayer {
    /**
     * The name of this offline player.
     *
     */
    private final String name;
    /**
     * The UUID of this offline player.
     *
     */
    private final UUID uuid;

    /**
     * Constructor of CanaryOfflinePlayer.
     *
     * @param canaryOfflinePlayer
     *            The Canary offline player to use/represent.
     */
    public CanaryOfflinePlayer(final net.canarymod.api.OfflinePlayer canaryOfflinePlayer) {
        assert canaryOfflinePlayer != null : "CanaryOfflinePlayer cannot be null!";

        this.name = canaryOfflinePlayer.getName();
        this.uuid = canaryOfflinePlayer.getUUID();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#getUUID()
     */
    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#isWhitelisted()
     */
    @Override
    public boolean isWhitelisted() {
        return InternalPluginManager.getServer().getWhitelist().isWhitelisted(this);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#setWhitelisted(boolean)
     */
    @Override
    public void setWhitelisted(final boolean whitelisted) {
        if (whitelisted) {
            InternalPluginManager.getServer().getWhitelist().add(this);
        } else {
            InternalPluginManager.getServer().getWhitelist().remove(this);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#isBanned()
     */
    @Override
    public boolean isBanned() {
        return InternalPluginManager.getServer().getBanlist().isBanned(this);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#ban(java.lang.String)
     */
    @Override
    public void ban(final String reason) {
        InternalPluginManager.getServer().getBanlist().ban(this, reason);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.OfflinePlayer#unban()
     */
    @Override
    public boolean unban() {
        return InternalPluginManager.getServer().getBanlist().unban(this);
    }
}
