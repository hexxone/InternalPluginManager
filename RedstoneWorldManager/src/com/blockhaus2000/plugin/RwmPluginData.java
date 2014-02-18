/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.plugin;

/**
 * 
 * @author Blockhaus2000
 */
public class RwmPluginData {
    private final RwmPlugin plugin;
    private final RwmPluginDescriptionFile description;

    public RwmPluginData(final RwmPlugin plugin, final RwmPluginDescriptionFile description) {
        this.plugin = plugin;
        this.description = description;
    }

    // Getter
    public RwmPlugin getPlugin() {
        return plugin;
    }

    public RwmPluginDescriptionFile getDescription() {
        return description;
    }
}
