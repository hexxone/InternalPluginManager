/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.configuration;

import com.blockhaus2000.main.bukkit.Main;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.resources.MainPluginResource;
import com.blockhaus2000.util.resources.ResourceManager;

/**
 * 
 * @author Blockhaus2000
 */
public class ConfigurationManager {
    private static ConfigurationManager instance;

    @MainPluginResource
    private Main main;

    private ConfigurationManager() {
        try {
            ResourceManager.initializeResources(this);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            ExceptionHandler.handle(ex, true);
        }
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }

        return instance;
    }
}
