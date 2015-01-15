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

import java.lang.reflect.Method;
import java.util.logging.Level;

import net.canarymod.Canary;
import net.canarymod.api.CommandBlockLogic;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.Command;

import com.blockhaus2000.ipm.minecraft.InternalPluginManager;
import com.blockhaus2000.ipm.minecraft.command.CommandSender;

/**
 * The dynamic command is an extension of the Canary {@link CanaryCommand} to
 * hack into the Canary command system.
 *
 */
public class DynamicCommand extends CanaryCommand {
    /**
     * The alias/command of this command.
     *
     */
    private final String alias;

    /**
     * Constructs a new {@link DynamicCommand} for the given alias/command.
     *
     * @param alias
     *            The alias of this command ("the command").
     */
    public DynamicCommand(final String alias) {
        super(DummyCommandAnnotation.get(), Canary.getServer(), null);

        this.alias = alias;
    }

    /**
     * {@inheritDoc}
     *
     * @see net.canarymod.commandsys.CanaryCommand#execute(net.canarymod.chat.MessageReceiver,
     *      java.lang.String[])
     */
    @Override
    protected void execute(final MessageReceiver sender, final String[] args) {
        final CommandSender commandSender;
        if (sender instanceof Player) {
            commandSender = InternalPluginManager.getServer().getPlayerManager().getPlayer(((Player) sender).getUUID());
        } else if (sender instanceof CommandBlockLogic) {
            commandSender = new CanaryBlockCommandSender();
        } else {
            commandSender = new CanaryConsoleCommandSender();
        }

        InternalPluginManager.getServer().getCommandManager().execute(this.alias, commandSender, args);
    }

    /**
     *
     * @return {@link DynamicCommand#alias}.
     */
    public String getAlias() {
        return this.alias;
    }

    /**
     * The {@link DummyCommandAnnotation} is used to get a {@link Command}
     * annotation to hack into the Canary command system.
     *
     */
    private static final class DummyCommandAnnotation {
        /**
         * The object to create locks on.
         *
         */
        private static final Object LOCK = new Object();

        /**
         * The {@link Command} annotation that is cached to speed up.
         *
         */
        private static volatile Command commandAnot;

        /**
         * Constructor of DummyCommandAnnotation.
         *
         */
        private DummyCommandAnnotation() {
            // Nothing to do.
        }

        /**
         *
         * @return The dummy command annotation.
         */
        // The annotation is returned from the method itself.
        @Command(aliases = { "" },
                 description = "",
                 permissions = { "" },
                 toolTip = "")
        private static Command get() {
            if (DummyCommandAnnotation.commandAnot == null) {
                synchronized (DummyCommandAnnotation.LOCK) {
                    if (DummyCommandAnnotation.commandAnot == null) {
                        Method method = null;
                        try {
                            method = DummyCommandAnnotation.class.getDeclaredMethod("get");
                        } catch (final SecurityException ex) {
                            InternalPluginManager.getServer().getLogger()
                            .log(Level.SEVERE, "An error occurred whilest getting the dummy command annotation!", ex);
                        } catch (final NoSuchMethodException ex) {
                            InternalPluginManager.getServer().getLogger()
                            .log(Level.SEVERE, "An error occurred whilest getting the dummy command annotation!", ex);
                        }
                        method.setAccessible(true);
                        DummyCommandAnnotation.commandAnot = method.getAnnotation(net.canarymod.commandsys.Command.class);
                    }
                }
            }
            return DummyCommandAnnotation.commandAnot;
        }
    }
}
