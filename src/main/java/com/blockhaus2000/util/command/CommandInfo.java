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

import java.lang.reflect.Method;

import com.blockhaus2000.minecraft.util.command.Command;
import com.blockhaus2000.minecraft.util.command.CommandPriority;

/**
 * This class contains the basic options of a command. Implements
 * {@link Comparable} to sort commands by the {@link CommandPriority}.
 *
 * @author Blockhaus2000
 */
public class CommandInfo implements Comparable<CommandInfo> {
    private final Command commandAnot;

    private final Class<?> clazz;
    private final Object object;
    private final Method method;
    private final CommandSyntax syntax;

    /**
     * Instances a new {@link CommandInfo} with the given informations.
     *
     * @param command
     *            The {@link Command} annotation.
     * @param clazz
     *            The {@link Class} that contains the method that executes the
     *            command.
     * @param object
     *            An {@link Object} of the given command-executing class.
     * @param method
     *            The {@link Method} that executes the command.
     * @param syntax
     *            The {@link CommandSyntax} that is the syntax of the command.
     */
    public CommandInfo(final Command command, final Class<?> clazz, final Object object, final Method method,
            final CommandSyntax syntax) {
        commandAnot = command;
        this.clazz = clazz;
        this.object = object;
        this.method = method;
        this.syntax = syntax;
    }

    /**
     *
     * @return The {@link Command} annotation for this command.
     */
    public Command getCommandAnot() {
        return commandAnot;
    }

    /**
     *
     * @return The {@link Class} where the invoked method is located.
     */
    public Class<?> getClazz() {
        return clazz;
    }

    /**
     *
     * @return The {@link Object} that is used for method invoking.
     */
    public Object getObject() {
        return object;
    }

    /**
     *
     * @return The {@link Method} that has been invoked.
     */
    public Method getMethod() {
        return method;
    }

    /**
     *
     * @return The {@link CommandSyntax} of this command.
     */
    public CommandSyntax getSyntax() {
        return syntax;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final CommandInfo that) {
        final int levelThis = getCommandAnot().priority().getLevel();
        final int levelThat = that.getCommandAnot().priority().getLevel();

        if (levelThis == levelThat) {
            return 0;
        }

        return levelThis < levelThat ? -1 : 1;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getName() + "[commandAnot=" + commandAnot + ", clazz=" + clazz + ", object=" + object + ", method="
                + method + ", syntax=" + syntax + "]";
    }
}
