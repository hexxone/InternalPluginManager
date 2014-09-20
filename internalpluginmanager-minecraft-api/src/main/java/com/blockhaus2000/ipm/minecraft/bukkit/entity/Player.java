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
package com.blockhaus2000.ipm.minecraft.bukkit.entity;

import java.util.UUID;

import com.blockhaus2000.ipm.minecraft.command.PlayerCommandSender;

/**
 * The player is a player that plays on the unerlying Minecraft server. Is
 * mostly used to teleport them, send messages, etc.
 *
 */
public interface Player extends PlayerCommandSender {
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
     * @return The display name of this player as shown in the chat.
     */
    String getDisplayName();

    /**
     *
     * @return The UUID for this player.
     */
    UUID getUniqueId();

    /**
     *
     * @return Whether this player is online or not.
     */
    boolean isOnline();
}
