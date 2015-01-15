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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import net.canarymod.Canary;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandManager;

import com.blockhaus2000.ipm.minecraft.InternalPluginManager;
import com.blockhaus2000.ipm.technical.command.util.CommandInfo;
import com.blockhaus2000.ipm.technical.plugin.command.SimplePluginCommandManager;

/**
 * A special extension of the {@link SimplePluginCommandManager} for Canary to
 * hack into the command system of Canary.
 *
 */
public class CanaryCommandManager extends SimplePluginCommandManager {
    /**
     * Constructor of BukkitCommandManager.
     *
     */
    public CanaryCommandManager() {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.SimpleCommandManager#register(java.lang.Class,
     *      java.lang.Object)
     */
    // Cast to "Map<String, CanaryCommand>".
    @SuppressWarnings("unchecked")
    @Override
    public <T> Set<CommandInfo> register(final Class<T> clazz, final T obj) {
        final Set<CommandInfo> registered = super.register(clazz, obj);

        final List<DynamicCommand> commands = new ArrayList<DynamicCommand>();
        for (final CommandInfo commandInfo : registered) {
            for (final String alias : commandInfo.getCommandAnot().aliases()) {
                commands.add(new DynamicCommand(alias));
            }
        }

        Field commandsField = null;
        try {
            commandsField = CommandManager.class.getDeclaredField("commands");
        } catch (final SecurityException ex) {
            InternalPluginManager.getServer().getLogger()
            .log(Level.SEVERE, "An error occurred whilest retrieving commands from Canary command manager.", ex);
        } catch (final NoSuchFieldException ex) {
            InternalPluginManager.getServer().getLogger()
            .log(Level.SEVERE, "An error occurred whilest retrieving commands from Canary command manager.", ex);
        }

        final boolean commandsFieldAccessible = commandsField.isAccessible();
        commandsField.setAccessible(true);

        Map<String, CanaryCommand> canaryCommands = null;
        try {
            canaryCommands = (Map<String, CanaryCommand>) commandsField.get(Canary.commands());
        } catch (final IllegalArgumentException ex) {
            InternalPluginManager.getServer().getLogger()
            .log(Level.SEVERE, "An error occurred whilest retrieving commands from Canary command manager.", ex);
        } catch (final IllegalAccessException ex) {
            InternalPluginManager.getServer().getLogger()
            .log(Level.SEVERE, "An error occurred whilest retrieving commands from Canary command manager.", ex);
        }
        for (final DynamicCommand dynamicCommand : commands) {
            canaryCommands.put(dynamicCommand.getAlias(), dynamicCommand);
        }

        commandsField.setAccessible(commandsFieldAccessible);

        return registered;
    }
}
