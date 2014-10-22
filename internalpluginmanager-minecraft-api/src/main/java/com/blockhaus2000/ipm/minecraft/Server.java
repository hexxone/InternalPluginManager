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
import com.blockhaus2000.technical.scheduler.Scheduler;

public interface Server {
    Logger getLogger();

    String getServerVersion();

    PluginManager getPluginManager();

    PluginCommandManager getCommandManager();

    PluginEventManager getEventManager();

    Scheduler getScheduler();

    ScoreboardManager getScoreboardManager();

    PlayerManager getPlayerManager();

    WorldManager getWorldManager();

    MessageManager getMessageManager();

    RecipeManager getRecipeManager();

    ServerProperties getServerProperties();

    Whitelist getWhitelist();

    Banlist getBanlist();

    IpBanlist getIpBanlist();

    ConsoleCommandSender getConsoleCommandSender();

    void dispatchCommand(final String command, final CommandSender sender);

    void shutdown();

    void reload();
}
