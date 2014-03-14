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
import java.util.Collection;
import java.util.logging.Level;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import com.blockhaus2000.main.bukkit.IpmMain;
import com.blockhaus2000.util.CommandRegistrationUtil;

/**
 * 
 * @author Blockhaus2000
 */
public interface IpmPlugin {
    /**
     * This inits the target {@link IpmPlugin}.
     * 
     * <p>
     * <b> NOTE: This is only for internal use, and you should not call it
     * explicit! </b>
     * </p>
     * 
     * @param description
     *            The init {@link IpmPluginDescription}.
     */
    public void init(final IpmPluginDescription description);

    /**
     * Will be callen on load.
     * 
     * <p>
     * <b> NOTE: This will not be callen in dependency-order! </b>
     * </p>
     * <p>
     * <b> NOTE: Do not call this explicit! </b>
     * </p>
     * 
     */
    public void onLoad();

    /**
     * Will be callen on disable.
     * 
     * <p>
     * <b> NOTE: This will be callen if you set the enabled-status to
     * <code>true</code> with {@link IpmPlugin#setEnabled(boolean)}. Do not call
     * this explicit! </b>
     * </p>
     * 
     */
    public void onDisable();

    /**
     * Will be callen on disable.
     * 
     * <p>
     * <b> NOTE: This will be callen if you set the enabled-status to
     * <code>false</code> with {@link IpmPlugin#setEnabled(boolean)}. Do not
     * call this explicit! </b>
     * </p>
     * 
     */
    public void onEnable();

    /**
     * Will reload the plugin.
     * 
     */
    public void reload();

    /**
     * Will save the default plugin config to the default path.
     * 
     */
    public void saveConfig();

    /**
     * Reloads the config.
     * 
     */
    public void reloadConfig();

    /**
     * Loads the config, but will call {@link IpmPlugin#reloadConfig()}.
     * 
     * @see com.blockhaus2000.plugin.IpmPlugin#reloadConfig()
     */
    public void loadConfig();

    /**
     * Registers the given command {@link Class} with the given {@link Object}.
     * Will call
     * {@link CommandRegistrationUtil#registerCommands(Class, Object, org.bukkit.plugin.Plugin)}
     * with <code>plugin = {@link IpmMain#getInstance()}</code>.
     * 
     * @param clazz
     *            The {@link Class} where the commands are located.
     * @param obj
     *            An {@link Object} of the given {@link Class}.
     * @see com.blockhaus2000.util.CommandRegistrationUtil#registerCommands(java.lang.Class,
     *      java.lang.Object, org.bukkit.plugin.Plugin)
     */
    // for link to IpmMain#getInstance()
    @SuppressWarnings("javadoc")
    public void registerCommands(final Class<?> clazz, final Object obj);

    /**
     * Registers the given command {@link Class}. Will call
     * {@link IpmPlugin#registerCommands(Class, Object)} with
     * <code>obj = null</code>.
     * 
     * @param clazz
     *            The {@link Class} where the commands are located.
     * @see com.blockhaus2000.plugin.IpmPlugin#registerCommands(java.lang.Class,
     *      java.lang.Object)
     */
    public void registerCommands(final Class<?> clazz);

    /**
     * Registers the given command {@link Object}. Will call
     * {@link IpmPlugin#registerCommands(Class, Object)} with
     * <code>clazz = {@link Object#getClass()}</code>.
     * 
     * @param obj
     *            The {@link Object} where the commands are located.
     * @see com.blockhaus2000.plugin.IpmPlugin#registerCommands(java.lang.Class,
     *      java.lang.Object)
     */
    public void registerCommands(final Object obj);

    public boolean isEnabled();

    public void setEnabled(final boolean enabled);

    public <T> void log(final Level level, final T msg);

    public <T> void log(final T msg);

    public <T> void log(final Level level, final Collection<T> msg);

    public <T> void log(final Collection<T> msg);

    public <T> void log(final Level level, final T... msg);

    public <T> void log(final T... msg);

    public <T> void sendMessage(final CommandSender sender, final T msg);

    public <T> void sendMessage(final CommandSender sender, final Collection<T> msg);

    public <T> void sendMessage(final CommandSender sender, final T... msg);

    public IpmServer getServer();

    public FileConfiguration getConfig();

    public File getDataFolder();

    public IpmPluginDescription getDescription();

    public String getName();
}
