/* This file is part of InternalPluginManager
 * 
 * Copyright 2014 Blockhaus2000
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  see the License for the specific language governing permissions and
 *  Limitations under the License.
 *  package com.blockhaus2000.bukkit.util;
 */
package com.blockhaus2000.plugin;

import java.util.Set;

import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;

import com.blockhaus2000.configuration.Configuration;
import com.blockhaus2000.main.bukkit.IpmMain;

/**
 * 
 * @author Blockhaus2000
 */
public abstract class IpmPlugin {
    private final Configuration config;

    public IpmPlugin() {
        config = null;
    }

    /**
     * This method will be called before the {@link IpmPluginManager} adding it
     * to the internal {@link IpmPlugin} {@link Set}.
     * 
     * <p>
     * See {@link IpmPlugin#onDisable()} for the documentation of the reloading
     * process.
     * </p>
     */
    public void onLoad() {
        // Has to be empty (see JavaDoc)
    }

    /**
     * This method will be called if the target plugin is registered in the
     * {@link IpmPluginManager}. After adding it to the internal
     * {@link IpmPlugin} {@link Set}, this will be called. Before the adding,
     * {@link IpmPlugin#onLoad()} will be called.
     * 
     * <p>
     * See {@link IpmPlugin#onDisable()} for the documentation of the reloading
     * process.
     * </p>
     */
    public void onEnable() {
        // Has to be empty (see JavaDoc)
    }

    /**
     * This will be called, if the {@link Server} shuts down, or the whole
     * {@link Server} will be reloaded. In that case,
     * {@link IpmPlugin#onDisable()} and {@link IpmPlugin#onEnable()} will be
     * called instead to the other in the sorting disable - enable.
     * {@link IpmPlugin#onLoad()} will not be called.
     * 
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
        return IpmMain.getInstance().getConfig();
    }
}
