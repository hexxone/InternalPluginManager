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
package com.blockhaus2000.util.tabcompl;

import java.lang.reflect.Method;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.blockhaus2000.minecraft.util.tabcompl.TabCompleter;

/**
 * The {@link TabCompleterContext} has to be the only argument in a method that
 * is tagged with the annotation {@link TabCompleter}.
 *
 * @see TabCompleterInfo
 */
public class TabCompleterContext extends TabCompleterInfo {
    private final CommandSender sender;
    private final Command command;
    private final String label;
    private final String[] args;

    /**
     * Creates a new {@link TabCompleterContext}.
     *
     * @param tabCompleter
     *            Will be use in super call.
     * @param clazz
     *            Will be use in super call.
     * @param obj
     *            Will be use in super call.
     * @param method
     *            Will be use in super call.
     * @param sender
     *            The {@link CommandSender} that has executed the command to tab
     *            complete.
     * @param command
     *            The {@link Command} that has been executed.
     * @param label
     *            The command label that has been executed.
     * @param args
     *            The args that has been passed to the command until tab
     *            complete.
     */
    public TabCompleterContext(final TabCompleter tabCompleter, final Class<?> clazz, final Object obj, final Method method,
            final CommandSender sender, final Command command, final String label, final String[] args) {
        super(tabCompleter, clazz, obj, method);
        this.sender = sender;
        this.command = command;
        this.label = label;
        this.args = args;
    }

    /**
     * Calls
     * <code>this(TabCompleter, Class<?>, Onbject, Method, CommandSender, Command, String, String[])</code>
     * .
     *
     * @param tabCompleterInfo
     *            Will be used to get the required information for the
     *            super-call.
     * @param sender
     *            Will be overgiven.
     * @param command
     *            Will be overgiven.
     * @param label
     *            Will be overgiven.
     * @param args
     *            Will be overgiven.
     */
    public TabCompleterContext(final TabCompleterInfo tabCompleterInfo, final CommandSender sender, final Command command,
            final String label, final String[] args) {
        this(tabCompleterInfo.getTabCompleter(), tabCompleterInfo.getClazz(), tabCompleterInfo.getObj(), tabCompleterInfo
                .getMethod(), sender, command, label, args);
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
     * @return The {@link Command} that has been executed.
     */
    public Command getCommand() {
        return command;
    }

    /**
     *
     * @return The command label that has been executed.
     */
    public String getLabel() {
        return label;
    }

    /**
     *
     * @return The arguments that has been passed to the command.
     */
    public String[] getArgs() {
        return args;
    }
}
