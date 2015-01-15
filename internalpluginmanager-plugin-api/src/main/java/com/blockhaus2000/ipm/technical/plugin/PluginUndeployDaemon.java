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
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.blockhaus2000.ipm.base.CollectionUtil;
import com.blockhaus2000.ipm.base.CommonConstants;

/**
 *
 * TODO: Add type description!
 *
 */
public class PluginUndeployDaemon extends Thread {
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
     * The plugin directory where to find undeploys.
     *
     */
    private final File pluginDir;

    /**
     * The loaded plugin files. Used to identify which plugins where undeployed.
     *
     */
    private Set<File> loadedPluginFiles;

    /**
     * Constructor of PluginUndeployListener.
     *
     * @param pluginDir
     *            The plugin directory where to scan for undeploys.
     */
    public PluginUndeployDaemon(final File pluginDir) {
        this.pluginDir = pluginDir;

        // Auto-Stop this thread at the and of the program, if not interrupted
        // earlier.
        this.setDaemon(true);
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        this.setLoadedPluginFiles();

        PluginUndeployDaemon.LOGGER.fine("Undeploy listener started for directory \"" + this.pluginDir.getAbsolutePath() + "\".");

        while (!this.isInterrupted()) {
            final Set<File> files = CollectionUtil.toSet(HashSet.class,
                    this.pluginDir.listFiles(PluginUndeployDaemon.JAR_FILE_FILTER));

            for (final File f : CollectionUtil.getDifferenceSet(this.loadedPluginFiles, files)) {
                PluginUndeployDaemon.LOGGER.log(Level.FINER, "Undeploying \"" + f.getAbsolutePath() + "\".");

                try {
                    this.undeploy(f);
                } catch (final Exception cause) {
                    PluginUndeployDaemon.LOGGER.log(Level.SEVERE,
                            "An error occurred whilest undeploying file \"" + f.getAbsolutePath() + "\"!", cause);
                }
            }

            this.setLoadedPluginFiles();

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (final InterruptedException dummy) {
                this.interrupt();
            }
        }
    }

    /**
     * Scans {@link PluginUndeployDaemon#pluginDir} and sets
     * {@link PluginUndeployDaemon#loadedPluginFiles}.
     *
     */
    private void setLoadedPluginFiles() {
        this.loadedPluginFiles = CollectionUtil.toSet(HashSet.class,
                this.pluginDir.listFiles(PluginUndeployDaemon.JAR_FILE_FILTER));

    }

    /**
     * Undeploys the given file.
     *
     * @param file
     *            The file to undeploy.
     * @throws IOException
     *             If an I/O error occurres.
     */
    private void undeploy(final File file) throws IOException {
        final PluginMeta pluginMeta = PluginLoader.getInstance().getMeta(file, false);
        final Plugin plugin = PluginManager.getInstance().getPlugin(pluginMeta.getName());
        PluginManager.getInstance().delete(plugin);
    }
}
