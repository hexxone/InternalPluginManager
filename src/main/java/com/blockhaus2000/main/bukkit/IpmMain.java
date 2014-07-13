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
package com.blockhaus2000.main.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.blockhaus2000.commands.Commands;
import com.blockhaus2000.commands.PluginManagerCommands;
import com.blockhaus2000.commands.PluginManagerTabCompleter;
import com.blockhaus2000.commands.test.TestCommands;
import com.blockhaus2000.plugin.SimpleIpmPluginLoader;
import com.blockhaus2000.plugin.SimpleIpmPluginManager;
import com.blockhaus2000.plugin.exception.PluginException;
import com.blockhaus2000.util.CommandRegistrationUtil;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.PluginUtil;
import com.blockhaus2000.util.TabCompleterRegistrationUtil;
import com.blockhaus2000.util.resources.MainPluginResource;
import com.blockhaus2000.util.resources.ResourceManager;

/**
 * Represents the main plugin of the InternalPluginManager.
 *
 * @see org.bukkit.plugin.Plugin
 * @see org.bukkit.plugin.java.JavaPlugin
 */
public class IpmMain extends JavaPlugin {
    /**
     * Indicates that the debug mode is enabled (for unit tests with the
     * bukkit-tester-maven-plugin, it has to be <code>true</code>). For normal
     * usage, this should be false. You can set it with the system property
     * <code>internalpluginmanager.debug-mode</code>. You have to set it to
     * <code>true</code>.
     *
     */
    public static final boolean DEBUGGING_ENABLED = Boolean.valueOf(System
            .getProperty("internalpluginmanager.debug-mode", "true"));

    /**
     * This is the path that is used in the Bukkit configuration (config.yml) to
     * enable/disable the integrated plugin manager. By default, the integrated
     * plugin manager is enabled.
     *
     */
    public static final String PLUGIN_MANAGER_ENABLED_PATH = "settings.plugin_manager_enabled";

    /**
     * This is the path that is used in the Bukkit configuration (config.cml) to
     * enable/disable error report sending.
     *
     * <p>
     * <b> NOTE: Error report sending is NOT possible yet. </b>
     * </p>
     *
     */
    // TODO Implement error report sending.
    public static final String SEND_ERROR_REPORTS_PATH = "send_error_reports";

    private static IpmMain instance;

    private boolean sendErrorReports = false;

    // Only for Testing (has to be commented for a release)
    // private final IpmPlugin ipmTestPlugin = new TestMain();

    @SuppressWarnings("javadoc")
    public IpmMain() {
        ResourceManager.getInstance().registerResource(MainPluginResource.class, this);

        if (IpmMain.instance != null) {
            throw new IllegalStateException("Only bukkit initialize a new main plugin!");
        }

        IpmMain.instance = this;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void onDisable() {
        SimpleIpmPluginManager.getInstance().disableAll();
        SimpleIpmPluginManager.getInstance().unregisterAll();

        // Only for Testing (has to be commented for a release)
        // ipmTestPlugin.onDisable();
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void onEnable() {
        getConfig().addDefault(IpmMain.PLUGIN_MANAGER_ENABLED_PATH, true);
        // TODO Error report sending is not implemented yet.
        // getConfig().addDefault(IpmMain.SEND_ERROR_REPORTS_PATH, false);
        saveConfig();

        sendErrorReports = getConfig().getBoolean(IpmMain.SEND_ERROR_REPORTS_PATH);

        try {
            ResourceManager.initializeResources(ExceptionHandler.class);
        } catch (IllegalArgumentException ex) {
            ExceptionHandler.handle(ex);
            PluginUtil.disable(this);
        } catch (IllegalAccessException ex) {
            ExceptionHandler.handle(ex);
            PluginUtil.disable(this);
        }

        try {
            SimpleIpmPluginManager.getInstance().register(SimpleIpmPluginLoader.getInstance().loadAll());
        } catch (PluginException ex) {
            ExceptionHandler.handle(ex);
        }

        // We do not really to delay the plugin enabling, but we want to enable
        // all plugins after Bukkit has enabled all other plugins. With this,
        // the enable process of the internal plugins will be started one tick
        // after the server has started finally (with all plugins enabled).
        Bukkit.getServer().getScheduler().runTaskLater(this, new Runnable() {
            @Override
            public void run() {
                SimpleIpmPluginManager.getInstance().enableAll();
            }
        }, 1);

        // register basic commands
        CommandRegistrationUtil.registerCommands(new Commands(), this);

        // only register plugin manager commands if the plugin manager is
        // enabled
        if (getConfig().getBoolean(IpmMain.PLUGIN_MANAGER_ENABLED_PATH)) {
            CommandRegistrationUtil.registerCommands(new PluginManagerCommands(), this);
        }

        // register basic tab completer
        TabCompleterRegistrationUtil.registerTabCompleters(new PluginManagerTabCompleter());

        // register testing commands if debug mode enabled
        if (IpmMain.DEBUGGING_ENABLED) {
            final TestCommands testCommands = new TestCommands();
            CommandRegistrationUtil.registerCommands(testCommands, this);
            Bukkit.getServer().getPluginManager().registerEvents(testCommands, this);
        }

        // Only for Testing (has to be commented for a release)
        // ipmTestPlugin.onEnable();
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void onLoad() {
        // Only for Testing (has to be commented for a release)
        // ipmTestPlugin.onLoad();
    }

    /**
     *
     * @return An indicator whether error report sending is enabled/disabled.
     */
    public boolean sendErrorReports() {
        return sendErrorReports;
    }

    /**
     * Returns you an instance from the {@link IpmMain} class.
     *
     * <p>
     * <b> NOTE: This is only for internal usage! Use the
     * {@link ResourceManager} system instead!. </b>
     * </p>
     *
     * @deprecated Use the annotation {@link MainPluginResource} and call
     *             {@link ResourceManager#initializeResources(Object)} instead!
     * @return An instance from the {@link IpmMain} class.
     */
    @Deprecated
    public static IpmMain getInstance() {
        if (IpmMain.instance == null) {
            throw new IllegalStateException(
                    "You can not get an instance of IpmMain until Bukkit has loaded the InternalPluginManager!");
        }

        return IpmMain.instance;
    }
}
