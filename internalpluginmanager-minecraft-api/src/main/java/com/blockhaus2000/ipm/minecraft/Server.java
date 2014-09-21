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
import com.blockhaus2000.ipm.minecraft.command.CommandSender;
import com.blockhaus2000.ipm.minecraft.command.ConsoleCommandSender;
import com.blockhaus2000.ipm.minecraft.event.EventManager;
import com.blockhaus2000.ipm.minecraft.inventory.ItemStack;
import com.blockhaus2000.ipm.minecraft.inventory.Recipe;
import com.blockhaus2000.ipm.minecraft.map.MapView;
import com.blockhaus2000.ipm.minecraft.scheduler.Scheduler;
import com.blockhaus2000.ipm.minecraft.scoreboard.ScoreboardManager;
import com.blockhaus2000.ipm.minecraft.world.World;
import com.blockhaus2000.ipm.minecraft.world.WorldCreator;
import com.blockhaus2000.ipm.technical.plugin.PluginManager;
import com.blockhaus2000.ipm.technical.plugin.command.PluginCommandManager;

/**
 * The server (interface) is the main access point to the underlying
 * implementation. It defines some technical methods.
 *
 */
public interface Server {
    // Technical getters.
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
     * @return The scoreboard manager for this server.
     */
    ScoreboardManager getScoreboardManager();

    /**
     *
     * @return The scheduler that manages tasks.
     */
    Scheduler getScheduler();

    /**
     *
     * @return The logger that should be used for logging for this server.
     */
    Logger getLogger();

    /**
     *
     * @return THE console command sender.
     */
    ConsoleCommandSender getConsoleCommandSender();

    /**
     *
     * @return The name of this server implementation.
     */
    String getName();

    /**
     *
     * @return The version of this server implementation
     */
    String getVersion();

    /**
     *
     * @return The host name of this server.
     */
    String getHostName();

    /**
     *
     * @return The default game mode.
     */
    GameMode getDefaultGameMode();

    /**
     * Sets the default game mode.
     *
     * @param gameMode
     *            The new default game mode.
     */
    void setDefaultGameMode(final GameMode gameMode);

    /**
     *
     * @return The icon of this server.
     */
    ServerIcon getServerIcon();

    /**
     * Sets the server icon.
     *
     * @param serverIcon
     *            The new server icon.
     */
    void setServerIcon(final ServerIcon serverIcon);

    /**
     *
     * @return Whether the online mode is enabled.
     */
    boolean isServerOnline();

    /**
     *
     * @return Whether flying is allowed.
     */
    boolean isFlightAllowed();

    /**
     *
     * @return Whether this server is running in hardcore mode.
     */
    boolean isHardcore();

    // Technical actions.
    /**
     * Reloads this server.
     *
     */
    void reload();

    /**
     * Shurdowns this server.
     *
     */
    void shutdown();

    /**
     * Executes the given command (the command string contains both command and
     * arguments) as the given command sender.
     *
     * @param sender
     *            The command sender that has to execute the given command
     *            string.
     * @param command
     *            The command string to execute. Contains both command and
     *            arguments.
     * @return <code>true</code> if the command was executed, <code>false</code>
     *         otherwise.
     */
    boolean executeCommand(final CommandSender sender, final String command);

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
    Set<Player> getPlayers();

    // Offline player actions.
    /**
     * Searchs for the offline player with the given name.
     *
     * <p>
     * <b> NOTE: This may fetches the player name from the web to get the player
     * that currently has the given name. </b>
     * </p>
     *
     * @deprecated Player names are no longer unique since Minecraft 1.8 where
     *             name changes where introduced. Use
     *             {@link Server#getOfflinePlayer(UUID)} instead.
     * @param name
     *            The player name to search for.
     * @return The found offline player, if found. Otherwise <code>null</code>.
     */
    @Deprecated
    OfflinePlayer getOfflinePlayer(final String name);

