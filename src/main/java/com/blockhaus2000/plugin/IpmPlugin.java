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

import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Collection;
import java.util.logging.Level;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import com.blockhaus2000.util.ChatOut;
import com.blockhaus2000.util.CommandRegistrationUtil;
import com.blockhaus2000.util.TabCompleterRegistrationUtil;

/**
 * The main class that is used for plugin.
 *
 * @author Blockhaus2000
 */
public interface IpmPlugin extends Plugin, PropertyChangeListener {
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
     * @param file
     *            The {@link File} where the plugin is loaded from.
     */
    public void init(final IpmPluginDescription description, final File file);

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
    @Override
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
    @Override
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
    @Override
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
    @Override
    public void saveConfig();

    /**
     * Reloads the config.
     *
     */
    @Override
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
     * with <code>plugin</code> as the target plugin.
     *
     * @param clazz
     *            The {@link Class} where the commands are located.
     * @param obj
     *            An {@link Object} of the given {@link Class}.
     * @see com.blockhaus2000.util.CommandRegistrationUtil#registerCommands(java.lang.Class,
     *      java.lang.Object, org.bukkit.plugin.Plugin)
     */
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

    /**
     * Registers the given tab completer {@link Class} with the given
     * {@link Object}. Will call
     * {@link TabCompleterRegistrationUtil#registerTabCompleters(Class, Object)}
     * with <code>plugin</code> as the target plugin.
     *
     * @param clazz
     *            The {@link Class} where the tab completers are located.
     * @param obj
     *            An {@link Object} of the given {@link Class}.
     * @see com.blockhaus2000.util.TabCompleterRegistrationUtil#registerTabCompleters(java.lang.Class,
     *      java.lang.Object)
     */
    public void registerTabCompletions(final Class<?> clazz, final Object obj);

    /**
     * Registers the given tab completer {@link Class}. Will call
     * {@link IpmPlugin#registerTabCompletions(Class, Object)} with
     * <code>obj = null</code>.
     *
     * @param clazz
     *            The {@link Class} where the registerTabCompletions are
     *            located.
     * @see com.blockhaus2000.plugin.IpmPlugin#registerTabCompletions(java.lang.Class,
     *      java.lang.Object)
     */
    public void registerTabCompletions(final Class<?> clazz);

    /**
     * Registers the given tab completer {@link Object}. Will call
     * {@link IpmPlugin#registerTabCompletions(Class, Object)} with
     * <code>clazz = {@link Object#getClass()}</code>.
     *
     * @param obj
     *            The {@link Object} where the registerTabCompletions are
     *            located.
     * @see com.blockhaus2000.plugin.IpmPlugin#registerTabCompletions(java.lang.Class,
     *      java.lang.Object)
     */
    public void registerTabCompletions(final Object obj);

    /**
     *
     * @return If <code>true</code>, the plugin is enabled, if
     *         <code>false</code>, the plugin is disabled.
     */
    @Override
    public boolean isEnabled();

    /**
     * Sets the enabled status and the {@link IpmPlugin#onEnable()} (or
     * {@link IpmPlugin#onDisable()}, of course) will be callen.
     *
     * @param enabled
     *            The enabled status (<code>true</code> if enabled,
     *            <code>false</code> if disabled).
     */
    public void setEnabled(final boolean enabled);

    /**
     * Will call {@link ChatOut#log(Level, Object)}.
     *
     * @param level
     *            Will be overgiven.
     * @param msg
     *            Will be overgiven.
     * @param <T>
     *            The message data type.
     * @see com.blockhaus2000.util.ChatOut#log(java.util.logging.Level,
     *      java.lang.Object)
     */
    public <T> void log(final Level level, final T msg);

    /**
     * Will call {@link ChatOut#log(Object)}.
     *
     * @param msg
     *            Will be overgiven.
     * @param <T>
     *            The message data type.
     * @see com.blockhaus2000.util.ChatOut#log(java.lang.Object)
     */
    public <T> void log(final T msg);

    /**
     * Will call {@link ChatOut#log(Level, Collection)}.
     *
     * @param level
     *            Will be overgiven.
     * @param msg
     *            Will be overgiven.
     * @param <T>
     *            The message data type.
     * @see com.blockhaus2000.util.ChatOut#log(Collection)
     */
    public <T> void log(final Level level, final Collection<T> msg);

    /**
     * Will call {@link ChatOut#log(Collection)}.
     *
     * @param msg
     *            Will be overgiven.
     * @param <T>
     *            The message data type.
     * @see com.blockhaus2000.util.ChatOut#log(Collection)
     */
    public <T> void log(final Collection<T> msg);

    /**
     * Will call {@link ChatOut#log(Level, Object...)}.
     *
     * @param level
     *            Will be overgiven.
     * @param msg
     *            Will be overgiven.
     * @param <T>
     *            The message data type.
     * @see com.blockhaus2000.util.ChatOut#log(java.util.logging.Level,
     *      java.lang.Object...)
     */
    public <T> void log(final Level level, final T... msg);

    /**
     * Will call {@link ChatOut#log(Object...)}.
     *
     * @param msg
     *            Will be overgiven.
     * @param <T>
     *            The message data type.
     * @see com.blockhaus2000.util.ChatOut#log(java.lang.Object...)
     */
    public <T> void log(final T... msg);

    /**
     * Will call {@link ChatOut#sendMessage(CommandSender, Object)}.
     *
     * @param sender
     *            Will be overgiven.
     * @param msg
     *            Will be overgiven.
     * @param <T>
     *            The message data type.
     * @see com.blockhaus2000.util.ChatOut#sendMessage(org.bukkit.command.CommandSender,
     *      java.lang.Object)
     */
    public <T> void sendMessage(final CommandSender sender, final T msg);

    /**
     * Will call {@link ChatOut#sendMessage(CommandSender, Collection)}.
     *
     * @param sender
     *            Will be overgiven.
     * @param msg
     *            Will be overgiven.
     * @param <T>
     *            The message data type.
     * @see com.blockhaus2000.util.ChatOut#sendMessage(org.bukkit.command.CommandSender,
     *      java.util.Collection)
     */
    public <T> void sendMessage(final CommandSender sender, final Collection<T> msg);

    /**
     * Will call {@link ChatOut#sendMessage(CommandSender, Object...)}.
     *
     * @param sender
     *            Will be overgiven.
     * @param msg
     *            Will be overgiven.
     * @param <T>
     *            The message data type.
     * @see com.blockhaus2000.util.ChatOut#sendMessage(org.bukkit.command.CommandSender,
     *      java.lang.Object...)
     */
    public <T> void sendMessage(final CommandSender sender, final T... msg);

    /**
     *
     * @return An instance of {@link IpmServer}.
     */
    public IpmServer getIpmServer();

    /**
     *
     * @return An instance of {@link IpmPluginManager}.
     */
    public IpmPluginManager getPluginManager();

    /**
     *
     * @return An instance of {@link IpmPluginLoader}.
     */
    public IpmPluginLoader getIpmPluginLoader();

    /**
     *
     * @return The {@link File} where the plugin is loaded from.
     */
    public File getFile();

    /**
     *
     * @return The individual {@link FileConfiguration} of this plugin.
     */
    @Override
    public FileConfiguration getConfig();

    /**
     *
     * @return The individual {@link File} (a folder) where the config is placed
     *         and where you can place your own files.
     */
    @Override
    public File getDataFolder();

    /**
     *
     * @return The {@link IpmPluginDescription} (the <code>plugin.yml</code>)
     *         for this plugin.
     */
    public IpmPluginDescription getIpmDescription();

    /**
     *
     * @return The name of this plugin. The name is case-sensitive and is
     *         configurable in the {@link IpmPluginDescription} (the
     *         <code>plugin.yml</code>).
     */
    @Override
    public String getName();

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> Based on case-sensitive plugin name. </b>
     * </p>
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode();

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> Based on case-sensitive plugin name. </b>
     * </p>
     *
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(final Object obj);

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString();
}
