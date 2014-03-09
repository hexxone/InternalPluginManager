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
 * 
 * @author Blockhaus2000
 */
public class FakePlugin implements Plugin {
    private final String name;
    private final String version;

    public FakePlugin(final String name, final String version) {
        this.name = ChatColor.YELLOW + name;
        this.version = version;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        return false;
    }

    @Override
    public List<String> onTabComplete(final CommandSender sender, final Command command, final String alias, final String[] args) {
        return null;
    }

    @Override
    public File getDataFolder() {
        return null;
    }

    @Override
    public PluginDescriptionFile getDescription() {
        return new PluginDescriptionFile(name, version, null);
    }

    @Override
    public FileConfiguration getConfig() {
        return null;
    }

    @Override
    public InputStream getResource(final String filename) {
        return null;
    }

    @Override
    public void saveConfig() {
    }

    @Override
    public void saveDefaultConfig() {
    }

    @Override
    public void saveResource(final String resourcePath, final boolean replace) {
    }

    @Override
    public void reloadConfig() {
    }

    @Override
    public PluginLoader getPluginLoader() {
        return null;
    }

    @Override
    public Server getServer() {
        return Bukkit.getServer();
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onEnable() {
    }

    @Override
    public boolean isNaggable() {
        return false;
    }

    @Override
    public void setNaggable(final boolean canNag) {
    }

    @Override
    public EbeanServer getDatabase() {
        return null;
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(final String worldName, final String id) {
        return null;
    }

    @Override
    public Logger getLogger() {
        return Bukkit.getLogger();
    }

    @Override
    public String getName() {
        return name;
    }
}
