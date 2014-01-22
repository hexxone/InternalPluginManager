/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.plugin;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.Validate;

import com.blockhaus2000.main.bukkit.Main;
import com.blockhaus2000.util.resources.MainPluginResource;

/**
 * 
 * @author Blockhaus2000
 */
public class RwmPluginManager implements Iterable<RwmPlugin> {
    private static RwmPluginManager instance;

    @MainPluginResource
    private Main main;

    private Set<RwmPlugin> plugins = new HashSet<RwmPlugin>();

    private RwmPluginManager() {
        // Nothing to do (only to provide singleton pattern)
    }

    /**
     * This will add the given {@link RwmPlugin} to
     * {@link RwmPluginManager#plugins}.
     * 
     * <p>
     * <b> Note: This will NOT register the given plugin (does not call
     * {@link RwmPlugin#onEnable()} or something like that). </b>
     * </p>
     * 
     * @param plugin
     *            The {@link RwmPlugin} that has to be added to the {@link Set}
     *            {@link RwmPluginManager#plugins}.
     * @throws IllegalArgumentException
     *             Will be throwed if the given {@link RwmPlugin} is
     *             <code>null</code>.
     */
    public void addPlugin(final RwmPlugin plugin) throws IllegalArgumentException {
        Validate.notNull(plugin, "Plugin cannot be null!");

        plugins.add(plugin);
    }

    public void registerPlugin(final RwmPlugin plugin) {
        Validate.notNull(plugin, "Plugin cannot be null!");

        plugin.onLoad();

        addPlugin(plugin);

        plugin.onEnable();
    }

    public void removePlugin(final RwmPlugin plugin) {
        Validate.notNull(plugin, "Plugin cannot be null!");

        plugins.remove(plugin);
    }

    @Override
    public Iterator<RwmPlugin> iterator() {
        return plugins.iterator();
    }

    public void unregisterPlugin(final RwmPlugin plugin) {
        plugin.onDisable();
    }

    public static RwmPluginManager getInstance() {
        if (instance == null) {
            instance = new RwmPluginManager();
        }

        return instance;
    }

    // Getter + Setter
    // Getter
    public Set<RwmPlugin> getPlugins() {
        return plugins;
    }
}
