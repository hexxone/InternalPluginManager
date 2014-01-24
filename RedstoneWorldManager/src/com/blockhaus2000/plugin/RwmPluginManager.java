/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.plugin;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.Validate;

import com.blockhaus2000.main.bukkit.Main;
import com.blockhaus2000.plugin.exception.PluginException;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.resources.MainPluginResource;

/**
 * 
 * @author Blockhaus2000
 */
public class RwmPluginManager implements Iterable<RwmPluginData> {
    private static RwmPluginManager instance;

    @MainPluginResource
    private Main main;

    private Set<RwmPluginData> plugins = new HashSet<RwmPluginData>();

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
    public void addPlugin(final RwmPlugin plugin, final RwmPluginDescriptionFile desc) throws IllegalArgumentException {
        Validate.notNull(plugin, "Plugin cannot be null!");

        plugins.add(new RwmPluginData(plugin, desc));
    }

    public void registerPlugin(final RwmPlugin plugin, final RwmPluginDescriptionFile desc) throws IllegalArgumentException {
        Validate.notNull(plugin, "Plugin cannot be null!");

        plugin.onLoad();
        addPlugin(plugin, desc);
        plugin.onEnable();
    }

    public void registerAllPlugins() {
        try {
            for (RwmPluginDescriptionFile target : RwmPluginLoader.getInstance().loadAllPlugins()) {
                registerPlugin(target.getMain().newInstance(), target);
            }
        } catch (InstantiationException | IllegalAccessException | PluginException | IOException | IllegalArgumentException ex) {
            ExceptionHandler.handle(ex);
        }
    }

    public void removePlugin(final RwmPlugin plugin) throws IllegalArgumentException {
        Validate.notNull(plugin, "Plugin cannot be null!");

        plugins.remove(plugin);
    }

    public void unregisterPlugin(final RwmPlugin plugin) throws IllegalArgumentException {
        Validate.notNull(plugin, "Plugin cannot be null!");

        plugin.onDisable();
        removePlugin(plugin);
    }

    public void unregisterAllPlugins() {
        for (RwmPluginData target : plugins) {
            removePlugin(target.getPlugin());
        }
    }

    @Override
    public Iterator<RwmPluginData> iterator() {
        return plugins.iterator();
    }

    public static RwmPluginManager getInstance() {
        if (instance == null) {
            instance = new RwmPluginManager();
        }

        return instance;
    }

    // Getter + Setter
    // Getter
    public Set<RwmPluginData> getPlugins() {
        return plugins;
    }
}
