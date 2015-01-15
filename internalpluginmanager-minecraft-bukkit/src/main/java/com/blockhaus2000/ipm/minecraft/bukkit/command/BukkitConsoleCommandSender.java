/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014-2015 Fabian Damken
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
 */
package com.blockhaus2000.ipm.minecraft.bukkit.command;

import com.blockhaus2000.ipm.minecraft.InternalPluginManager;
import com.blockhaus2000.ipm.minecraft.command.CommandSenderType;
import com.blockhaus2000.ipm.minecraft.command.ConsoleCommandSender;

/**
 * The Bukkit implementation of {@link ConsoleCommandSender}.
 *
 */
public class BukkitConsoleCommandSender implements ConsoleCommandSender {
    /**
     * Constructor of BukkitConsoleCommandSender.
     *
     */
    public BukkitConsoleCommandSender() {
        // Nothing to do.
    }

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
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.CommandSender#sendMessage(java.lang.String)
     */
    @Override
    public void sendMessage(final String message) {
        InternalPluginManager.getServer().getMessageManager().sendMessage(this, message);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.command.CommandSender#sendRawMessage(java.lang.String)
     */
    @Override
    public void sendRawMessage(final String message) {
        System.out.println(message);
    }
}
