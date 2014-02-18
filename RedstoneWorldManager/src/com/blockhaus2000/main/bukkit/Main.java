/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.main.bukkit;

import org.bukkit.plugin.java.JavaPlugin;

import com.blockhaus2000.plugin.RwmPlugin;
import com.blockhaus2000.plugin.RwmPluginManager;
import com.blockhaus2000.util.CommandUtil;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.PluginUtil;
import com.blockhaus2000.util.resources.MainPluginResource;
import com.blockhaus2000.util.resources.ResourceManager;

/**
 * 
 * @author Blockhaus2000
 */
public class Main extends JavaPlugin {
    private static Main instance;

    public Main() {
        if (instance != null) {
            throw new IllegalStateException("Only bukkit initialize a new main plugin!");
        }

        instance = this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDisable() {
        RwmPluginManager.getInstance().unregisterAllPlugins();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnable() {
        try {
            ResourceManager.initializeResources(ExceptionHandler.class);
            ResourceManager.initializeResources(RwmPluginManager.getInstance());
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            ExceptionHandler.handle(ex);
            PluginUtil.disable(this);
        }

        RwmPluginManager.getInstance().registerAllPlugins();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLoad() {
        new RwmPlugin() {
            // Nothing to do (only to load the class)
        };
    }

    public void registerCommands(final Class<?> clazz, final Object obj) {
        CommandUtil.registerCommands(clazz, obj, this);
    }

    public void registerCommands(final Class<?> clazz) {
        registerCommands(clazz, null);
    }

    public void registerCommands(final Object obj) {
        registerCommands(obj.getClass(), obj);
    }

    /**
     * Returns you an instance from the {@link Main} class.
     * 
     * @deprecated Use the annotation {@link MainPluginResource} and call
     *             {@link ResourceManager#initializeResources(Object)} instead!
     * @return An instance from the {@link Main} class.
     */
    @Deprecated
    public static Main getInstance() {
        if (instance == null) {
            return instance = new Main();
        }

        return instance;
    }
}
