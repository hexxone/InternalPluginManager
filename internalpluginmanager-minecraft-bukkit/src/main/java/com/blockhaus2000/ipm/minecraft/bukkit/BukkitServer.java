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
package com.blockhaus2000.ipm.minecraft.bukkit;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

import com.blockhaus2000.ipm.base.injection.InjectionManager;
import com.blockhaus2000.ipm.minecraft.GameMode;
import com.blockhaus2000.ipm.minecraft.OfflinePlayer;
import com.blockhaus2000.ipm.minecraft.Server;
import com.blockhaus2000.ipm.minecraft.ServerIcon;
import com.blockhaus2000.ipm.minecraft.bukkit.BukkitOfflinePlayer.BukkitOfflinePlayerFactory;
import com.blockhaus2000.ipm.minecraft.bukkit.command.BukkitCommandManager;
import com.blockhaus2000.ipm.minecraft.bukkit.command.BukkitConsoleCommandSender;
import com.blockhaus2000.ipm.minecraft.bukkit.entity.BukkitPlayer.BukkitPlayerFactory;
import com.blockhaus2000.ipm.minecraft.bukkit.entity.Player;
import com.blockhaus2000.ipm.minecraft.command.CommandSender;
import com.blockhaus2000.ipm.minecraft.command.ConsoleCommandSender;
import com.blockhaus2000.ipm.minecraft.event.EventManager;
import com.blockhaus2000.ipm.minecraft.event.SimpleEventManager;
import com.blockhaus2000.ipm.minecraft.inventory.ItemStack;
import com.blockhaus2000.ipm.minecraft.inventory.Recipe;
import com.blockhaus2000.ipm.minecraft.map.MapView;
import com.blockhaus2000.ipm.minecraft.scheduler.Scheduler;
import com.blockhaus2000.ipm.minecraft.scoreboard.ScoreboardManager;
import com.blockhaus2000.ipm.minecraft.world.World;
import com.blockhaus2000.ipm.minecraft.world.WorldCreator;
import com.blockhaus2000.ipm.technical.plugin.Plugin;
import com.blockhaus2000.ipm.technical.plugin.PluginManager;
import com.blockhaus2000.ipm.technical.plugin.command.PluginCommandManager;

/**
 * The implementation of {@link Server} for Bukkit.
 *
 */
public class BukkitServer implements Server {
    /**
     * THE instance of the {@link BukkitServer}.
     *
     */
    private static final Server INSTANCE = new BukkitServer();

    /**
     * The command manager to use for this server.
     *
     */
    private final PluginCommandManager commandManager;
    /**
     * The evtn manager to use for this server.
     *
     */
    private final EventManager eventManager;
    /**
     * The plugin manager to use for this server.
     *
     */
    private final PluginManager pluginManager;
    /**
     * The logger to use for this server.
     *
     * <p>
     * <b> NOTE: If you are within a plugin, use the plugin logger instead (
     * {@link Plugin#getLogger()}). </b>
     * </p>
     *
     */
    private final Logger logger;

