/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014 Fabian Damken
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
package com.blockhaus2000.ipm.minecraft.entity;

import com.blockhaus2000.ipm.minecraft.OfflinePlayer;
import com.blockhaus2000.ipm.minecraft.command.PlayerCommandSender;
import com.blockhaus2000.ipm.minecraft.util.WorldLocation;

/**
 * Represents a {@link Player}.
 *
 */
public interface Player extends HumanEntity, OfflinePlayer, PlayerCommandSender {
    /**
     *
     * @return The {@link WorldLocation} of this player.
     */
    WorldLocation getLocation();

    /**
     *
     * @return The IP of this player.
     */
    String getIp();

    // IP-ban stuff.
    /**
     *
     * @return Whether the IP of this player is banned.
     */
    boolean isIpBanned();

    /**
     * Bans the IP of this player for the given reason.
     *
     * @param reason
     *            The reason why the IP of this player should be banned.
     */
    void banIp(final String reason);

    /**
     * Unbans the IP of this player.
     *
     * @return Whether the IP was unbanned successfully.
     */
    boolean unbanIp();
}
