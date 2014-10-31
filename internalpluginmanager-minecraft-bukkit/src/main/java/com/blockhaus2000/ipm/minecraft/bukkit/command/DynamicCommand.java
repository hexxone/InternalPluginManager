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
package com.blockhaus2000.ipm.minecraft.bukkit.command;

import java.util.Arrays;

import org.bukkit.command.Command;

import com.blockhaus2000.ipm.minecraft.InternalPluginManager;
import com.blockhaus2000.ipm.minecraft.command.BlockCommandSender;
import com.blockhaus2000.ipm.minecraft.command.CommandSender;

/**
 * The dynamic command is an extension of the Bukkit {@link Command} to hack
 * into the Bukkit command system.
 *
 */
public class DynamicCommand extends Command {
    /**
     * Constructor of DynamicCommand. uses all information of the commandAnot
     * for the <code>super</code> call.
     *
     * @param commandAnot
     *            The command annotation that contains all information that is
     *            needed for the <code>super</code> call like aliases, etc.
     */
    public DynamicCommand(final com.blockhaus2000.ipm.technical.command.Command commandAnot) {
        super(commandAnot.aliases()[0], "", "/" + commandAnot.aliases()[0], Arrays.asList(commandAnot.aliases()));
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.command.Command#execute(org.bukkit.command.CommandSender,
     *      java.lang.String, java.lang.String[])
     */
    @Override
    public boolean execute(final org.bukkit.command.CommandSender sender, final String label, final String[] args) {
        final CommandSender commandSender;
        if (sender instanceof org.bukkit.entity.Player) {
            commandSender = InternalPluginManager.getServer().getPlayerManager()
                    .getPlayer(((org.bukkit.entity.Player) sender).getUniqueId());
        } else if (sender instanceof BlockCommandSender) {
            commandSender = new BukkitBlockCommandSender();
        } else {
            commandSender = new BukkitConsoleCommandSender();
        }

        return InternalPluginManager.getServer().getCommandManager().execute(label, commandSender, args);
    }
}
