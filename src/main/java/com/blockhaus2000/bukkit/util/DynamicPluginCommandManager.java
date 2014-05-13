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
package com.blockhaus2000.bukkit.util;

import java.util.Collection;

import org.bukkit.command.CommandMap;

import com.blockhaus2000.util.command.CommandInfo;

/**
 * This command manager enables dynamic command registration in Bukkit, so you
 * do not have to describe all of your commands in the <code>plugin.yml</code>.
 *
 * @author Blockhaus2000
 */
public interface DynamicPluginCommandManager {
    /**
     * Registers the given command (represented by {@link CommandInfo}) to the
     * Bukkit {@link CommandMap}.
     *
     * @param registered
     *            The command (represented by {@link CommandInfo}) to register.
     */
    public void register(CommandInfo registered);

    /**
     * Registers all of the given commands (represented by {@link CommandInfo}
     * s). Will call {@link DynamicPluginCommandManager#register(CommandInfo)}.
     *
     * @param registered
     *            The {@link Collection} with the {@link CommandInfo}s.
     * @see com.blockhaus2000.bukkit.util.DynamicPluginCommandManager#register(com.blockhaus2000.util.command.CommandInfo)
     */
    public void register(Collection<CommandInfo> registered);
}
