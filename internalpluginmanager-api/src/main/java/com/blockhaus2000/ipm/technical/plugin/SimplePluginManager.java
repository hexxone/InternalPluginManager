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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.blockhaus2000.ipm.technical.plugin.util.exception.MissingDependencyPluginException;

/**
 * An implementation of the {@link PluginManager}.
 *
 */
public class SimplePluginManager implements PluginManager {
    /**
     * THE instance of the {@link SimplePluginManager}.
     *
     */
    private static final PluginManager INSTANCE = new SimplePluginManager();

    /**
     * This {@link Map} contains all loaded plugins.
     *
     * <p>
     * <ul>
     * <li>Key ({@link String}): The plugin name.</li>
     * <li>Value ({@link Plugin}): The associated plugin.</li>
     * </ul>
     * </p>
     *
     */
    private final Map<String, Plugin> plugins = new HashMap<String, Plugin>();

    /**
     * The {@link PluginManager} status. If <code>true</code>, the
     * {@link PluginManager} is started. <code>false</code> otherwise.
     *
     */
    private boolean started = false;

    /**
     * The hot deploy daemon.
     *
     */
    private PluginDeployDaemon deployDaemon;

    /**
     * The hot deploy directory.
     *
     */
    private File deployDir;
    /**
     * The plugin directory were plugins are stored.
     *
     */
    private File pluginDir;
    /**
     * The plugin configuration directory.
     *
     */
    private File configDir;

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.PluginManager#start(java.io.File)
     */
    @Override
    public synchronized void start(final File directory) throws IllegalStateException {
        if (this.started) {
            throw new IllegalStateException("Already started!");
        }
        this.started = true;

        assert !directory.exists() || directory.isDirectory() : "\"" + directory.getAbsolutePath() + "\" has to be a directory!";
        directory.mkdirs();

        this.deployDir = new File(directory, "deploy");
        assert !this.deployDir.exists() || this.deployDir.isDirectory() : "\"" + this.deployDir.getAbsolutePath()
                + "\" has to be a directory!";
        this.deployDir.mkdirs();

        this.pluginDir = new File(directory, "plugins" + File.separator + "plugin");
        assert !this.pluginDir.exists() || this.pluginDir.isDirectory() : "\"" + this.pluginDir.getAbsolutePath()
                + "\" has to be a directory!";
        this.pluginDir.mkdirs();

        this.configDir = new File(directory, "plugins" + File.separator + "config");
        assert !this.configDir.exists() || this.configDir.isDirectory() : "\"" + this.configDir.getAbsolutePath()
                + "\" has to be a directory!";
        this.configDir.mkdirs();

        this.deployDaemon = new PluginDeployDaemon(this.deployDir, this.pluginDir);
        this.deployDaemon.start();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.PluginManager#remove(com.blockhaus2000.ipm.technical.plugin.Plugin)
     */
    @Override
    public synchronized void remove(final Plugin plugin) {
        this.remove(plugin, false);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.PluginManager#delete(com.blockhaus2000.ipm.technical.plugin.Plugin)
     */
    @Override
    public void delete(final Plugin plugin) {
        this.remove(plugin, true);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.PluginManager#disable(com.blockhaus2000.ipm.technical.plugin.Plugin)
     */
    @Override
    public synchronized void disable(final Plugin plugin) {
        this.checkAccess();

        if (!plugin.isEnabled()) {
            return;
        }

        final List<Plugin> depending = new ArrayList<Plugin>();
        final String name = plugin.getName();
        for (final Entry<String, Plugin> entry : this.plugins.entrySet()) {
            for (final String dep : entry.getValue().getPluginMeta().getDependencies()) {
                if (dep.equalsIgnoreCase(name)) {
                    depending.add(entry.getValue());
                }
            }
        }
        for (final Plugin dependingPlugin : depending) {
            this.disable(dependingPlugin);
        }

        plugin.setEnabled(false);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.PluginManager#disableAll()
     */
    @Override
    public void disableAll() {
        this.checkAccess();

        for (final Plugin plugin : this.plugins.values()) {
            this.disable(plugin);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.PluginManager#enable(com.blockhaus2000.ipm.technical.plugin.Plugin)
     */
    @Override
    public synchronized void enable(final Plugin plugin) {
        this.checkAccess();

        if (plugin.isEnabled()) {
            return;
        }

        final List<String> missingDeps = new ArrayList<String>();
        for (final String dep : plugin.getPluginMeta().getDependencies()) {
            if (this.getPlugin(dep) == null) {
                missingDeps.add(dep);
            }
        }
        if (!missingDeps.isEmpty()) {
            throw new MissingDependencyPluginException(plugin, missingDeps.toArray(new String[missingDeps.size()]));
        }

        plugin.setEnabled(true);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.PluginManager#enableAll()
     */
    @Override
    public void enableAll() { // TODO: Add dependecy resolving
        this.checkAccess();

        for (final Plugin plugin : this.plugins.values()) {
            this.enable(plugin);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.plugin.PluginManager#getPlugin(java.lang.String)
     */
    @Override
    public Plugin getPlugin(final String name) {
        this.checkAccess();

        return this.plugins.get(name.toLowerCase());
    }

    /**
     * Loads the plugin for the given {@link File}. If <code>enabled</code> is
     * <code>true</code>, this also enables the plugin after loading it.
     *
     * @param file
     *            The {@link File} that represents the plugin.
     * @param enable
     *            If <code>true</code>, this also enables the plugin after
     *            loading.
     * @param updateMeta
     *            If <code>true</code>, the plugin meta will be updated (read
     *            from the plugin jar). If <code>false</code>, the meta is read
     *            from the plugin meta cache.
     * @throws IOException
     *             Is thrown if {@link PluginLoader#getMeta(File, boolean)}
     *             throws it.
     */
    synchronized void loadPlugin(final File file, final boolean enable, final boolean updateMeta) throws IOException {
        this.checkAccess();

        assert file != null : "File cannot be null!";
        assert file.isFile() : "\"" + file.getAbsolutePath() + "\" has to be a file!";

        final PluginMeta meta = PluginLoader.getInstance().getMeta(file, updateMeta);

        final Plugin oldPlugin = this.getPlugin(meta.getName().toLowerCase());
        if (oldPlugin != null) {
            if (meta.getFile().equals(oldPlugin.getPluginMeta().getFile())) {
                this.remove(oldPlugin);
            } else {
                this.delete(oldPlugin);
            }
        }

        final Plugin plugin = PluginLoader.getInstance().load(meta);

        this.plugins.put(plugin.getName().toLowerCase(), plugin);
        plugin.onLoad();
        if (enable) {
            this.enable(plugin);
        }
    }

    /**
     *
     * @return {@link SimplePluginManager#pluginDir}
     */
    File getPluginDirectory() {
        this.checkAccess();

        return this.pluginDir;
    }

    /**
     * Removes the given plugin.
     *
     * @param plugin
     *            The {@link Plugin} to remove.
     * @param delete
     *            If <code>true</code>, the plugin will be deleted.
     */
    private synchronized void remove(final Plugin plugin, final boolean delete) {
        this.checkAccess();

        this.disable(plugin);
        if (delete) {
            PluginLoader.getInstance().delete(plugin);
        } else {
            PluginLoader.getInstance().remove(plugin);
        }
        this.plugins.remove(plugin.getName().toLowerCase());
    }

    /**
     * Checks if the access is valid. An access is valid if the
     * {@link PluginManager} is started. If not, the access is invalid.
     *
     * @throws IllegalStateException
     *             If the {@link PluginManager} is not started.
     */
    private void checkAccess() throws IllegalStateException {
        if (!this.started) {
            throw new IllegalStateException("Not started yet!");
        }
    }

    /**
     *
     * @return {@link SimplePluginManager#INSTANCE}
     */
    public static final PluginManager getInstance() {
        return SimplePluginManager.INSTANCE;
    }

    /**
     *
     * @return {@link SimplePluginManager#INSTANCE} (casted to
     *         {@link SimplePluginManager}).
     */
    static final SimplePluginManager getClassInstance() {
        return (SimplePluginManager) SimplePluginManager.INSTANCE;
    }
}
