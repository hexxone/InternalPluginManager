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

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * An extended {@link CommandInfo}.
 *
 * @author Blockhaus2000
 */
public class RawCommandContext extends CommandInfo {
    private final CommandSender sender;
    private final Command command;
    private final String label;
    private final String[] rawArgs;

    /**
     * Instances a new {@link RawCommandContext}.
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
     */
    public RawCommandContext(final CommandInfo commandInfo, final CommandSender sender, final Command command,
            final String label, final String[] rawArgs) {
        super(commandInfo.getCommandAnot(), commandInfo.getClazz(), commandInfo.getObject(), commandInfo.getMethod(), commandInfo
                .getSyntax());
        this.sender = sender;
        this.command = command;
        this.label = label;
        this.rawArgs = rawArgs;
    }

    /**
     *
     * @return The {@link CommandSender} that has executed the command.
     */
    public CommandSender getSender() {
        return sender;
    }

    /**
     *
     * @return The {@link Command} that represents the executed Bukkit command.
     */
    public Command getCommand() {
        return command;
    }

    /**
     *
     * @return The label of the executed command.
     */
    public String getLabel() {
        return label;
    }

    /**
     *
     * @return The raw arguments of the executed command.
     */
    public String[] getRawArgs() {
        return rawArgs;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getName() + "[sender=" + sender + ", command=" + command + ", label=" + label + ", rawArgs="
                + Arrays.toString(rawArgs) + "]";
    }
}
