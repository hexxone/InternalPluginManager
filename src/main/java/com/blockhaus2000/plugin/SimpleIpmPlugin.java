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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;

import com.avaje.ebean.EbeanServer;
import com.blockhaus2000.bukkit.mock.MockPluginLoader;
import com.blockhaus2000.main.bukkit.InternalPluginManager;
import com.blockhaus2000.main.bukkit.IpmMain;
import com.blockhaus2000.util.ChatOut;
import com.blockhaus2000.util.CommandRegistrationUtil;
import com.blockhaus2000.util.ExceptionHandler;

/**
 * An implmenetation of {@link IpmPlugin}.
 *
 * @author Blockhaus2000
 */
public class SimpleIpmPlugin implements IpmPlugin {
    protected final PropertyChangeSupport changes = new PropertyChangeSupport(this);

    private PluginDescriptionFile bukkitDescription;

    private IpmPluginDescription description;
    private String name;

    private FileConfiguration config;
    private File configFile;

    private File dataFolder;

    private File file;

    private boolean enabled = false;

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#init(IpmPluginDescription, File)
     */
    // com.blockhaus2000.main.bukkit.IpmMain#getInstance()
    @SuppressWarnings("deprecation")
    @Override
    public void init(final IpmPluginDescription description, final File file) {
        changes.addPropertyChangeListener(this);

        this.file = file;
        this.description = description;

        dataFolder = new File(IpmMain.getInstance().getDataFolder() + File.separator + "plugins" + File.separator
                + description.getName() + File.separator);
        configFile = new File(dataFolder.getPath() + File.separator + "config.yml");
        config = new YamlConfiguration();
        reloadConfig();

        name = this.description.getName();

        bukkitDescription = new PluginDescriptionFile(name, this.description.getVersion(), this.description.getMain());
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#onLoad()
     */
    @Override
    public void onLoad() {
        // empty
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#onDisable()
     */
    @Override
    public void onDisable() {
        // empty
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#onEnable()
     */
    @Override
    public void onEnable() {
        // empty
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#reload()
     */
    @Override
    public void reload() {
        SimpleIpmPluginManager.getInstance().reload(this);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#saveConfig()
     */
    @Override
    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#loadConfig()
     */
    @Override
    public void loadConfig() {
        reloadConfig();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#registerCommands(java.lang.Class,
     *      java.lang.Object)
     */
    @Override
    public void registerCommands(final Class<?> clazz, final Object obj) {
        CommandRegistrationUtil.registerCommands(clazz, obj, this);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#registerCommands(java.lang.Object)
     */
    @Override
    public void registerCommands(final Object obj) {
        registerCommands(obj.getClass(), obj);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#registerCommands(java.lang.Class)
     */
    @Override
    public void registerCommands(final Class<?> clazz) {
        registerCommands(clazz, null);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#log(java.util.logging.Level,
     *      java.lang.Object)
     */
    @Override
    public <T> void log(final Level level, final T msg) {
        ChatOut.log(level, msg);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#log(java.lang.Object)
     */
    @Override
    public <T> void log(final T msg) {
        ChatOut.log(msg);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#log(java.util.logging.Level,
     *      java.util.Collection)
     */
    @Override
    public <T> void log(final Level level, final Collection<T> msg) {
        ChatOut.log(level, msg);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#log(java.util.Collection)
     */
    @Override
    public <T> void log(final Collection<T> msg) {
        ChatOut.log(msg);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#log(java.util.logging.Level,
     *      java.lang.Object[])
     */
    @Override
    public <T> void log(final Level level, final T... msg) {
        ChatOut.log(level, msg);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#log(java.lang.Object[])
     */
    @Override
    public <T> void log(final T... msg) {
        ChatOut.log(msg);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#sendMessage(org.bukkit.command.CommandSender,
     *      java.lang.Object)
     */
    @Override
    public <T> void sendMessage(final CommandSender sender, final T msg) {
        ChatOut.sendMessage(sender, msg);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#sendMessage(org.bukkit.command.CommandSender,
     *      java.util.Collection)
     */
    @Override
    public <T> void sendMessage(final CommandSender sender, final Collection<T> msg) {
        ChatOut.sendMessage(sender, msg);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#sendMessage(org.bukkit.command.CommandSender,
     *      java.lang.Object[])
     */
    @Override
    public <T> void sendMessage(final CommandSender sender, final T... msg) {
        ChatOut.sendMessage(sender, msg);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#getServer()
     */
    @Override
    public IpmServer getIpmServer() {
        return SimpleIpmServer.getInstance();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#getPluginManager()
     */
    @Override
    public IpmPluginManager getPluginManager() {
        return InternalPluginManager.getServer().getPluginManager();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#getIpmPluginLoader()
     */
    @Override
    public IpmPluginLoader getIpmPluginLoader() {
        return InternalPluginManager.getServer().getPluginLoader();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#getFile()
     */
    @Override
    public File getFile() {
        return file;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#getConfig()
     */
    @Override
    public FileConfiguration getConfig() {
        return config;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#getDataFolder()
     */
    @Override
    public File getDataFolder() {
        return dataFolder;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#getIpmDescription()
     */
    @Override
    public IpmPluginDescription getIpmDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.plugin.IpmPlugin#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        if (!event.getPropertyName().equalsIgnoreCase("enabled")) {
            return;
        }

        if (event.getNewValue().equals(true)) {
            System.out.println("[" + getIpmDescription().getName() + "] Enabling " + getIpmDescription().getName() + " v"
                    + getIpmDescription().getVersion());
            onEnable();
        } else if (event.getNewValue().equals(false)) {
            System.out.println("[" + getIpmDescription().getName() + "] Disabling " + getIpmDescription().getName() + " v"
                    + getIpmDescription().getVersion());
            onDisable();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return 31 + (name == null ? 0 : name.hashCode());
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || !(obj instanceof SimpleIpmPlugin)) {
            return false;
        }

        IpmPlugin that = (SimpleIpmPlugin) obj;

        if (name == null || that.getName() == null) {
            return false;
        }

        return name.equals(that.getName());
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getName() + "[description=" + description + ", name=" + name + ", configFile=" + configFile
                + ", dataFolder=" + dataFolder + ", enabled=" + enabled + "]";
    }

    // Mock implementations
    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is a mock implementation! Use
     * {@link IpmPlugin#getIpmDescription()} instead. </b>
     * </p>
     *
     * @return A {@link PluginDescriptionFile} with the plugin name, the version
     *         and the main class.
     * @see org.bukkit.plugin.Plugin#getDescription()
     */
    @Override
    public PluginDescriptionFile getDescription() {
        return bukkitDescription;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is a mock implementation! </b>
     * </p>
     *
     * @return <code>null</code>
     * @see org.bukkit.plugin.Plugin#getResource(java.lang.String)
     */
    @Override
    public InputStream getResource(final String filename) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is a mock implementation! </b>
     * </p>
     *
     * @see org.bukkit.plugin.Plugin#saveDefaultConfig()
     */
    @Override
    public void saveDefaultConfig() {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is a mock implementation! </b>
     * </p>
     *
     * @see org.bukkit.plugin.Plugin#saveResource(java.lang.String, boolean)
     */
    @Override
    public void saveResource(final String resourcePath, final boolean replace) {
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.plugin.Plugin#getPluginLoader()
     */
    @Override
    public PluginLoader getPluginLoader() {
        return MockPluginLoader.getInstance();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link Bukkit#getServer()}
     * @see org.bukkit.plugin.Plugin#getServer()
     * @see org.bukkit.Bukkit#getServer()
     */
    @Override
    public Server getServer() {
        return Bukkit.getServer();
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is a mock implementation! </b>
     * </p>
     *
     * @return <code>false</code>
     * @see org.bukkit.plugin.Plugin#isNaggable()
     */
    @Override
    public boolean isNaggable() {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is a mock implementation! </b>
     * </p>
     *
     * @see org.bukkit.plugin.Plugin#setNaggable(boolean)
     */
    @Override
    public void setNaggable(final boolean canNag) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is a mock implementation and returns the database from the
     * root plugin! </b>
     * </p>
     *
     * @return {@link IpmMain#getDatabase()}
     * @see org.bukkit.plugin.Plugin#getDatabase()
     * @see com.blockhaus2000.main.bukkit.IpmMain#getDatabase()
     */
    @SuppressWarnings("deprecation")
    @Override
    public EbeanServer getDatabase() {
        return IpmMain.getInstance().getDatabase();
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is a mock implementation and returns the default world
     * generator from the root plugin! </b>
     * </p>
     *
     * @return {@link IpmMain#getDefaultWorldGenerator(String, String)}
     * @see org.bukkit.plugin.Plugin#getDefaultWorldGenerator(java.lang.String,
     *      java.lang.String)
     * @see com.blockhaus2000.main.bukkit.IpmMain#getDefaultWorldGenerator(java.lang.String,
     *      java.lang.String)
     */
    @SuppressWarnings("deprecation")
    @Override
    public ChunkGenerator getDefaultWorldGenerator(final String worldName, final String id) {
        return IpmMain.getInstance().getDefaultWorldGenerator(worldName, id);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is a mock implementation and returns the logger from the
     * root plugin! </b>
     * </p>
     *
     * @return {@link IpmMain#getLogger()}
     * @see org.bukkit.plugin.Plugin#getLogger()
     * @see com.blockhaus2000.main.bukkit.IpmMain#getLogger()
     */
    @SuppressWarnings("deprecation")
    @Override
    public Logger getLogger() {
        return IpmMain.getInstance().getLogger();
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is a mock implementation! </b>
     * </p>
     *
     * @return <code>false</code>
     * @see org.bukkit.command.CommandExecutor#onCommand(org.bukkit.command.CommandSender,
     *      org.bukkit.command.Command, java.lang.String, java.lang.String[])
     */
    @Override
    public final boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * <b> NOTE: This is a mock implementation! </b>
     * </p>
     *
     * @return <code>null</code>
     * @see org.bukkit.command.TabCompleter#onTabComplete(org.bukkit.command.CommandSender,
     *      org.bukkit.command.Command, java.lang.String, java.lang.String[])
     */
    @Override
    public final List<String> onTabComplete(final CommandSender sender, final Command command, final String alias,
            final String[] args) {
        return null;
    }
}
