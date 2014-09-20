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
import java.util.logging.Logger;

import com.blockhaus2000.ipm.minecraft.bukkit.entity.Player;
import com.blockhaus2000.ipm.minecraft.event.EventManager;
import com.blockhaus2000.ipm.technical.plugin.PluginManager;
import com.blockhaus2000.ipm.technical.plugin.command.PluginCommandManager;

/**
 * The server (interface) is the main access point to the underlying
 * implementation. It defines some technical methods.
 *
 */
public interface Server {
    // Technical actions.
    /**
     *
     * @return The command manager that manages the commands for this server.
     */
    PluginCommandManager getCommandManager();

    /**
     *
     * @return The event manager that manages the events for this sever.
     */
    EventManager getEventManager();

    /**
     *
     * @return The plugin manager that manages the plugins for this server.
     */
    PluginManager getPluginManager();

    /**
     *
     * @return The logger that should be used for logging for this server.
     */
    Logger getLogger();

    // Player actions.
    /**
     * Searchs for the player that currently has the given name.
     *
     * @deprecated Since Minecraft 1.8, it is possible to change names. So, use
     *             {@link Server#getPlayer(UUID)} instead if it is possible. Do
     *             only use this this if you only use the name temporarily
     *             (whilest a command execution, for example). Do NOT store
     *             player data associated with their names in a database.
     * @param name
     *            The player name to search for. The name si case-insensitive.
     * @return The player, if found. <code>null</code> otherwise.
     */
    @Deprecated
    Player getPlayer(final String name);

    /**
     * Search for the player with the given UUID.
     *
     * @param uuid
     *            The UUID to search for.
     * @return The player, if found. <code>null</code> otherwise.
     */
    Player getPlayer(final UUID uuid);

    /**
     *
     * @return All players that are online. If no player is online, an empty
     *         set.
     */
    Set<Player> getOnlinePlayers();
}
