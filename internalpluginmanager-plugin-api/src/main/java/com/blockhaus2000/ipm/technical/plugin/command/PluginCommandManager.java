/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014-2015 Fabian Damken
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
 */
package com.blockhaus2000.ipm.technical.plugin.command;

import java.util.Set;

import com.blockhaus2000.ipm.technical.command.Command;
import com.blockhaus2000.ipm.technical.command.CommandManager;
import com.blockhaus2000.ipm.technical.command.util.CommandInfo;
import com.blockhaus2000.ipm.technical.plugin.Plugin;

/**
 * The {@link PluginCommandManager} is an extension of the
 * {@link CommandManager} that also provides auto-unregistering for commands
 * associated with a {@link Plugin}.
 *
 */
public interface PluginCommandManager extends CommandManager {
    /**
     * Registers all command methods that are included in the given class,
     * marked with the {@link Command} annotation.
     *
     * @param <T>
     *            The type of the object to register.
     * @param plugin
     *            The {@link Plugin} that should be associated with the
     *            registered commands. Is used for auto-unregistering.
     * @param clazz
     *            The {@link Class} containing all commands.
     * @param obj
     *            An {@link Object} of the given {@link Class}, if one of the
     *            methods to register is non-static.
     * @return A set of all {@link CommandInfo} that where registered.
     */
    <T> Set<CommandInfo> register(final Plugin plugin, final Class<T> clazz, final T obj);

    /**
     * Delegates to {@link PluginCommandManager#register(Plugin, Class, Object)}
     * with <code>clazz = obj.getClass()</code>.
     *
     * @param <T>
     *            The type of the object to register.
     * @param plugin
     *            The {@link Plugin} that should be associated with the
     *            registered commands. Is used for auto-unregistering.
     * @param obj
     *            Will be passed to
     *            {@link PluginCommandManager#register(Plugin, Class, Object)}.
     * @return See {@link PluginCommandManager#register(Plugin, Class, Object)}.
     * @see com.blockhaus2000.ipm.technical.plugin.command.PluginCommandManager#register(Plugin,
     *      java.lang.Class, java.lang.Object)
     */
    <T> Set<CommandInfo> register(final Plugin plugin, final T obj);

    /**
     * Delegates to {@link PluginCommandManager#register(Plugin, Class, Object)}
     * with <code>obj = null</code>.
     *
     * <p>
     * <b> NOTE: Only use this of all methods to register are static. </b>
     * </p>
     *
     * @param <T>
     *            The type of the object to register.
     * @param plugin
     *            The {@link Plugin} that should be associated with the
     *            registered commands. Is used for auto-unregistering.
     * @param clazz
     *            Will be passed to
     *            {@link PluginCommandManager#register(Plugin, Class, Object)}.
     * @return See {@link PluginCommandManager#register(Plugin, Class, Object)}.
     * @see com.blockhaus2000.ipm.technical.plugin.command.PluginCommandManager#
     *      register(Plugin, java.lang.Class, java.lang.Object)
     */
    <T> Set<CommandInfo> register(final Plugin plugin, final Class<T> clazz);

    /**
     * {@inheritDoc}
     *
     * @deprecated Use
     *             {@link PluginCommandManager#register(Plugin, Class, Object)}
     *             instead.
     * @see com.blockhaus2000.ipm.technical.command.CommandManager#register(java.lang.Class,
     *      java.lang.Object)
     */
    @Deprecated
    @Override
    <T> Set<CommandInfo> register(Class<T> clazz, T obj);

    /**
     * {@inheritDoc}
     *
     * @deprecated Use
     *             {@link PluginCommandManager#register(Plugin, Class, Object)}
     *             instead.
     * @see com.blockhaus2000.ipm.technical.plugin.command.PluginCommandManager#
     *      register(com.blockhaus2000.ipm.technical.plugin.Plugin,
     *      java.lang.Object)
     */
    @Deprecated
    @Override
    <T> Set<CommandInfo> register(T obj);

    /**
     * {@inheritDoc}
     *
     * @deprecated Use
     *             {@link PluginCommandManager#register(Plugin, Class, Object)}
     *             instead.
     * @see com.blockhaus2000.ipm.technical.plugin.command.PluginCommandManager#
     *      register(com.blockhaus2000.ipm.technical.plugin.Plugin,
     *      java.lang.Class)
     */
    @Deprecated
    @Override
    <T> Set<CommandInfo> register(Class<T> clazz);
}
