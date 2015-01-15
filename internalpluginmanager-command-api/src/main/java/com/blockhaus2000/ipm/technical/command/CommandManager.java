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
package com.blockhaus2000.ipm.technical.command;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.blockhaus2000.ipm.technical.command.util.CommandInfo;

/**
 * The {@link CommandManager} is the core of the command system, managing all
 * commands and check permissions, argument count, etc.
 *
 */
public interface CommandManager {
    /**
     * Registers all command methods that are included in the given class,
     * marked with the {@link Command} annotation.
     *
     * @param <T>
     *            The type of the object to register.
     * @param clazz
     *            The {@link Class} containing all commands.
     * @param obj
     *            An {@link Object} of the given {@link Class}, if one of the
     *            methods to register is non-static.
     * @return A set of all {@link CommandInfo} that where registered.
     */
    <T> Set<CommandInfo> register(final Class<T> clazz, final T obj);

    /**
     * Delegates to {@link CommandManager#register(Class, Object)} with
     * <code>clazz = obj.getClass()</code>.
     *
     * @param <T>
     *            The type of the object to register.
     * @param obj
     *            Will be passed to
     *            {@link CommandManager#register(Class, Object)}.
     * @return See {@link CommandManager#register(Class, Object)}.
     * @see com.blockhaus2000.ipm.technical.command.CommandManager#register(java.lang.Class,
     *      java.lang.Object)
     */
    <T> Set<CommandInfo> register(final T obj);

    /**
     * Delegates to {@link CommandManager#register(Class, Object)} with
     * <code>obj = null</code>.
     *
     * <p>
     * <b> NOTE: Only use this of all methods to register are static. </b>
     * </p>
     *
     * @param <T>
     *            The type of the object to register.
     * @param clazz
     *            Will be passed to
     *            {@link CommandManager#register(Class, Object)}.
     * @return See {@link CommandManager#register(Class, Object)}.
     * @see com.blockhaus2000.ipm.technical.command.CommandManager#register(java.lang.Class,
     *      java.lang.Object)
     */
    <T> Set<CommandInfo> register(final Class<T> clazz);

    /**
     * Unregisters all commands specified in the given {@link Class}.
     *
     * @param clazz
     *            The {@link Class} that contains all commands to unregister.
     * @return A {@link Set} of {@link CommandInfo}s that are unregistered.
     */
    Set<CommandInfo> unregister(final Class<?> clazz);

    /**
     * Delegates to {@link CommandManager#unregister(Class)} with
     * <code>clazz = obj.getClass()</code>.
     *
     * @param obj
     *            Will be passed to {@link CommandManager#unregister(Class)}.
     * @return See {@link CommandManager#unregister(Class)}.
     * @see com.blockhaus2000.ipm.technical.command.CommandManager#unregister(java.lang.Class)
     */
    Set<CommandInfo> unregister(final Object obj);

    /**
     * Executes the given alias (label).
     *
     * @param label
     *            The command alias to execute.
     * @param sender
     *            The {@link CommandSender} that has executed the command.
     * @param rawArgs
     *            The raw arguments that will be parsed (used for flags, syntax,
     *            etc.).
     * @return <code>true</code> if the command was executed successfully,
     *         <code>false</code> otherwise.
     */
    boolean execute(final String label, final CommandSender sender, final String... rawArgs);

    /**
     * <ul>
     * <li>Key (java.lang.String): The command alias.</li>
     * <li>Value (java.util.Set): The {@link CommandInfo}s that are associated
     * with the command alias.</li>
     * </ul>
     *
     * @return All registered commands.
     */
    Map<String, List<CommandInfo>> getCommands();
}
