/*
 * This file is part of RedstoneWorldManager
 * 
 */
package me.lobnews.rwm.bukkit;

import org.bukkit.plugin.java.JavaPlugin;

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
    public void onEnable() {
        try {
            ResourceManager.initializeResources(ExceptionHandler.class);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            ExceptionHandler.handle(ex);
            PluginUtil.disable(this);
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
