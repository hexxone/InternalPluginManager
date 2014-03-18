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
 */
package com.blockhaus2000.plugin;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;

import com.avaje.ebean.EbeanServer;

/**
 * A fake {@link Plugin} that is used to "register" plugins in Bukkit.
 * 
 * @author Blockhaus2000
 */
public class FakePlugin implements Plugin {
    private final String name;
    private final String version;

    /**
     * Instances a new {@link FakePlugin}.
     * 
     * @param name
     *            The name of the plugin.
     * @param version
     *            The version of the plugin.
     */
    public FakePlugin(final String name, final String version) {
        this.name = ChatColor.YELLOW + name;
        this.version = version;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.command.CommandExecutor#onCommand(org.bukkit.command.CommandSender,
     *      org.bukkit.command.Command, java.lang.String, java.lang.String[])
     */
    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.command.TabCompleter#onTabComplete(org.bukkit.command.CommandSender,
     *      org.bukkit.command.Command, java.lang.String, java.lang.String[])
     */
    @Override
    public List<String> onTabComplete(final CommandSender sender, final Command command, final String alias, final String[] args) {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#getDataFolder()
     */
    @Override
    public File getDataFolder() {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#getDescription()
     */
    @Override
    public PluginDescriptionFile getDescription() {
        return new PluginDescriptionFile(name, version, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#getConfig()
     */
    @Override
    public FileConfiguration getConfig() {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#getResource(java.lang.String)
     */
    @Override
    public InputStream getResource(final String filename) {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#saveConfig()
     */
    @Override
    public void saveConfig() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#saveDefaultConfig()
     */
    @Override
    public void saveDefaultConfig() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#saveResource(java.lang.String, boolean)
     */
    @Override
    public void saveResource(final String resourcePath, final boolean replace) {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#reloadConfig()
     */
    @Override
    public void reloadConfig() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#getPluginLoader()
     */
    @Override
    public PluginLoader getPluginLoader() {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#getServer()
     */
    @Override
    public Server getServer() {
        return Bukkit.getServer();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#onDisable()
     */
    @Override
    public void onDisable() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#onLoad()
     */
    @Override
    public void onLoad() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#onEnable()
     */
    @Override
    public void onEnable() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#isNaggable()
     */
    @Override
    public boolean isNaggable() {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#setNaggable(boolean)
     */
    @Override
    public void setNaggable(final boolean canNag) {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#getDatabase()
     */
    @Override
    public EbeanServer getDatabase() {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#getDefaultWorldGenerator(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public ChunkGenerator getDefaultWorldGenerator(final String worldName, final String id) {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#getLogger()
     */
    @Override
    public Logger getLogger() {
        return Bukkit.getLogger();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.bukkit.plugin.Plugin#getName()
     */
    @Override
    public String getName() {
        return name;
    }
}
