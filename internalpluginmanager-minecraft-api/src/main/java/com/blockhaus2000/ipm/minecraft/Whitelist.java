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
 * The whitelist of this server.
 *
 */
public interface Whitelist {
    /**
     * Adds the given player to the whitelist.
     *
     * @param player
     *            The player to add to the whitelist.
     */
    void add(final OfflinePlayer player);

    /**
     * Removes the given player from the whitelist.
     *
     * @param player
     *            The player to remove from the whitelist.
     * @return Whether the player was removed successfully of not (not, if the
     *         player was not found).
     */
    boolean remove(final OfflinePlayer player);

    /**
     * Checks whether the given player is whitelisted.
     *
     * @param player
     *            The player to check for.
     * @return Whethet the given player is whitelisted or not.
     */
    boolean isWhitelisted(final OfflinePlayer player);

    /**
     * Reloads the whitelist from disk.
     *
     */
    void reload();

    /**
     * Saves the whitelist to disk.
     *
     */
    void save();

    /**
     *
     * @return A set of all whitelisted players.
     */
    Set<OfflinePlayer> getWhitelistedPlayers();

    /**
     *
     * @return Whether the whitelist is enabled or not.
     */
    boolean isEnabled();

    /**
     * Enables/Disables the whitelist.
     *
     * @param enabled
     *            Whether to enable or disable the whitelist.
     */
    void setEnabled(final boolean enabled);
}
