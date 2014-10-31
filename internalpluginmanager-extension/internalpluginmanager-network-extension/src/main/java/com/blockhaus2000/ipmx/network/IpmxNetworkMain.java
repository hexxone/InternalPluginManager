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
package com.blockhaus2000.ipmx.network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import com.blockhaus2000.ipm.technical.plugin.SimplePlugin;

/**
 * The main class of the InternalPluginManager Network Extension.
 *
 */
public class IpmxNetworkMain extends SimplePlugin {
    /**
     * The whitelist that is used by default, if no whitelist is available and
     * as the default for config creation.
     *
     */
    private static final List<String> DEFAULT_WHITELIST = new ArrayList<String>(Arrays.asList("127.0.0.1"));
    /**
     * The default port for the IPMNE server.
     *
     */
    private static final int DEFAULT_PORT = 50023;

    /**
     * The instance of this class.
     *
     */
    private static IpmxNetworkMain instance;

    /**
     * The server thread.
     *
     */
    private ServerThread server;

    /**
     * Constructor of IpmxNetworkMain.
     *
     */
    public IpmxNetworkMain() {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.SimplePlugin#onDisable()
     */
    @Override
    public void onDisable() {
        // Stop the server.
        this.server.interrupt();
        try {
            this.server.join();
        } catch (final InterruptedException ex) {
            ex.printStackTrace();
        }

        // Save configuration to disk.
        this.getConfig().save();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.SimplePlugin#onEnable()
     */
    @Override
    public void onEnable() {
        // Set instance to this.
        IpmxNetworkMain.instance = this;

        // Load configuration from disk.
        this.getConfig().load();

        // Add default configuration values.
        this.getConfig().add(ConfigPath.CONNECTION_PORT.getPath(), IpmxNetworkMain.DEFAULT_PORT);
        this.getConfig().add(ConfigPath.WHITELIST_ENABLED.getPath(), true);
        this.getConfig().add(ConfigPath.WHITELIST_WHITELIST.getPath(), IpmxNetworkMain.DEFAULT_WHITELIST);

        // Save default values to disk.
        this.getConfig().save();

        // The port the server should run on.
        final int port = this.getConfig().getInteger(ConfigPath.CONNECTION_PORT.getPath());

        // Set the server thread.
        if (this.getConfig().getBoolean(ConfigPath.WHITELIST_ENABLED.getPath(), true)) {
            this.server = new ServerThread(port, this.getConfig().getStringList(ConfigPath.WHITELIST_WHITELIST.getPath(),
                    IpmxNetworkMain.DEFAULT_WHITELIST));
        } else {
            this.getLogger().log(Level.WARNING, "The whitelist is disabled!");
            this.server = new ServerThread(port, null);
        }

        // Start server thread.
        this.server.start();
    }

    /**
     *
     * @return {@link IpmxNetworkMain#instance}
     */
    public static IpmxNetworkMain getInstance() {
        return IpmxNetworkMain.instance;
    }
}
