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
 */
package com.blockhaus2000.ipm.minecraft;

import java.util.Set;

/**
 * The ban list for this server.
 *
 */
public interface Banlist {
    /**
     * Bans the given player with the given reason.
     *
     * @param player
     *            The player to ban.
     * @param reason
     *            The reason why the player should be banned.
     */
    void ban(final OfflinePlayer player, final String reason);

    /**
     * Unbans the given player.
     *
     * @param player
     *            The player to unban.
     * @return Whether the unban was successful (not, if the player was not
     *         banned yet).
     */
    boolean unban(final OfflinePlayer player);

    /**
     * Checks whether the given player is banned.
     *
     * @param player
     *            The player to check for.
     * @return Whether the given player is banned or not.
     */
    boolean isBanned(final OfflinePlayer player);

    /**
     * Reloads the bans from disk.
     *
     */
    void reload();

    /**
     * Saves the bans to disk.
     *
     */
    void save();

    /**
     *
     * @return A set of all banned players.
     */
    Set<OfflinePlayer> getBannedPlayers();
}
