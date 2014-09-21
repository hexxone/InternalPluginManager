/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014 Fabian Damken
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.blockhaus2000.ipm.minecraft.canary.command;

import com.blockhaus2000.ipm.minecraft.command.CommandSenderType;
import com.blockhaus2000.ipm.minecraft.command.ConsoleCommandSender;

/**
 * The implementation of {@link ConsoleCommandSender} for Canary.
 *
 */
public class CanaryConsoleCommandSender implements ConsoleCommandSender {
    /**
     * An instance of the {@link CanaryConsoleCommandSender} to reduce heap
     * memory usage.
     *
     */
    private static final ConsoleCommandSender INSTANCE = new CanaryConsoleCommandSender();

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.command.CommandSender#getType()
     */
    @Override
    public CommandSenderType getType() {
        return CommandSenderType.CONSOLE;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.CommandSender#hasPermission(java.lang.String)
     */
    @Override
    public boolean hasPermission(final String permission) {
        return true;
    }

    /**
     * <p>
     * <b> NOTE: This is only a method to get an instance! This is no singleton!
     * </b>
     * </p>
     *
     * @return An instance of the {@link CanaryConsoleCommandSender}.
     */
    public static ConsoleCommandSender getInstance() {
        return CanaryConsoleCommandSender.INSTANCE;
    }
}
