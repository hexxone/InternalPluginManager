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
package com.blockhaus2000.ipm.minecraft.command;

/**
 * The Minecraft command sender is an extension of the ddefault
 * {@link CommandSender} to provide an easy access to the command sender type.
 *
 */
public interface CommandSender extends com.blockhaus2000.ipm.technical.command.CommandSender {
    /**
     *
     * @return The type of the command sender (command block, console, etc.).
     */
    CommandSenderType getType();

    /**
     * Sends the given message to this {@link CommandSender} <b>wihout</b>
     * formatting it.
     *
     * @param message
     *            The message to sent.
     */
    void sendRawMessage(final String message);
}
