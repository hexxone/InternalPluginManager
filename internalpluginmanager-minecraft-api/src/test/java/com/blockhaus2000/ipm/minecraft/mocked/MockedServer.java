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
package com.blockhaus2000.ipm.minecraft.mocked;

import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import com.blockhaus2000.ipm.minecraft.GameMode;
import com.blockhaus2000.ipm.minecraft.OfflinePlayer;
import com.blockhaus2000.ipm.minecraft.Server;
import com.blockhaus2000.ipm.minecraft.ServerIcon;
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
 * A mock implementation of {@link Server}.
 *
 */
public class MockedServer implements Server {
    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getCommandManager()
     */
    @Override
    public PluginCommandManager getCommandManager() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getEventManager()
     */
    @Override
    public EventManager getEventManager() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getPluginManager()
     */
    @Override
    public PluginManager getPluginManager() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getLogger()
     */
    @Override
    public Logger getLogger() {
        return Logger.getLogger(this.getClass().getName() + "_LOGGER");
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getPlayer(java.lang.String)
     */
    @Deprecated
    @Override
    public Player getPlayer(final String name) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getPlayer(java.util.UUID)
     */
    @Override
    public Player getPlayer(final UUID uuid) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getScoreboardManager()
     */
    @Override
    public ScoreboardManager getScoreboardManager() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getScheduler()
     */
    @Override
    public Scheduler getScheduler() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getConsoleCommandSender()
     */
    @Override
    public ConsoleCommandSender getConsoleCommandSender() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getName()
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getVersion()
     */
    @Override
    public String getVersion() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getDefaultGameMode()
     */
    @Override
    public GameMode getDefaultGameMode() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#setDefaultGameMode(com.blockhaus2000.ipm.minecraft.GameMode)
     */
    @Override
    public void setDefaultGameMode(final GameMode gameMode) {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getServerIcon()
     */
    @Override
    public ServerIcon getServerIcon() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#setServerIcon(com.blockhaus2000.ipm.minecraft.ServerIcon)
     */
    @Override
    public void setServerIcon(final ServerIcon serverIcon) {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#isServerOnline()
     */
    @Override
    public boolean isServerOnline() {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#isFlightAllowed()
     */
    @Override
    public boolean isFlightAllowed() {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#isHardcore()
     */
    @Override
    public boolean isHardcore() {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#reload()
     */
    @Override
    public void reload() {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#shutdown()
     */
    @Override
    public void shutdown() {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getPlayers()
     */
    @Override
    public Set<Player> getPlayers() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getOfflinePlayer(java.lang.String)
     */
    @Deprecated
    @Override
    public OfflinePlayer getOfflinePlayer(final String name) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getOfflinePlayer(java.util.UUID)
     */
    @Override
    public OfflinePlayer getOfflinePlayer(final UUID uuid) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#isWhitelistEnabled()
     */
    @Override
    public boolean isWhitelistEnabled() {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getWhitelistedPlayers()
     */
    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#reloadWhitelist()
     */
    @Override
    public void reloadWhitelist() {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getBannedPlayers()
     */
    @Override
    public Set<OfflinePlayer> getBannedPlayers() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getBannedIps()
     */
    @Override
    public Set<String> getBannedIps() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#isGeneratingStructures()
     */
    @Override
    public boolean isGeneratingStructures() {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#isEndEnabled()
     */
    @Override
    public boolean isEndEnabled() {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#isNetherEnabled()
     */
    @Override
    public boolean isNetherEnabled() {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getWorld(java.lang.String)
     */
    @Override
    public World getWorld(final String name) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getWorld(java.util.UUID)
     */
    @Override
    public World getWorld(final UUID uuid) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getWorlds()
     */
    @Override
    public Set<World> getWorlds() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#createWorld(com.blockhaus2000.ipm.minecraft.world.WorldCreator)
     */
    @Override
    public World createWorld(final WorldCreator creator) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#unloadWorld(com.blockhaus2000.ipm.minecraft.world.World,
     *      boolean)
     */
    @Override
    public boolean unloadWorld(final World world, final boolean save) {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#createMap(com.blockhaus2000.ipm.minecraft.world.World)
     */
    @Override
    public MapView createMap(final World world) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getRecipes(com.blockhaus2000.ipm.minecraft.inventory.ItemStack)
     */
    @Override
    public Set<Recipe> getRecipes(final ItemStack stack) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getRecipes()
     */
    @Override
    public Set<Recipe> getRecipes() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#addRecipe(com.blockhaus2000.ipm.minecraft.inventory.Recipe)
     */
    @Override
    public void addRecipe(final Recipe recipe) {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#clearRecipes()
     */
    @Override
    public void clearRecipes() {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#resetRecipes()
     */
    @Override
    public void resetRecipes() {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#broadcast(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public void broadcast(final String message, final String permission) {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#broadcast(java.lang.String)
     */
    @Override
    public void broadcast(final String message) {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getHostName()
     */
    @Override
    public String getHostName() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#executeCommand(com.blockhaus2000.ipm.minecraft.command.CommandSender,
     *      java.lang.String)
     */
    @Override
    public boolean executeCommand(final CommandSender sender, final String command) {
        // TODO Auto-generated method body.
        return false;
    }
}
