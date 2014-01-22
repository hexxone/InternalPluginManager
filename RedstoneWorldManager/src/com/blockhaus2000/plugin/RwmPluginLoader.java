/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.plugin;

import java.io.File;
import java.util.List;

import com.blockhaus2000.main.bukkit.Main;
import com.blockhaus2000.plugin.exception.PluginNotFoundException;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.resources.MainPluginResource;
import com.blockhaus2000.util.resources.ResourceManager;

/**
 * 
 * @author Blockhaus2000
 */
public class RwmPluginLoader {
    private static RwmPluginLoader instance;

    @MainPluginResource
    private Main main;

    private final String mainPluginPath;

    private RwmPluginLoader() {
        try {
            ResourceManager.initializeResources(this);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            ExceptionHandler.handle(ex, true);
        }

        mainPluginPath = main.getDataFolder().getAbsolutePath() + "/plugins/%pluginName%.jar";
    }

    public RwmPlugin loadPlugin(final String pluginName) {
        String pluginPath = mainPluginPath.replace("%pluginName%", pluginName);

        File file = new File(pluginPath);

        if (!file.exists()) {
            throw new PluginNotFoundException("The plugin " + pluginName + " cannot be found at " + pluginPath);
        }

        return null; // TODO
    }

    public List<RwmPlugin> loadAllPlugins() {
        return null; // TODO
    }

    public static RwmPluginLoader getInstance() {
        if (instance == null) {
            instance = new RwmPluginLoader();
        }

        return instance;
    }
}