    /**
     * The offline player that has the given UUID.
     *
     * <p>
     * <b> NOTE: For this method, every player exist. But it is may possible
     * that the returned player is not a real player. </b>
     * </p>
     *
     * @param uuid
     *            The UUID to search for.
     * @return The found offline player.
     */
    OfflinePlayer getOfflinePlayer(final UUID uuid);

    // Whitelist actions.
    /**
     *
     * @return Whether the whitelist is enabled.
     */
    boolean isWhitelistEnabled();

    /**
     *
     * @return The whitelisted players.
     */
    Set<OfflinePlayer> getWhitelistedPlayers();

    /**
     * Reloads the whitelist from disk.
     *
     */
    void reloadWhitelist();

    // Banlist actions.
    /**
     *
     * @return The banned players.
     */
    Set<OfflinePlayer> getBannedPlayers();

    // IP-Banlist actions.
    /**
     *
     * @return The banned IPs.
     */
    Set<String> getBannedIps();

    // World actions.
    /**
     *
     * @return Whether this server generates structures or not.
     */
    boolean isGeneratingStructures();

    /**
     *
     * @return Whether "The End" is enabled.
     */
    boolean isEndEnabled();

    /**
     *
     * @return Whether "The Nether" is enabled.
     */
    boolean isNetherEnabled();

    /**
     *
     * @param name
     *            The world name to search for.
     * @return The world with the given name. <code>null</code> if no world with
     *         the given name exist.
     */
    World getWorld(final String name);

    /**
     *
     * @param uuid
     *            The world UUID to search for.
     * @return The world with the given UUID. <code>null</code> if no world with
     *         the given UUID exist.
     */
    World getWorld(final UUID uuid);

    /**
     *
     * @return All worlds on this server.
     */
    Set<World> getWorlds();

    /**
     * If the world with the name specified in the given world creator is not
     * present, creates a new world. Otherwise, returns already generated world.
     *
     * @param creator
     *            The world creator to use for creation.
     * @return The newly created world or, if the world already exist, the
     *         already generated world.
     */
    World createWorld(final WorldCreator creator);

    /**
     * Saves the given world. If <code>save == true</code>, saves the chunks
     * before unloading.
     *
     * @param world
     *            The world to unload.
     * @param save
     *            hether to save the chucks before unloading.
     * @return <code>true</code> if removing was successful, <code>false</code>
     *         otherwise.
     */
    boolean unloadWorld(final World world, final boolean save);

    /**
     *
     * @param world
     *            The world to create a map view from.
     * @return The newly created map view.
     */
    MapView createMap(final World world);

    // Recipe actions.
    /**
     * Gets all recipes for the given item stack. The stack size is ignored.
     *
     * @param stack
     *            The item stack to search for recipe.
     * @return A set of recipes that can be used to craft the given item stack.
     *         If no recipe was found, returning <code>null</code>.
     */
    Set<Recipe> getRecipes(final ItemStack stack);

    /**
     *
     * @return All registered recipes.
     */
    Set<Recipe> getRecipes();

    /**
     * Adds/Registers the given recipe.
     *
     * @param recipe
     *            The recipe to add/register.
     */
    void addRecipe(final Recipe recipe);

    /**
     * Clears all recipes.
     *
     */
    void clearRecipes();

    /**
     * Resets all recipes to the defaults.
     *
     */
    void resetRecipes();

    // Broadcasting
    /**
     * Broadcasts the given message to every player on this server that has the
     * given permission.
     *
     * @param message
     *            The message to broadcast.
     * @param permission
     *            The permission that every player has to have to receive the
     *            broadcast message. If <code>permission == null</code>, every
     *            player receives the message.
     */
    void broadcast(final String message, final String permission);

    /**
     * Broadcasts the given message to all players on this sever.
     *
     * @param message
     *            The message to broadcast.
     */
    void broadcast(final String message);
}
