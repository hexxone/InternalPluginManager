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
package com.blockhaus2000.ipm.technical.plugin;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link PluginDeployDaemon} scans the deploy directory for new deployed
 * plugins, moves them to the plugin directory and deploys them into the
 * classpath, loads them and enables them. If the plugin is not new, it updates
 * the plugin.
 *
 */
public class PluginDeployDaemon extends Thread {
    /**
     * The Logger of this class.
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PluginDeployDaemon.class);

    /**
     * A {@link FileFilter} that only allowes jar files.
     *
     */
    private static final FileFilter JAR_FILE_FILTER = new FileFilter() {
        /**
         * {@inheritDoc}
         *
         * @return <code>true</code> if {@link File} is not <code>null</code>,
         *         is a file and (the name) ends with <code>.jar</code>.
         *         <code>false</code> otherwise.
         * @see java.io.FileFilter#accept(java.io.File)
         */
        @Override
        public boolean accept(final File file) {
            return file != null && file.isFile() && file.getName().endsWith(".jar");
        }
    };

    /**
     * The hot deploy directory.
     *
     */
    private final File deployDir;
    /**
     * The plugin directory.
     *
     */
    private final File pluginDir;

    /**
     * The undeploy daemon (the other hand of this daemon).
     *
     */
    private final Thread pluginUndeployDaemon;

    /**
     * Constructor of PluginDeployDeamon.
     *
     * @param deployDir
     *            The deploy directory to scan for new plugins.
     * @param pluginDir
     *            The internal plugin directory.
     */
    public PluginDeployDaemon(final File deployDir, final File pluginDir) {
        assert deployDir != null : "DeployDir cannot be null!";
        assert deployDir.exists() : "DeployDir has to exist!";
        assert deployDir.isDirectory() : "DeployDir has to be a directory!";

        assert pluginDir != null : "PluginDir cannot be null!";
        assert pluginDir.exists() : "PluginDir has to exist!";
        assert pluginDir.isDirectory() : "PluginDir has to be a directory!";

        this.deployDir = deployDir;
        this.pluginDir = pluginDir;

        this.pluginUndeployDaemon = new PluginUndeployDaemon(pluginDir);

        // Auto-Stop this thread at the and of the program, if not interrupted
        // earlier.
        this.setDaemon(true);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This also starts a {@link PluginUndeployDaemon}. </b>
     * </p>
     *
     *
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        PluginDeployDaemon.LOGGER.info("Plugin deploy daemon started for directory " + this.deployDir.getPath());

        this.loadAlreadyDeployedPlugins();

        // Start undeploy daemon after loading all already deplyoed plugins.
        this.pluginUndeployDaemon.start();

        while (!this.isInterrupted()) {
            for (final File rawFile : this.deployDir.listFiles(PluginDeployDaemon.JAR_FILE_FILTER)) {
                PluginDeployDaemon.LOGGER.debug("Deploying file " + rawFile.getPath());

                this.deploy(rawFile);
            }

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (final InterruptedException dummy) {
                this.interrupt();
            }
        }

        // Also stop undeploy daemon.
        this.pluginUndeployDaemon.interrupt();
        try {
            this.pluginUndeployDaemon.join();
        } catch (final InterruptedException dummy) {
            // The thread was shut down before join() was invoked. Ignore as
            // the thread stopped already.
        }

        PluginDeployDaemon.LOGGER.info("Plugin deploy daemon stopped");
    }

    /**
     * Loads the already deployed plugins from the plugin directory.
     *
     */
    private void loadAlreadyDeployedPlugins() {
        PluginDeployDaemon.LOGGER.debug("Loading already deployed plugins");

        for (final File file : this.pluginDir.listFiles(PluginDeployDaemon.JAR_FILE_FILTER)) {
            try {
                PluginManager.getInstance().loadPlugin(file, false, false);
            } catch (final Exception cause) {
                PluginDeployDaemon.LOGGER.error("An error occurred whilest deploying \"" + file.getAbsolutePath() + "\"!", cause);
            }
        }

        try {
            PluginManager.getInstance().enableAll();
        } catch (final Exception cause) {
            PluginDeployDaemon.LOGGER.error("An error occurred whilest enabling all (loaded) plugins!", cause);
        }

        PluginDeployDaemon.LOGGER.debug("Already deploy plugins loaded");
    }

    /**
     * Deploys/Loads the given {@link File}
     *
     * @param rawFile
     *            The {@link File} to load (and deploy).
     */
    private void deploy(final File rawFile) {
        assert rawFile != null : "RawFile cannot be null!";
        assert rawFile.exists() : "RawFile has to exist!";
        assert rawFile.isFile() : "RawFile has to be a file!";

        PluginDeployDaemon.LOGGER.debug("Deploying file " + rawFile.getPath());

        final File file = new File(this.pluginDir, rawFile.getName());
        file.delete();
        rawFile.renameTo(file);

        try {
            TimeUnit.SECONDS.sleep(2);

            // Only deploy if no InterruptedException occurred.
            try {
                PluginManager.getInstance().loadPlugin(file, true, true);
            } catch (final Exception cause) {
                PluginDeployDaemon.LOGGER.error("An error occurred whilest deploying \"" + file.getAbsolutePath() + "\"!", cause);
                PluginDeployDaemon.LOGGER.error("Deleting \"" + file.getAbsolutePath() + "\"!");
                file.delete();
            }
        } catch (final InterruptedException cause) {
            PluginDeployDaemon.LOGGER.error("An Exception occurres while waiting for deployment starting.", cause);
            this.interrupt();
        }
    }
}
