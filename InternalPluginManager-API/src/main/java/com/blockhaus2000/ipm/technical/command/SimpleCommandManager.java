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
package com.blockhaus2000.ipm.technical.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.blockhaus2000.ipm.minecraft.api.command.CommandSender;
import com.blockhaus2000.ipm.technical.command.event.CommandEvent;
import com.blockhaus2000.ipm.technical.command.event.CommandEventData;
import com.blockhaus2000.ipm.technical.command.event.CommandEventData.CommandEventType;
import com.blockhaus2000.ipm.technical.command.event.CommandEventManager;
import com.blockhaus2000.ipm.technical.command.util.CommandContext;
import com.blockhaus2000.ipm.technical.command.util.CommandInfo;
import com.blockhaus2000.ipm.technical.command.util.RawCommandContext;
import com.blockhaus2000.ipm.technical.command.util.SimpleCommandContext;
import com.blockhaus2000.ipm.technical.command.util.SimpleCommandInfo;
import com.blockhaus2000.ipm.technical.command.util.SimpleRawCommandContext;
import com.blockhaus2000.ipm.technical.command.util.exception.CommandException;
import com.blockhaus2000.ipm.util.Tag;
import com.blockhaus2000.ipm.util.exception.IllegalMethodSignatureException;
import com.blockhaus2000.ipm.util.exception.IllegalStaticAccessException;

/**
 * This is an implementation of {@link CommandManager}.
 *
 */
public class SimpleCommandManager implements CommandManager {
    /**
     * THE instance of the {@link SimpleCommandManager}.
     *
     */
    private static final CommandManager INSTANCE = new SimpleCommandManager();

    /**
     * Contains all registered commands.
     *
     * <ul>
     * <li>Key (String): The name of the command that is stored in the value.</li>
     * <li>Value (Set{@code <CommandInfo> } A list of all {@link CommandInfo}s
     * for one command.</li>
     * </ul>
     */
    private final Map<String, Set<CommandInfo>> commands = new HashMap<String, Set<CommandInfo>>();

    /**
     * Constructor of SimpleCommandManager.
     *
     * <p>
     * <b> NOTE: use {@link SimpleCommandManager#getInstance()} instead. </b>
     * </p>
     */
    private SimpleCommandManager() {
        // Nothing to do (only to provide singleton).
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.CommandManager#register(java.lang.Class,
     *      java.lang.Object)
     */
    @Override
    public Set<CommandInfo> register(final Class<?> clazz, final Object obj) {
        assert clazz != null : "Clazz cannot be null!";
        assert obj == null || clazz.equals(obj.getClass()) : "Obj has to be null or an object of clazz!";

        final Set<CommandInfo> result = new HashSet<CommandInfo>();

        for (final Method method : clazz.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Command.class)) {
                continue;
            }
            if (!Modifier.isStatic(method.getModifiers()) && obj == null) {
                throw new IllegalStaticAccessException("The method <" + method + "> is non-static and the given object (<" + obj
                        + ">) is null!");
            }
            if (method.getParameterTypes().length != 1 || !method.getParameterTypes()[0].equals(CommandContext.class)) {
                throw new IllegalMethodSignatureException("The signature of the method <" + method
                        + "> is incorrect (More detailed: Your arguments are not enough or too many, "
                        + "or your arguments types are not valid)! "
                        + "Please change your method signature to this (you can replace \"context\" "
                        + "with the name you like more): \"" + method.getName() + "(" + CommandContext.class.getName()
                        + " context)\"");
            }

