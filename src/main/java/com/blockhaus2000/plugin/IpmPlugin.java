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

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * 
 * @author Blockhaus2000
 */
public interface IpmPlugin {
    public void init(final IpmPluginDescription description);

    public void onLoad();

    public void onDisable();

    public void onEnable();

    public void saveConfig() throws IOException;

    public void reloadConfig();

    public void loadConfig();

    public void registerCommands(final Class<?> clazz, final Object obj);

    public void registerCommands(final Class<?> clazz);

    public void registerCommands(final Object obj);

    public boolean isEnabled();

    public void setEnabled(final boolean enabled);

    public IpmServer getServer();

    public FileConfiguration getConfig();

    public File getDataFolder();

    public IpmPluginDescription getDescription();
}
