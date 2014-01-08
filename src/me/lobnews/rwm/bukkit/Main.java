/*
 * This file is part of RedstoneWorldManager
 * 
 */
package me.lobnews.rwm.bukkit;

import me.lobnews.util.ExceptionHandler;
import me.lobnews.util.PluginUtil;
import me.lobnews.util.resources.MainPluginResource;
import me.lobnews.util.resources.ResourceManager;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * 
 * @author Blockhaus2000
 */
public class Main extends JavaPlugin {
    private static Main instance;

    public Main() {
        instance = this;
    }

    /**
     * {@inheritDoc}
     */
    public void onEnable() {
        try {
            ResourceManager.initializeResources(new PluginUtil());
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            ExceptionHandler.handle(ex, true);
        }
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