            final Command commandAnot = method.getAnnotation(Command.class);
            for (final String alias : commandAnot.aliases()) {
                if (!commands.containsKey(alias)) {
                    commands.put(alias, new HashSet<CommandInfo>());
                }
                commands.get(alias).add(new SimpleCommandInfo(commandAnot, clazz, obj, method));
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.CommandManager#register(java.lang.Object)
     */
    @Override
    public Set<CommandInfo> register(final Object obj) {
        assert obj != null : "Obj cannot be null!";

        return register(obj.getClass(), obj);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.CommandManager#register(java.lang.Class)
     */
    @Override
    public Set<CommandInfo> register(final Class<?> clazz) {
        return register(clazz, null);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.CommandManager#execute(java.lang.String,
     *      com.blockhaus2000.ipm.minecraft.api.command.CommandSender,
     *      java.lang.String...)
     */
    @Override
    public boolean execute(final String label, final CommandSender sender, final String... rawArgs) {
        assert label != null && !label.isEmpty() : "Label cannot be null or empty!";
        assert sender != null : "Sender cannot be null!";
        assert rawArgs != null : "RawArgs cannot be null!";

        if (commands.get(label) == null) {
            return false;
        }

        final List<CommandEventData> commandEventData = new ArrayList<CommandEventData>();

        boolean executed = false;
        for (final CommandInfo commandInfo : commands.get(label)) {
            final SimpleRawCommandContext rawCommandContext = new SimpleRawCommandContext(commandInfo, label, rawArgs, sender);

            final CommandEventType commandEventType = isCommandExecutionValid(rawCommandContext);
            if (commandEventType != null) {
                commandEventData.add(new CommandEventData(rawCommandContext, commandEventType));
            }

            invoke(parseArguments(rawCommandContext));

            executed = true;
        }

        if (!executed) {
            CommandEventManager.getInstance().fire(new CommandEvent(commandEventData));
        }

        return executed;
    }

    /**
     * Checks if the command execution is valid.
     *
     * <p>
     * Checks these:
     * <ul>
     * <li>Permission</li>
     * <li>Minimum argument count</li>
     * <li>Maximum argument count</li>
     * </ul>
     * </p>
     *
     * @param rawCommandContext
     *            The {@link RawCommandContext} that contains all information
     *            that are required to validate the execution.
     * @return The {@link CommandEventType} that can be thrown if the call is
     *         invalid. Returns <code>null</code> if the execution is valid.
     *         Only returns event types that can be fired in combination with a
     *         {@link RawCommandContext}.
     */
    private CommandEventType isCommandExecutionValid(final RawCommandContext rawCommandContext) {
        assert rawCommandContext != null : "RawCommandContext cannot be null!";

        final Command commandAnot = rawCommandContext.getCommandAnot();
        final CommandSender sender = rawCommandContext.getSender();
        final String[] args = rawCommandContext.getRawArgs();

        final CommandEventType result;
        if (!sender.hasPermission(commandAnot.permission())) {
            result = CommandEventType.NO_PERMISSION;
        } else if (args.length < commandAnot.min()) {
            result = CommandEventType.NOT_ENOUGH_ARGUMENTS;
        } else if (commandAnot.max() != -1 && args.length > commandAnot.max()) {
            result = CommandEventType.TOO_MANY_ARGUMENTS;
        } else {
            result = null;
        }
        return result;
    }

    /**
     * Parses the arguments of the given {@link RawCommandContext}.
     *
     * @param rawCommandContext
     *            The {@link RawCommandContext} containing all information for
     *            parsing arguments.
     * @return A {@link CommandContext} containing the parsed arguments.
     */
    private CommandContext parseArguments(final RawCommandContext rawCommandContext) {
        assert rawCommandContext != null : "RawCommandContext cannot be null!";

        final List<Tag<?>> args = new ArrayList<Tag<?>>();
        for (final String rawArg : rawCommandContext.getRawArgs()) {
            args.add(new Tag<String>(rawArg));
        }

        return new SimpleCommandContext(rawCommandContext, args);
    }

    /**
     * Invokes the method containing in the given {@link CommandContext} with
     * the given {@link CommandContext}.
     *
     * @param context
     *            The {@link CommandContext} that will be passed into the method
     *            invokation.
     */
    private void invoke(final CommandContext context) {
        assert context != null : "Context cannot be null!";

        final Method method = context.getMethod();
        final boolean oldAccessible = method.isAccessible();
        method.setAccessible(true);
        try {
            method.invoke(context.getObject(), context);
        } catch (final IllegalArgumentException cause) {
            throw new CommandException("Maybe there is a wrong method signatur at method <" + method
                    + ">! Check documentation for signature details.", cause);
        } catch (final IllegalAccessException cause) {
            throw new CommandException("Not enough permissions.", cause);
        } catch (final InvocationTargetException cause) {
            throw new CommandException("An exception occurres in the command execution method!", cause);
        }
        method.setAccessible(oldAccessible);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.CommandManager#getCommands()
     */
    @Override
    public Map<String, Set<CommandInfo>> getCommands() {
        final Map<String, Set<CommandInfo>> result = new HashMap<String, Set<CommandInfo>>();
        for (final Entry<String, Set<CommandInfo>> entry : commands.entrySet()) {
            result.put(entry.getKey(), new HashSet<CommandInfo>(entry.getValue()));
        }
        return result;
    }

    /**
     *
     * @return An instance of the {@link SimpleCommandManager} (in form of a
     *         {@link CommandManager}).
     */
    public static CommandManager getInstance() {
        return SimpleCommandManager.INSTANCE;
    }
}
