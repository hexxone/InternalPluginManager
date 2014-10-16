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
package com.blockhaus2000.ipm.technical.plugin;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.blockhaus2000.ipm.base.CommonConstants;

/**
 * The {@link PluginDeployDaemon} scans the deploy directory for new deployed
 * plugins, moves them to the plugin directory and deploys them into the
 * classpath, loads them and enables them. If the plugin is not new, it updates
 * the plugin.
 *
 */
public class PluginDeployDaemon extends Thread {
    /**
     * The InternalPluginManager system logger.
     *
     */
    private static final Logger LOGGER = Logger.getLogger(CommonConstants.INTERNALPLUGINMANAGER_SYSTEM_LOGGER_NAME);

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

        this.setDaemon(true);
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        this.loadAlreadyDeployedPlugins();

        PluginDeployDaemon.LOGGER.fine("Hot deploy daemon started on directory \"" + this.deployDir.getAbsolutePath() + "\".");

        while (!this.isInterrupted()) {
            for (final File rawFile : this.deployDir.listFiles(PluginDeployDaemon.JAR_FILE_FILTER)) {
                this.deploy(rawFile);
            }

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (final InterruptedException dummy) {
                this.interrupt();
            }
        }

        PluginDeployDaemon.LOGGER.fine("Hot deploy daemon stopped.");
    }

    /**
     * Loads the already deployed plugins from the plugin directory.
     *
     */
    private void loadAlreadyDeployedPlugins() {
        PluginDeployDaemon.LOGGER.fine("Loading plugins in directory \"" + this.pluginDir.getAbsolutePath() + "\".");

        for (final File file : this.pluginDir.listFiles(PluginDeployDaemon.JAR_FILE_FILTER)) {
            PluginDeployDaemon.LOGGER.info("Jar file \"" + file.getAbsolutePath() + "\" detected.");

            PluginDeployDaemon.LOGGER.finer("Starting deployment of \"" + file.getAbsolutePath() + "\".");

            try {
                PluginManager.getInstance().loadPlugin(file, true, false);
            } catch (final Exception ex) {
                ex.printStackTrace();
                PluginDeployDaemon.LOGGER.severe("An error occurred whilest deploying \"" + file.getAbsolutePath() + "\"!");
            }

            PluginDeployDaemon.LOGGER.finer("Deployment finished");
        }

        PluginDeployDaemon.LOGGER.fine("Loading finished.");
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

        PluginDeployDaemon.LOGGER.info("Jar file \"" + rawFile.getAbsolutePath() + "\" detected.");

        final File file = new File(this.pluginDir, rawFile.getName());

        PluginDeployDaemon.LOGGER.finer("Deleting old jar file \"" + file.getAbsolutePath() + "\".");

        file.delete();

        PluginDeployDaemon.LOGGER.finer("Moving new jar file \"" + rawFile.getAbsolutePath() + "\" to plugin directory.");

        rawFile.renameTo(file);

        PluginDeployDaemon.LOGGER.finer("Starting deployment of file \"" + file.getAbsolutePath() + "\" in a few seconds.");

        try {
            TimeUnit.SECONDS.sleep(2);

            // Only deploy if no InterruptedException occurred.
            try {
                PluginManager.getInstance().loadPlugin(file, true, true);
            } catch (final Exception ex) {
                ex.printStackTrace();
                PluginDeployDaemon.LOGGER.severe("An error occurred whilest deploying \"" + file.getAbsolutePath() + "\"!");
                PluginDeployDaemon.LOGGER.severe("Deleting \"" + file.getAbsolutePath() + "\"!");
                file.delete();
            }
        } catch (final InterruptedException ex) {
            PluginDeployDaemon.LOGGER.severe("An Exception occurres while waiting for deployment starting.");
            ex.printStackTrace();
            this.interrupt();
        }

        PluginDeployDaemon.LOGGER.finer("Deployment finished.");
    }
}
