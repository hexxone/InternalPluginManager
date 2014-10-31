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

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import com.blockhaus2000.ipm.base.injection.InjectionManager;
import com.blockhaus2000.ipm.minecraft.Banlist;
import com.blockhaus2000.ipm.minecraft.IpBanlist;
import com.blockhaus2000.ipm.minecraft.MessageManager;
import com.blockhaus2000.ipm.minecraft.PlayerManager;
import com.blockhaus2000.ipm.minecraft.Server;
import com.blockhaus2000.ipm.minecraft.ServerProperties;
import com.blockhaus2000.ipm.minecraft.Whitelist;
import com.blockhaus2000.ipm.minecraft.bukkit.command.BukkitCommandManager;
import com.blockhaus2000.ipm.minecraft.bukkit.command.BukkitConsoleCommandSender;
import com.blockhaus2000.ipm.minecraft.bukkit.world.BukkitWorldManager;
import com.blockhaus2000.ipm.minecraft.command.ConsoleCommandSender;
import com.blockhaus2000.ipm.minecraft.entity.Player;
import com.blockhaus2000.ipm.minecraft.recipe.RecipeManager;
import com.blockhaus2000.ipm.minecraft.scoreboard.ScoreboardManager;
import com.blockhaus2000.ipm.minecraft.world.WorldManager;
import com.blockhaus2000.ipm.technical.plugin.PluginManager;
import com.blockhaus2000.ipm.technical.plugin.command.PluginCommandManager;
import com.blockhaus2000.ipm.technical.plugin.event.PluginEventManager;
import com.blockhaus2000.ipm.technical.plugin.event.SimplePluginEventManager;
import com.blockhaus2000.ipm.technical.plugin.scheduler.SimplePluginScheduler;
import com.blockhaus2000.ipm.technical.scheduler.Scheduler;

/**
 * The Bukkit implementation of {@link Server}.
 *
 */
public class BukkitServer implements Server {
    /**
     * The one and only instance of this server.
     *
     */
    private static final Server INSTANCE = new BukkitServer();

    /**
     * The main logger for this server.
     *
     */
    private final Logger logger = Logger.getLogger(this.getClass().getName() + ".LOGGER");

    /**
     * The command manager of this server.
     *
     */
    private final PluginCommandManager commandManager = new BukkitCommandManager();
    /**
     * The event manager of this server.
     *
     */
    private final PluginEventManager eventManager = new SimplePluginEventManager();
    /**
     * The scheduler of this server.
     *
     */
    private final Scheduler scheduler = new SimplePluginScheduler();
    /**
     * TODO: Implement.
     *
     */
    private final ScoreboardManager scoreboardManager = null;
    /**
     * The player manager of this server.
     *
     */
    private final PlayerManager playerManager = new BukkitPlayerManager();
    /**
     * The world manager of this server.
     *
     */
    private final WorldManager worldManager = new BukkitWorldManager();
    /**
     * The message manager of this server.
     *
     */
    private final MessageManager messageManager = new BukkitMessageManager();
    /**
     * TODO: Implement.
     *
     */
    private final RecipeManager recipeManager = null;
    /**
     * TODO: Implement.
     *
     */
    private final ServerProperties serverProperties = null;
    /**
     * TODO: Implement.
     *
     */
    private final Whitelist whitelist = null;
    /**
     * TODO: Implement.
     *
     */
    private final Banlist banlist = null;
    /**
     * TODO: Implement.
     *
     */
    private final IpBanlist ipBanlist = null;
    /**
     * The main {@link ConsoleCommandSender} of this server.
     *
     */
    private final ConsoleCommandSender consoleCommandSender = new BukkitConsoleCommandSender();

    /**
     * Constructor of BukkitServer.
     *
     */
    public BukkitServer() {
        InjectionManager.addResource(this, Server.class);
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
     * @see com.blockhaus2000.ipm.minecraft.Server#getServerVersion()
     */
    @Override
    public String getServerVersion() {
        // TODO Auto-generated method body.
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getPluginManager()
     */
    @Override
    public PluginManager getPluginManager() {
        return PluginManager.getInstance();
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
    public PluginEventManager getEventManager() {
        return this.eventManager;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getScheduler()
     */
    @Override
    public Scheduler getScheduler() {
        return this.scheduler;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getScoreboardManager()
     */
    @Override
    public ScoreboardManager getScoreboardManager() {
        return this.scoreboardManager;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getPlayerManager()
     */
    @Override
    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getWorldManager()
     */
    @Override
    public WorldManager getWorldManager() {
        return this.worldManager;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getMessageManager()
     */
    @Override
    public MessageManager getMessageManager() {
        return this.messageManager;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getRecipeManager()
     */
    @Override
    public RecipeManager getRecipeManager() {
        return this.recipeManager;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getServerProperties()
     */
    @Override
    public ServerProperties getServerProperties() {
        return this.serverProperties;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getWhitelist()
     */
    @Override
    public Whitelist getWhitelist() {
        return this.whitelist;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getBanlist()
     */
    @Override
    public Banlist getBanlist() {
        return this.banlist;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getIpBanlist()
     */
    @Override
    public IpBanlist getIpBanlist() {
        return this.ipBanlist;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#getConsoleCommandSender()
     */
    @Override
    public ConsoleCommandSender getConsoleCommandSender() {
        return this.consoleCommandSender;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#dispatchCommand(java.lang.String)
     */
    @Override
    public void dispatchCommand(final String command) {
        this.dispatchCommand(command, Bukkit.getServer().getConsoleSender());
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#dispatchCommand(java.lang.String,
     *      com.blockhaus2000.ipm.minecraft.entity.Player)
     */
    @Override
    public void dispatchCommand(final String command, final Player player) {
        this.dispatchCommand(command, Bukkit.getServer().getPlayer(player.getUUID()));
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
     * @see com.blockhaus2000.ipm.minecraft.Server#reload()
     */
    @Override
    public void reload() {
        Bukkit.getServer().reload();
    }

    /**
     * Dispatches the give command as the given sender.
     *
     * @param command
     *            The command to execute.
     * @param sender
     *            The {@link CommandSender} to execute the command as.
     */
    private void dispatchCommand(final String command, final CommandSender sender) {
        Bukkit.getServer().dispatchCommand(sender, command);
    }

    /**
     *
     * @return {@link BukkitServer#INSTANCE}
     */
    public static Server getInstance() {
        return BukkitServer.INSTANCE;
    }
}
