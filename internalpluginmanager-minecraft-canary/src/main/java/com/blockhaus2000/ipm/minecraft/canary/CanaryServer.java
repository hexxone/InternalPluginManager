/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014-2015 Fabian Damken
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
package com.blockhaus2000.ipm.minecraft.canary;

import java.util.logging.Logger;

import com.blockhaus2000.ipm.base.injection.InjectionManager;
import com.blockhaus2000.ipm.minecraft.Banlist;
import com.blockhaus2000.ipm.minecraft.IpBanlist;
import com.blockhaus2000.ipm.minecraft.MessageManager;
import com.blockhaus2000.ipm.minecraft.PlayerManager;
import com.blockhaus2000.ipm.minecraft.Server;
import com.blockhaus2000.ipm.minecraft.ServerProperties;
import com.blockhaus2000.ipm.minecraft.Whitelist;
import com.blockhaus2000.ipm.minecraft.canary.command.CanaryConsoleCommandSender;
import com.blockhaus2000.ipm.minecraft.command.ConsoleCommandSender;
import com.blockhaus2000.ipm.minecraft.entity.Player;
import com.blockhaus2000.ipm.minecraft.recipe.RecipeManager;
import com.blockhaus2000.ipm.minecraft.scoreboard.ScoreboardManager;
import com.blockhaus2000.ipm.minecraft.world.WorldManager;
import com.blockhaus2000.ipm.technical.plugin.PluginManager;
import com.blockhaus2000.ipm.technical.plugin.command.PluginCommandManager;
import com.blockhaus2000.ipm.technical.plugin.event.PluginEventManager;
import com.blockhaus2000.ipm.technical.scheduler.Scheduler;

/**
 * The Canary implementation of {@link Server}.
 *
 */
public class CanaryServer implements Server {
    /**
     * The one and only instance of this class.
     *
     */
    private static final Server INSTANCE = new CanaryServer();

    /**
     * The man logger for this server.
     *
     */
    private final Logger logger = Logger.getLogger(this.getClass().getName() + ".LOGGER");

    /**
     * The {@link PluginManager} of this server.
     *
     */
    private final PluginManager pluginManager = null; // TODO
    /**
     * The {@link PluginCommandManager} of this server.
     *
     */
    private final PluginCommandManager commandManager = null; // TODO
    /**
     * The {@link PluginEventManager} of this server.
     *
     */
    private final PluginEventManager eventManager = null; // TODO
    /**
     * The {@link Scheduler} of this server.
     *
     */
    private final Scheduler scheduler = null; // TODO
    /**
     * The {@link ScoreboardManager} of this server.
     *
     */
    private final ScoreboardManager scoreboardManager = null; // TODO
    /**
     * The {@link PlayerManager} of this server.
     *
     */
    private final PlayerManager playerManager = new CanaryPlayerManager();
    /**
     * The {@link WorldManager} of this server.
     *
     */
    private final WorldManager worldManager = null; // TODO
    /**
     * The {@link MessageManager} of this server.
     *
     */
    private final MessageManager messageManager = null; // TODO
    /**
     * The {@link RecipeManager} of this server.
     *
     */
    private final RecipeManager recipeManager = null; // TODO
    /**
     * The {@link ServerProperties} of this server.
     *
     */
    private final ServerProperties serverProperties = null; // TODO
    /**
     * The {@link Whitelist} of this server.
     *
     */
    private final Whitelist whitelist = new CanaryWhitelist();
    /**
     * The {@link Banlist} of this server.
     *
     */
    private final Banlist banlist = new CanaryBanlist();
    /**
     * The {@link IpBanlist} of this server.
     *
     */
    private final IpBanlist ipBanlist = new CanaryIpBanlist();
    /**
     * The {@link ConsoleCommandSender} of this server.
     *
     */
    private final ConsoleCommandSender consoleCommandSender = new CanaryConsoleCommandSender();

    /**
     * Constructor of CanaryServer.
     *
     */
    private CanaryServer() {
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
        // FIXME: Add server version. Has to be done before EVERY release.
        return null;
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
        // TODO Auto-generated method body.

    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#dispatchCommand(java.lang.String,
     *      com.blockhaus2000.ipm.minecraft.entity.Player)
     */
    @Override
    public void dispatchCommand(final String command, final Player player) {
        // TODO Auto-generated method body.

    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#shutdown()
     */
    @Override
    public void shutdown() {
        // TODO Auto-generated method body.

    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.Server#reload()
     */
    @Override
    public void reload() {
        // TODO Auto-generated method body.

    }

    /**
     *
     * @return An instance of this class.
     */
    public static Server getInstance() {
        return CanaryServer.INSTANCE;
    }
}
