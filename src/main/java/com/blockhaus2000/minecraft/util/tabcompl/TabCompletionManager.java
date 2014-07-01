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
package com.blockhaus2000.minecraft.util.tabcompl;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.blockhaus2000.minecraft.util.command.CommandManager;
import com.blockhaus2000.util.command.CommandInfo;

/**
 * The {@link TabCompletionManager} enables tab completion for commands that are
 * registered with the {@link CommandManager}. Every method tagged with the
 * {@link TabCompleter} annotation can be registered with the
 * {@link TabCompletionManager}.
 *
 * <p>
 * <b> NOTE: This does not effect the automatic tab completion of the second
 * level commands. </b>
 * </p>
 *
 */
public interface TabCompletionManager {
    /**
     * Registers all of the methods in the given {@link Class} that are tagged
     * with the {@link TabCompleter} annotation.
     *
     * @param clazz
     *            The {@link Class} that contains all methods to register.
     * @param obj
     *            An {@link Object} of the given {@link Class}. Can be
     *            <code>null</code> if the methods to register are
     *            <code>static</code>.
     */
    public void register(final Class<?> clazz, final Object obj);

    /**
     * Registers all of the methods in the given {@link Class} that are tagged
     * with the {@link TabCompleter} annotation. Will call
     * {@link TabCompletionManager#register(Class, Object)} with
     * <code>obj = null</code>.
     *
     * <p>
     * <b> NOTE: Only call this if all methods of the given {@link Class} are
     * <code>static</code>. </b>
     * </p>
     *
     * @param clazz
     *            The {@link Class} that contains all methods to register.
     * @see com.blockhaus2000.minecraft.util.tabcompl.TabCompletionManager#register(java.lang.Class,
     *      java.lang.Object)
     */
    public void register(final Class<?> clazz);

    /**
     * Registers all of the methods in the given {@link Class} that are tagged
     * with the {@link TabCompleter} annotation. Will call
     * {@link TabCompletionManager#register(Class, Object)} with
     * <code>clazz = {@link Object#getClass()}</code>.
     *
     * @param obj
     *            An {@link Object} of the given {@link Class}. Will be used to
     *            get the {@link Class} of the {@link Object}.
     * @see com.blockhaus2000.minecraft.util.tabcompl.TabCompletionManager#register(java.lang.Class,
     *      java.lang.Object)
     */
    public void register(final Object obj);

    /**
     * Performs a tab complete.
     *
     * <p>
     * <b> NOTE: The call has to be checked against the {@link CommandManager}.
     * </b>
     * </p>
     *
     * @param info
     *            The command info that contains the required command
     *            information.
     * @param sender
     *            The {@link CommandSender} that will execute the command.
     * @param command
     *            The {@link Command} that will be executed.
     * @param label
     *            The command label that triggers the command execution.
     * @param args
     *            The actual arguments that has been typed.
     * @return The whole list of possible tab completions.
     */
    public List<String> performTabComplete(final CommandInfo info, CommandSender sender, Command command, final String label,
            final String[] args);
}
