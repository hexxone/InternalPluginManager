/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.plugin;

import java.util.Set;

import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;

import com.blockhaus2000.configuration.Configuration;
import com.blockhaus2000.main.bukkit.Main;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.resources.MainPluginResource;
import com.blockhaus2000.util.resources.ResourceManager;

/**
 * 
 * @author Blockhaus2000
 */
public abstract class RwmPlugin {
    @MainPluginResource
    private Main main;

    private final Configuration config;

    public RwmPlugin() {
        try {
            ResourceManager.initializeResources(this);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            ExceptionHandler.handle(ex, true);
        }

        config = null;
    }

    /**
     * This method will be called before the {@link RwmPluginManager} adding it
     * to the internal {@link RwmPlugin} {@link Set}.
     * 
     * <p>
     * See {@link RwmPlugin#onDisable()} for the documentation of the reloading
     * process.
     * </p>
     */
    public void onLoad() {
        // Has to be empty (see JavaDoc)
    }

    /**
     * This method will be called if the target plugin is registered in the
     * {@link RwmPluginManager}. After adding it to the internal
     * {@link RwmPlugin} {@link Set}, this will be called. Before the adding,
     * {@link RwmPlugin#onLoad()} will be called.
     * 
     * <p>
     * See {@link RwmPlugin#onDisable()} for the documentation of the reloading
     * process.
     * </p>
     */
    public void onEnable() {
        // Has to be empty (see JavaDoc)
    }

    /**
     * This will be called, if the {@link Server} shuts down, or the whole
     * {@link Server} will be reloaded. In that case,
     * {@link RwmPlugin#onDisable()} and {@link RwmPlugin#onEnable()} will be
     * called instead to the other in the sorting disable - enable.
     * {@link RwmPlugin#onLoad()} will not be called.
     * 
     * <p>
     * See {@link RwmPlugin#onDisable()} for the documentation of the reloading
     * process.
     * </p>
     */
    public void onDisable() {
        // Has to be empty (see JavaDoc)
    }

    public void saveConfig() {
        // TODO
    }

    // Getter + Setter
    // Getter
    public Configuration getConfig() {
        return config;
    }

    @Deprecated
    public FileConfiguration getRawConfig() {
        return main.getConfig();
    }
}