    /**
     * Constructor of BukkitServer.
     *
     * <p>
     * Initilizes all fields and registers itself to the injection manager.
     * </p>
     *
     */
    private BukkitServer() {
        InjectionManager.addResource(this, Server.class);

        this.commandManager = new BukkitCommandManager();
        this.eventManager = new SimpleEventManager();
        this.pluginManager = PluginManager.getInstance();
        this.logger = Logger.getLogger(this.getClass().getPackage().getName() + ".LOGGER@" + System.currentTimeMillis());
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getCommandManager()
     */
    @Override
    public PluginCommandManager getCommandManager() {
        return this.commandManager;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getEventManager()
     */
    @Override
    public EventManager getEventManager() {
        return this.eventManager;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getPluginManager()
     */
    @Override
    public PluginManager getPluginManager() {
        return this.pluginManager;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getScoreboardManager()
     */
    @Override
    public ScoreboardManager getScoreboardManager() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getScheduler()
     */
    @Override
    public Scheduler getScheduler() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getLogger()
     */
    @Override
    public Logger getLogger() {
        return this.logger;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getConsoleCommandSender()
     */
    @Override
    public ConsoleCommandSender getConsoleCommandSender() {
        return BukkitConsoleCommandSender.getInstance();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getName()
     */
    @Override
    public String getName() {
        return Bukkit.getServer().getName();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getVersion()
     */
    @Override
    public String getVersion() {
        return Main.getPlugin().getDescription().getVersion();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getHostName()
     */
    @Override
    public String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (final UnknownHostException dummy) {
            return "local.host";
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getDefaultGameMode()
     */
    @Override
    public GameMode getDefaultGameMode() {
        final GameMode defaultGameMode;
        switch (Bukkit.getServer().getDefaultGameMode()) {
            case SURVIVAL:
                defaultGameMode = GameMode.SURVIVAL;
                break;
            case CREATIVE:
                defaultGameMode = GameMode.CREATIVE;
                break;
            case ADVENTURE:
                defaultGameMode = GameMode.ADVENTURE;
                break;
            default:
                defaultGameMode = null;
                break;
        }
        return defaultGameMode;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#setDefaultGameMode(com.blockhaus2000.ipm.minecraft.GameMode)
     */
    @Override
    public void setDefaultGameMode(final GameMode gameMode) {
        final org.bukkit.GameMode defaultGameMode;
        switch (gameMode) {
            case SURVIVAL:
                defaultGameMode = org.bukkit.GameMode.SURVIVAL;
                break;
            case CREATIVE:
                defaultGameMode = org.bukkit.GameMode.CREATIVE;
                break;
            case ADVENTURE:
                defaultGameMode = org.bukkit.GameMode.ADVENTURE;
                break;
            default:
                defaultGameMode = null;
                break;
        }
        Bukkit.getServer().setDefaultGameMode(defaultGameMode);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getServerIcon()
     */
    @Override
    public ServerIcon getServerIcon() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#setServerIcon(com.blockhaus2000.ipm.minecraft.ServerIcon)
     */
    @Override
    public void setServerIcon(final ServerIcon serverIcon) {
        // TODO Auto-generated method body.

    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#isServerOnline()
     */
    @Override
    public boolean isServerOnline() {
        return Bukkit.getServer().getOnlineMode();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#isFlightAllowed()
     */
    @Override
    public boolean isFlightAllowed() {
        return Bukkit.getServer().getAllowFlight();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#isHardcore()
     */
    @Override
    public boolean isHardcore() {
        return Bukkit.getServer().isHardcore();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#reload()
     */
    @Override
    public void reload() {
        Bukkit.getServer().reload();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#shutdown()
     */
    @Override
    public void shutdown() {
        Bukkit.getServer().shutdown();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#executeCommand(com.blockhaus2000.ipm.minecraft.command.CommandSender,
     *      java.lang.String)
     */
    @Override
    public boolean executeCommand(final CommandSender sender, final String command) {
        final org.bukkit.command.CommandSender commandSender;
        switch (sender.getType()) {
            case PLAYER:
                commandSender = Bukkit.getServer().getPlayer(((Player) sender).getUniqueId());
                break;
            case CONSOLE:
                // FALL-THROUGH
            case BLOCK:
                // FALL-THROUGH
            default:
                // Use the console sender every time no player has "executed"
                // the command.
                commandSender = Bukkit.getServer().getConsoleSender();
                break;
        }
        return Bukkit.getServer().dispatchCommand(commandSender, command);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getPlayer(java.lang.String)
     */
    @Deprecated
    @Override
    public Player getPlayer(final String name) {
        for (final Player player : this.getPlayers()) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getPlayer(java.util.UUID)
     */
    @Override
    public Player getPlayer(final UUID uuid) {
        for (final Player player : this.getPlayers()) {
            if (player.getUniqueId().equals(uuid)) {
                return player;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getPlayers()
     */
    @Override
    public Set<Player> getPlayers() {
        final Set<Player> players = new HashSet<Player>();
        for (final org.bukkit.entity.Player player : Bukkit.getServer().getOnlinePlayers()) {
            players.add(BukkitPlayerFactory.getBukkitPlayer(player));
        }
        return players;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getOfflinePlayer(java.lang.String)
     */
    @Deprecated
    @Override
    public OfflinePlayer getOfflinePlayer(final String name) {
        return BukkitOfflinePlayerFactory.getBukkitOfflinePlayer(Bukkit.getServer().getOfflinePlayer(name));
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getOfflinePlayer(java.util.UUID)
     */
    @Override
    public OfflinePlayer getOfflinePlayer(final UUID uuid) {
        return BukkitOfflinePlayerFactory.getBukkitOfflinePlayer(Bukkit.getServer().getOfflinePlayer(uuid));
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#isWhitelistEnabled()
     */
    @Override
    public boolean isWhitelistEnabled() {
        return Bukkit.getServer().hasWhitelist();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getWhitelistedPlayers()
     */
    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers() {
        final Set<OfflinePlayer> players = new HashSet<OfflinePlayer>();
        for (final org.bukkit.OfflinePlayer player : Bukkit.getServer().getWhitelistedPlayers()) {
            players.add(BukkitOfflinePlayerFactory.getBukkitOfflinePlayer(player));
        }
        return players;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#reloadWhitelist()
     */
    @Override
    public void reloadWhitelist() {
        Bukkit.getServer().reloadWhitelist();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getBannedPlayers()
     */
    @Override
    public Set<OfflinePlayer> getBannedPlayers() {
        final Set<OfflinePlayer> players = new HashSet<OfflinePlayer>();
        for (final org.bukkit.OfflinePlayer player : Bukkit.getServer().getBannedPlayers()) {
            players.add(BukkitOfflinePlayerFactory.getBukkitOfflinePlayer(player));
        }
        return players;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getBannedIps()
     */
    @Override
    public Set<String> getBannedIps() {
        return Bukkit.getServer().getIPBans();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#isGeneratingStructures()
     */
    @Override
    public boolean isGeneratingStructures() {
        return Bukkit.getServer().getGenerateStructures();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#isEndEnabled()
     */
    @Override
    public boolean isEndEnabled() {
        return Bukkit.getServer().getAllowEnd();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#isNetherEnabled()
     */
    @Override
    public boolean isNetherEnabled() {
        return Bukkit.getServer().getAllowNether();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getWorld(java.lang.String)
     */
    @Override
    public World getWorld(final String name) {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getWorld(java.util.UUID)
     */
    @Override
    public World getWorld(final UUID uuid) {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getWorlds()
     */
    @Override
    public Set<World> getWorlds() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#createWorld(com.blockhaus2000.ipm.minecraft.world.WorldCreator)
     */
    @Override
    public World createWorld(final WorldCreator creator) {
        // TODO Auto-generated method body.
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
        // TODO Auto-generated method body.
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#createMap(com.blockhaus2000.ipm.minecraft.world.World)
     */
    @Override
    public MapView createMap(final World world) {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getRecipes(com.blockhaus2000.ipm.minecraft.inventory.ItemStack)
     */
    @Override
    public Set<Recipe> getRecipes(final ItemStack stack) {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getRecipes()
     */
    @Override
    public Set<Recipe> getRecipes() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#addRecipe(com.blockhaus2000.ipm.minecraft.inventory.Recipe)
     */
    @Override
    public void addRecipe(final Recipe recipe) {
        // TODO Auto-generated method body.

    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#clearRecipes()
     */
    @Override
    public void clearRecipes() {
        Bukkit.getServer().clearRecipes();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#resetRecipes()
     */
    @Override
    public void resetRecipes() {
        Bukkit.getServer().resetRecipes();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#broadcast(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public void broadcast(final String message, final String permission) {
        Bukkit.getServer().broadcast(message, permission);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#broadcast(java.lang.String)
     */
    @Override
    public void broadcast(final String message) {
        this.broadcast(message, null);
    }

    /**
     *
     * @return {@link BukkitServer#INSTANCE}
     */
    public static Server getInstance() {
        return BukkitServer.INSTANCE;
    }
}
