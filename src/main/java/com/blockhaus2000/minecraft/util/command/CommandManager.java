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
package com.blockhaus2000.minecraft.util.command;

import java.util.Set;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabExecutor;

import com.blockhaus2000.util.command.CommandInfo;

/**
 * This command manager is used to execute the commands tagged with the
 * {@link Command} annotation. This is a {@link TabExecutor}, which extends the
 * {@link CommandExecutor}.
 *
 * @author Blockhaus2000
 * @see org.bukkit.command.TabExecutor
 * @see org.bukkit.command.CommandExecutor
 */
public interface CommandManager extends TabExecutor {
    /**
     * Registers all of the methods in the given {@link Class} that are tagged
     * with the {@link Command} annotation.
     *
     * @param clazz
     *            The {@link Class} that holds of the tagged methods.
     * @param obj
     *            An {@link Object} of the given {@link Class}. Can be
     *            <code>null</code> if all of the methods are static.
     * @return An {@link Set} of {@link CommandInfo}s that are registered.
     */
    public Set<CommandInfo> register(final Class<?> clazz, final Object obj);

    /**
     * Registers all of the methods in the given {@link Class} that are tagged
     * with the {@link Command} annotation. Will call
     * {@link CommandManager#register(Class, Object)} with
     * <code>obj = null</code>.
     *
     * <p>
     * <b> NOTE: Only call this if all of the tagged methods are static. </b>
     * </p>
     *
     * @param clazz
     *            The {@link Class} that holds of the tagged methods.
     * @return An {@link Set} of {@link CommandInfo}s that are registered.
     * @see com.blockhaus2000.minecraft.util.command.CommandManager#register(java.lang.Class,
     *      java.lang.Object)
     */
    public Set<CommandInfo> register(final Class<?> clazz);

    /**
     * Registers all of the methods in the given {@link Class} that are tagged
     * with the {@link Command} annotation. Will call
     * {@link CommandManager#register(Class, Object)} with
     * <code>clazz = {@link Object#getClass()}</code>.
     *
     * @param obj
     *            An {@link Object} of the given {@link Class}. Will be used to
     *            get the {@link Class} of the {@link Object}.
     * @return An {@link Set} of {@link CommandInfo}s that are registered.
     * @see com.blockhaus2000.minecraft.util.command.CommandManager#register(java.lang.Class,
     *      java.lang.Object)
     */
    public Set<CommandInfo> register(final Object obj);
}
