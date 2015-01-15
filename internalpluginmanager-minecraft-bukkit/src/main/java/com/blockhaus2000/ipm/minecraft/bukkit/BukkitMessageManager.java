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
package com.blockhaus2000.ipm.minecraft.bukkit;

import com.blockhaus2000.ipm.minecraft.InternalPluginManager;
import com.blockhaus2000.ipm.minecraft.MessageManager;
import com.blockhaus2000.ipm.minecraft.command.CommandSender;
import com.blockhaus2000.ipm.minecraft.entity.Player;
import com.blockhaus2000.ipm.technical.format.SimpleStringFormatter;
import com.blockhaus2000.ipm.technical.format.StringFormatter;

/**
 * The Bukkit implementation of {@link MessageManager}.
 *
 */
public class BukkitMessageManager implements MessageManager {
    /**
     * The formatter to use.
     *
     */
    private final StringFormatter formatter = new SimpleStringFormatter();

    /**
     * Constructor of BukkitMessageManager.
     *
     */
    public BukkitMessageManager() {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.MessageManager#broadcast(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public void broadcast(final String message, final String permission) {
        for (final Player player : InternalPluginManager.getServer().getPlayerManager().getPlayers()) {
            if (player.hasPermission(permission)) {
                player.sendMessage(message);
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.MessageManager#broadcast(java.lang.String)
     */
    @Override
    public void broadcast(final String message) {
        this.broadcast(message, null);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.MessageManager#sendMessage(com.blockhaus2000.ipm.minecraft.command.CommandSender,
     *      java.lang.String)
     */
    @Override
    public void sendMessage(final CommandSender receiver, final String message) {
        receiver.sendRawMessage(this.format(message));
    }

    /**
     * Formats the given string with the formatter
     * {@link BukkitMessageManager#formatter} and the chat mappings of all
     * values of
     * {@link com.blockhaus2000.ipm.minecraft.MessageManager.ChatColor}.
     *
     * @param message
     *            The message to format.
     * @return The processed string.
     */
    private String format(final String message) {
        return message == null ? "" : this.formatter.format(message, MessageManager.ChatColor.values());
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.MessageManager#getFormatter()
     */
    @Override
    public StringFormatter getFormatter() {
        return this.formatter;
    }
}
