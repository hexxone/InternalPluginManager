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

import java.util.logging.Logger;

import com.blockhaus2000.ipm.minecraft.command.CommandSender;
import com.blockhaus2000.ipm.minecraft.command.ConsoleCommandSender;
import com.blockhaus2000.ipm.minecraft.recipe.RecipeManager;
import com.blockhaus2000.ipm.minecraft.scoreboard.ScoreboardManager;
import com.blockhaus2000.ipm.minecraft.world.WorldManager;
import com.blockhaus2000.ipm.technical.plugin.PluginManager;
import com.blockhaus2000.ipm.technical.plugin.command.PluginCommandManager;
import com.blockhaus2000.ipm.technical.plugin.event.PluginEventManager;
import com.blockhaus2000.ipm.technical.scheduler.Scheduler;

/**
 * The main access point to the underlying implementation. This is THE server.
 *
 */
public interface Server {
    /**
     *
     * @return The {@link Logger} for this server.
     */
    Logger getLogger();

    /**
     *
     * @return The version of this server implementation.
     */
    String getServerVersion();

    /**
     *
     * @return The {@link PluginManager} for this server.
     */
    PluginManager getPluginManager();

    /**
     *
     * @return The {@link PluginCommandManager} for this server.
     */
    PluginCommandManager getCommandManager();

    /**
     *
     * @return The {@link PluginEventManager} for this server.
     */
    PluginEventManager getEventManager();

    /**
     *
     * @return The {@link Scheduler} for this server.
     */
    Scheduler getScheduler();

    /**
     *
     * @return The {@link ScoreboardManager} for this server.
     */
    ScoreboardManager getScoreboardManager();

    /**
     *
     * @return The {@link PlayerManager} for this server.
     */
    PlayerManager getPlayerManager();

    /**
     *
     * @return The {@link WorldManager} for this server.
     */
    WorldManager getWorldManager();

    /**
     *
     * @return The {@link MessageManager} for this server.
     */
    MessageManager getMessageManager();

    /**
     *
     * @return The {@link RecipeManager} for this server.
     */
    RecipeManager getRecipeManager();

    /**
     *
     * @return The {@link ServerProperties} from this server.
     */
    ServerProperties getServerProperties();

    /**
     *
     * @return The {@link Whitelist} from this server.
     */
    Whitelist getWhitelist();

    /**
     *
     * @return The {@link Banlist} from this server.
     */
    Banlist getBanlist();

    /**
     *
     * @return The {@link IpBanlist} from this server.
     */
    IpBanlist getIpBanlist();

    /**
     *
     * @return The {@link ConsoleCommandSender} from this server.
     */
    ConsoleCommandSender getConsoleCommandSender();

    /**
     * Executes/dispatchs the given command string (containing the command and
     * the arguments) as the given {@link CommandSender}.
     *
     * @param command
     *            The command string to execute (containing command and
     *            arguments).
     * @param sender
     *            The {@link CommandSender} to execute the command as.
     */
    void dispatchCommand(final String command, final CommandSender sender);

    /**
     * Stops this server.
     *
     */
    void shutdown();

    /**
     * Reloads the server.
     *
     */
    void reload();
}
