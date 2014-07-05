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

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;

/**
 * This will represent a {@link Command} that can be dynamicly registered in the
 * {@link DynamicPluginCommandManager}.
 *
 */
public interface DynamicPluginCommand extends PluginIdentifiableCommand {
    /**
     * This will be callen if the command has be executed.
     *
     * @param sender
     *            The {@link CommandSender} that has executed the command.
     * @param label
     *            The label of the executed command (will be the alias or
     *            something like that).
     * @param args
     *            The arguments of the executed command (will be a
     *            {@link String}[]).
     * @return Has to return <code>true</code> if command has been handled,
     *         otherwise <code>false</code>.
     */
    public boolean execute(final CommandSender sender, final String label, final String[] args);

    /**
     *
     * @return The {@link CommandExecutor} for this command.
     */
    public CommandExecutor getExecutor();
}
