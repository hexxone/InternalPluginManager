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

import java.util.UUID;

import javax.xml.stream.Location;

import com.blockhaus2000.ipm.minecraft.bukkit.entity.Player;

public interface OfflinePlayer {
    /**
     *
     * @return The UUID of this player.
     */
    UUID getUniqueId();

    /**
     *
     * @deprecated Since Minecraft 1.8, name changes are possible. Because of
     *             that, UUIDs where added. So use {@link Player#getUniqueId()}
     *             instead of using the name to associated data with a player
     *             (in a database, for example).
     * @return The name of this player that has been lower-cased and trimmed.
     */
    @Deprecated
    String getName();

    /**
     *
     * @return The player object of this offline player object, if found.
     *         <code>null</code> otherwise.
     */
    Player getPlayer();

    /**
     *
     * @return The unix time stamp of the time this player has first played on
     *         this server. If the player has not played on this server until
     *         now, returning <code>0</code>.
     */
    long getFirstPlayed();

    /**
     *
     * @return The unix time stamp of the time this player has last played on
     *         this server. If the player has not played on this server until
     *         now, returning <code>0</code>.
     */
    long getLastplayed();

    /**
     *
     * @return Whether the player plays the first time on this server.
     */
    boolean isNew();

    /**
     *
     * @return The location of the bed where the player spawns on death. If the
     *         player "has no bed", returning <code>null</code>.
     */
    Location getBedSpawnLoaction();

    /**
     *
     * @return Whether this player is online.
     */
    boolean isOnline();

    /**
     *
     * @return Whether this player is banned.
     */
    boolean isBanned();

    /**
     *
     * @return Whether this player is whitelisted.
     */
    boolean isWhitelisted();

    /**
     * Set whether this player is banned.
     *
     * @param banned
     *            The new banned state.
     */
    void setBanned(final boolean banned);

    /**
     * Set whether this player is whitelisted.
     *
     * @param whitelisted
     *            The new whitelisted state.
     */
    void setWhitelisted(final boolean whitelisted);
}
