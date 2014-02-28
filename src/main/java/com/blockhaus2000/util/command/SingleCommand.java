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
 *  package com.blockhaus2000.bukkit.util;
 */
package com.blockhaus2000.util.command;

import com.blockhaus2000.bukkit.util.DynamicPluginCommandManager;
import com.blockhaus2000.minecraft.util.command.Command;
import com.blockhaus2000.minecraft.util.command.CommandManager;
import com.blockhaus2000.util.CommandRegistrationUtil;

/**
 * This interface represents a help interface for implementing a usage-proof
 * command method. Has to be used with the command system and the
 * {@link Command} annotation.
 * 
 * @author Blockhaus2000
 */
public interface SingleCommand {
    /**
     * If you tag this with the {@link Command} annotation and register it to
     * the {@link DynamicPluginCommandManager} and {@link CommandManager}
     * (easyli with the {@link CommandRegistrationUtil}).
     * 
     * @param context
     *            The {@link CommandContext} of the executed command.
     */
    public void onCommand(final CommandContext context);
}
