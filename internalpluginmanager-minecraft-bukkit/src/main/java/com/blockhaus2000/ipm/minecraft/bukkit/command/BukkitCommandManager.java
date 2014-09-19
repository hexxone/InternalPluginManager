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

import java.lang.reflect.Field;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;

import com.blockhaus2000.ipm.minecraft.InternalPluginManager;
import com.blockhaus2000.ipm.technical.command.util.CommandInfo;
import com.blockhaus2000.ipm.technical.command.util.exception.CommandException;
import com.blockhaus2000.ipm.technical.plugin.command.SimplePluginCommandManager;

/**
 * A special extension for the {@link SimplePluginCommandManager} for Bukkit to
 * hack into the command system of Bukkit.
 *
 */
// TODO: Add injection into Bukkit.
public class BukkitCommandManager extends SimplePluginCommandManager {
    /**
     * The logger.
     *
     */
    private static final Logger LOGGER = InternalPluginManager.getServer().getLogger();

    /**
     * Constructor of BukkitCommandManager.
     *
     */
    public BukkitCommandManager() {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.SimpleCommandManager#register(java.lang.Class,
     *      java.lang.Object)
     */
    @Override
    public <T> Set<CommandInfo> register(final Class<T> clazz, final T obj) {
        final Set<CommandInfo> registered = super.register(clazz, obj);

        final CommandMap commandMap = this.getCommandMap();

        for (final CommandInfo commandInfo : registered) {
            commandMap.register(InternalPluginManager.class.getName().toLowerCase(), new DynamicCommand(commandInfo));
        }

        return registered;
    }

    private CommandMap getCommandMap() {
        CommandMap commandMap = null;
        try {
            Field commandMapField;
            commandMapField = SimplePluginManager.class.getDeclaredField("commandMap");

            final boolean commandMapFieldAccessible = commandMapField.isAccessible();
            commandMapField.setAccessible(true);

            commandMap = (CommandMap) commandMapField.get(Bukkit.getServer().getPluginManager());

            commandMapField.setAccessible(commandMapFieldAccessible);
        } catch (final SecurityException cause) {
            throw new CommandException("An error occurred whilest retrieving command map from Bukkit!", cause);
        } catch (final NoSuchFieldException cause) {
            throw new CommandException("An error occurred whilest retrieving command map from Bukkit!", cause);
        } catch (final IllegalArgumentException cause) {
            throw new CommandException("An error occurred whilest retrieving command map from Bukkit!", cause);
        } catch (final IllegalAccessException cause) {
            throw new CommandException("An error occurred whilest retrieving command map from Bukkit!", cause);
        } catch (final ClassCastException cause) {
            throw new CommandException("An error occurred whilest retrieving command map from Bukkit!", cause);
        }

        return commandMap;
    }
}
