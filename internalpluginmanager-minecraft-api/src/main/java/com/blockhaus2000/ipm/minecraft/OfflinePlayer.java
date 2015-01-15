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
package com.blockhaus2000.ipm.minecraft;

import java.util.UUID;

import com.blockhaus2000.ipm.minecraft.entity.Player;

/**
 * A representation of {@link Player} for players that are not online but have
 * available data. This can be used to ban or unban players also if they are not
 * online, for example.
 *
 */
public interface OfflinePlayer {
    /**
     *
     * @return The name of this player.
     */
    String getName();

    /**
     *
     * @return The {@link UUID} of this player.
     */
    UUID getUUID();

    // Whitelist stuff.
    /**
     *
     * @return Whether this player is whitelisted or not.
     */
    boolean isWhitelisted();

    /**
     * Adds or removes this player to the whitelist.
     *
     * @param whitelisted
     *            Whether this player should be whitelisted or not.
     */
    void setWhitelisted(final boolean whitelisted);

    // Ban stuff.
    /**
     *
     * @return Whether this player is banned.
     */
    boolean isBanned();

    /**
     * Bans this player for the given reason.
     *
     * @param reason
     *            The reason why this player should be banned,
     */
    void ban(final String reason);

    /**
     * Unbans this player.
     *
     * @return Whether this player was unbanned successfully.
     */
    boolean unban();
}
