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
package com.blockhaus2000.ipm.minecraft;

import java.util.Set;
import java.util.UUID;

import com.blockhaus2000.ipm.minecraft.entity.Player;

/**
 * The {@link PlayerManager} for this server. It is used to get players by
 * {@link UUID}s, etc.
 *
 */
public interface PlayerManager {
    /**
     * Searchs for the given player with the given {@link UUID}.
     *
     * <p>
     * <b> NOTE: The player has to be online. If the player is offline, this
     * returns <code>null</code>. </b>
     * </p>
     *
     * @param uuid
     *            The {@link UUID} to search for.
     * @return The {@link Player}, if found. Otherwise <code>null</code>.
     */
    Player getPlayer(final UUID uuid);

    /**
     * Searchs for the player that currently has the given name.
     *
     * <p>
     * <b> NOTE: The player has to be online. If the player is offline, this
     * returns <code>null</code>. </b>
     * </p>
     *
     * @deprecated Please use {@link PlayerManager#getPlayer(UUID)} instead if
     *             you have a {@link UUID}. Since minecraft <code>1.8</code>,
     *             name changes are possible, so names are not longer unique.
     *             <p>
     *             If you do not have a {@link UUID}, you can use this method.
     *             </p>
     * @param name
     *            The player name to search for.
     * @return The player that currently has the given name. If not found, this
     *         returns <code>null</code>.
     */
    @Deprecated
    Player getPlayer(final String name);

    /**
     *
     * @return A set of all players currently online.
     */
    Set<Player> getPlayers();

    /**
     * Searchs for the given {@link UUID} in the offline player cache. If the
     * player is not found there, this requests the Mojang servers for a player
     * with the given {@link UUID}.
     *
     * @param uuid
     *            The {@link UUID} to search for.
     * @return The {@link OfflinePlayer} that is associated with the given
     *         {@link UUID}. If no player was found, this returns
     *         <code>null</code>.
     */
    OfflinePlayer getOfflinePlayer(final UUID uuid);

    /**
     * Searchs for the given name in the offline player cache. If the player is
     * not found there, this requests the Mojang servers for a player with the
     * given name.
     *
     * @deprecated Please use {@link PlayerManager#getOfflinePlayer(UUID)}
     *             instead if you have a {@link UUID}. Since minecraft
     *             <code>1.8</code>, name changes are possible, so names are not
     *             longer unique.
     *             <p>
     *             If you do not have a {@link UUID}, you can use this method.
     *             </p>
     * @param name
     *            The name to search for.
     * @return The {@link OfflinePlayer} that is associated with the given name.
     *         If no player was found, this returns <code>null</code>.
     */
    @Deprecated
    OfflinePlayer getOfflinePlayer(final String name);

    /**
     *
     * @return A set of all offline players that are currently in the cache.
     */
    Set<OfflinePlayer> getOfflinePlayers();
}
