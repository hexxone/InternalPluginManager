/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014 Fabian Damken
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.blockhaus2000.ipm.technical.plugin;

import java.beans.PropertyChangeListener;
import java.util.logging.Logger;

import com.blockhaus2000.ipm.technical.command.CommandManager;

/**
 * The plugin interface. To implement a plugin, extend the {@link SimplePlugin}.
 * A plugin can be loaded by the {@link PluginManager} and extends the
 * functionality of can implement completly new features.
 *
 */
public interface Plugin extends PropertyChangeListener {
    /**
     * Registers all command in the given class by delegating to
     * {@link CommandManager#register(Class, Object)}.
     *
     * <p>
     * <b> NOTE: Use this method instead of calling
     * {@link CommandManager#register(Class, Object)} directly, because this
     * method enables also the automatic handling of unregistering commands. So,
     * if you use this method, the commands will be unregistered automaticly on
     * disable. Otherwise, you have to handle it manually. </b>
     * </p>
     *
     * @param clazz
     *            Is passed into {@link CommandManager#register(Class, Object)}
     *            and is used for automatic unregister handling.
     * @param obj
     *            Is passed into {@link CommandManager#register(Class, Object)}.
     */
    public <T> void registerCommands(final Class<T> clazz, final T obj);

    /**
     * Delegates to {@link Plugin#registerCommands(Class, Object)} with
     * <code>clazz = obj.getClass()</code> and <code>obj = obj</code>.
     *
     * @param obj
     *            Is passed into {@link Plugin#registerCommands(Class, Object)}.
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#registerCommands(java.lang.Class,
     *      java.lang.Object)
     */
    public <T> void registerCommands(final T obj);

    /**
     * Delegates to {@link Plugin#registerCommands(Class, Object)} with
     * <code>clazz = clazz</code> and <code>obj = null</code>.
     *
     * @param clazz
     *            Is passed into {@link Plugin#registerCommands(Class, Object)}.
     * @see com.blockhaus2000.ipm.technical.plugin.Plugin#registerCommands(java.lang.Class,
     *      java.lang.Object)
     */
    public <T> void registerCommands(final Class<T> clazz);

    /**
     * If the enabled flag is set to <code>false</code>, this will be callen.
     *
     */
    public void onDisable();

    /**
     * If the enabled flag is set to <code>true</code>, this will be callen.
     *
     */
    public void onEnable();

    /**
     * This method will be callen after loading the plugin, but before enabling
     * it.
     *
     * <p>
     * <b> NOTE: The loading does NOT take care of the dependencies. This method
     * is called in the loading order. </b>
     * </p>
     *
     */
    public void onLoad();

    /**
     *
     * @return The {@link Logger} for this plugin.
     */
    public Logger getLogger();

    /**
     *
     * @return The {@link PluginMeta}, that contains some information about this
     *         plugin.
     */
    public PluginMeta getPluginMeta();

    /**
     *
     * @return The name of the plugin.
     */
    public String getName();

    /**
     *
     * @return <code>true</code> if the plugin is enabled, <code>false</code>
     *         otherwise.
     */
    public boolean isEnabled();

    /**
     * Sets the enabled-flag. Will automaticly call {@link Plugin#onEnable()}
     * and {@link Plugin#onDisable()} when the enable flag changes its state.
     *
     * @param enabled
     *            The new enabled flag.
     */
    public void setEnabled(final boolean enabled);
}
