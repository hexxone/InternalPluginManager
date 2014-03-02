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
package com.blockhaus2000.util.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * 
 * @author Blockhaus2000
 */
public class RawCommandContext extends CommandInfo {
    private final CommandSender sender;
    private final Command command;
    private final String label;
    private final String[] rawArgs;

    public RawCommandContext(final CommandInfo commandInfo, final CommandSender sender, final Command command,
            final String label, final String[] rawArgs) {
        super(commandInfo.getCommandAnot(), commandInfo.getClazz(), commandInfo.getObject(), commandInfo.getMethod(), commandInfo
                .getSyntax());
        this.sender = sender;
        this.command = command;
        this.label = label;
        this.rawArgs = rawArgs;
    }

    // Getter
    public CommandSender getSender() {
        return sender;
    }

    public Command getCommand() {
        return command;
    }

    public String getLabel() {
        return label;
    }

    public String[] getRawArgs() {
        return rawArgs;
    }
}
