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
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;

import com.avaje.ebean.EbeanServer;
import com.blockhaus2000.configuration.Configuration;
import com.blockhaus2000.main.bukkit.IpmMain;

/**
 * 
 * @author Blockhaus2000
 */
@SuppressWarnings("deprecation")
public class IpmPlugin implements Plugin {
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
     * 
     * @see org.bukkit.plugin.java.JavaPlugin#onLoad()
     */
    @Override
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
     * 
     * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
     */
    @Override
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
     * @see org.bukkit.plugin.java.JavaPlugin#onDisable()
     */
    @Override
    public void onDisable() {
        // Has to be empty (see JavaDoc)
    }

    /**
     * <p>
     * <b> NOTE: This is disable (<code>final</code>)! Please use the command
     * system (with {@link com.blockhaus2000.minecraft.util.command.Command}
     * instead! </b>
     * </p>
     * 
     */
    @Override
    public final boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return false;
    }

    /**
     * <p>
     * <b> NOTE: This is disable (<code>final</code>)! </b>
     * </p>
     * 
     */
    @Override
    public final List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return null;
    }

    // Getter + Setter
    // Getter
    @Override
    public Configuration getConfig() {
        return config;
    }

    public FileConfiguration getRootConfig() {
        return IpmMain.getInstance().getConfig();
    }

    // ----------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------

    // This stuff has to be implemented but it is only directed to the root
    // plugin. Maybe some methods will be implemented explicit, but the usage
    // will not change.

    @Override
    public File getDataFolder() {
        return IpmMain.getInstance().getDataFolder();
    }

    @Override
    public EbeanServer getDatabase() {
        return IpmMain.getInstance().getDatabase();
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return IpmMain.getInstance().getDefaultWorldGenerator(worldName, id);
    }

    @Override
    public PluginDescriptionFile getDescription() {
        return IpmMain.getInstance().getDescription();
    }

    @Override
    public Logger getLogger() {
        return IpmMain.getInstance().getLogger();
    }

    @Override
    public String getName() {
        return IpmMain.getInstance().getName();
    }

    @Override
    public PluginLoader getPluginLoader() {
        return IpmMain.getInstance().getPluginLoader();
    }

    @Override
    public InputStream getResource(String resource) {
        return IpmMain.getInstance().getResource(resource);
    }

    @Override
    public Server getServer() {
        return IpmMain.getInstance().getServer();
    }

    @Override
    public boolean isEnabled() {
        return IpmMain.getInstance().isEnabled();
    }

    @Override
    public boolean isNaggable() {
        return IpmMain.getInstance().isNaggable();
    }

    @Override
    public void reloadConfig() {
        IpmMain.getInstance().reloadConfig();
        ;
    }

    @Override
    public void saveConfig() {
        IpmMain.getInstance().saveConfig();
        ;
    }

    @Override
    public void saveDefaultConfig() {
        IpmMain.getInstance().saveDefaultConfig();
        ;
    }

    @Override
    public void saveResource(String resource, boolean unknown) {
        IpmMain.getInstance().saveResource(resource, unknown);
    }

    @Override
    public void setNaggable(boolean canNag) {
        IpmMain.getInstance().setNaggable(canNag);
    }
}
