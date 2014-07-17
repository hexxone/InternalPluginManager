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

import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.blockhaus2000.util.Tag;

/**
 * An extended {@link RawCommandContext}.
 *
 */
public class CommandContext extends RawCommandContext {
    private final List<Tag<?>> args;
    private final Map<Character, Tag<?>> flags;

    /**
     * Instances a new {@link CommandContext}.
     *
     * @param commandInfo
     *            The {@link CommandInfo} that will be used for the super-call.
     * @param sender
     *            The {@link CommandSyntax} that has executed the command.
     * @param command
     *            The {@link Command} that has been executed.
     * @param label
     *            The label from the execution.
     * @param rawArgs
     *            The non-parsed arguments of the command.
     * @param args
     *            The parsed arguments.
     * @param flags
     *            The parsed flags.
     */
    public CommandContext(final CommandInfo commandInfo, final CommandSender sender, final Command command, final String label,
            final String[] rawArgs, final List<Tag<?>> args, final Map<Character, Tag<?>> flags) {
        super(commandInfo, sender, command, label, rawArgs);
        this.args = args;
        this.flags = flags;
    }

    /**
     * Instances a new {@link CommandContext}.
     *
     * @param rawContext
     *            The {@link CommandInfo} that will be used for the super-call.
     * @param args
     *            The parsed arguments.
     * @param flags
     *            The parsed flags.
     */
    public CommandContext(final RawCommandContext rawContext, final List<Tag<?>> args, final Map<Character, Tag<?>> flags) {
        this(rawContext, rawContext.getSender(), rawContext.getCommand(), rawContext.getLabel(), rawContext.getRawArgs(), args,
                flags);
    }

    /**
     * @return The arguments of this command.
     */
    public List<Tag<?>> getArgs() {
        return args;
    }

    /**
     *
     * @return The flags of this command.
     */
    public Map<Character, Tag<?>> getFlags() {
        return flags;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getName() + "[args=" + args + ", flags=" + flags + "]";
    }
}
