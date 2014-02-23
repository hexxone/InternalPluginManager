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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.blockhaus2000.main.bukkit.IpmMain;
import com.blockhaus2000.util.CommandRegistrationUtil;
import com.blockhaus2000.util.ExceptionHandler;

/**
 * 
 * @author Blockhaus2000
 */
public class SimpleIpmPlugin implements IpmPlugin, PropertyChangeListener {
    protected final PropertyChangeSupport changes = new PropertyChangeSupport(this);

    private IpmPluginDescription description;

    private FileConfiguration config;
    private File configFile;

    private File dataFolder;

    private boolean enabled = false;

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#init(IpmPluginDescription)
     */
    @SuppressWarnings("deprecation")
    @Override
    public void init(final IpmPluginDescription description) {
        changes.addPropertyChangeListener(this);

        this.description = description;

        dataFolder = new File(IpmMain.getInstance().getDataFolder() + File.separator + "plugins" + File.separator
                + description.getName() + File.separator);
        configFile = new File(dataFolder.getPath() + File.separator + "config.yml");
        config = new YamlConfiguration();
        reloadConfig();
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#onLoad()
     */
    @Override
    public void onLoad() {
        // empty
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#onDisable()
     */
    @Override
    public void onDisable() {
        // empty
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#onEnable()
     */
    @Override
    public void onEnable() {
        // empty
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#saveConfig()
     */
    @Override
    public void saveConfig() throws IOException {
        config.save(configFile);
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#reloadConfig()
     */
    @Override
    public void reloadConfig() {
        try {
            config.load(configFile);
        } catch (FileNotFoundException ex) {
            // fails silent
        } catch (IOException ex) {
            ExceptionHandler.handle(ex);
        } catch (InvalidConfigurationException ex) {
            ExceptionHandler.handle(ex);
        }
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#loadConfig()
     */
    @Override
    public void loadConfig() {
        reloadConfig();
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#registerCommands(java.lang.Class,
     *      java.lang.Object)
     */
    @SuppressWarnings("deprecation")
    @Override
    public void registerCommands(final Class<?> clazz, final Object obj) {
        CommandRegistrationUtil.registerCommands(clazz, obj, IpmMain.getInstance());
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#registerCommands(java.lang.Class)
     */
    @Override
    public void registerCommands(final Class<?> clazz) {
        registerCommands(clazz, null);
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#registerCommands(java.lang.Object)
     */
    @Override
    public void registerCommands(final Object obj) {
        registerCommands(obj.getClass(), obj);
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#setEnabled(boolean)
     */
    @Override
    public void setEnabled(final boolean enabled) {
        boolean oldValue = this.enabled;

        this.enabled = enabled;

        changes.firePropertyChange("enabled", oldValue, enabled);
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#getServer()
     */
    @Override
    public IpmServer getServer() {
        return SimpleIpmServer.getInstance();
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#getConfig()
     */
    @Override
    public FileConfiguration getConfig() {
        return config;
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#getDataFolder()
     */
    @Override
    public File getDataFolder() {
        return dataFolder;
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#getDescription()
     */
    @Override
    public IpmPluginDescription getDescription() {
        return description;
    }

    /**
     * 
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        if (!event.getPropertyName().equalsIgnoreCase("enabled")) {
            return;
        }

        if (event.getNewValue().equals(true)) {
            onEnable();
        } else if (event.getNewValue().equals(false)) {
            onDisable();
        }
    }
}
