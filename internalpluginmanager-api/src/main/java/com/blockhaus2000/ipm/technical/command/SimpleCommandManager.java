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
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.blockhaus2000.ipm.technical.command.event.CommandEvent;
import com.blockhaus2000.ipm.technical.command.event.CommandEventData;
import com.blockhaus2000.ipm.technical.command.event.CommandEventData.CommandEventType;
import com.blockhaus2000.ipm.technical.command.event.CommandEventManager;
import com.blockhaus2000.ipm.technical.command.util.CommandContext;
import com.blockhaus2000.ipm.technical.command.util.CommandInfo;
import com.blockhaus2000.ipm.technical.command.util.Constants;
import com.blockhaus2000.ipm.technical.command.util.RawCommandContext;
import com.blockhaus2000.ipm.technical.command.util.SimpleCommandContext;
import com.blockhaus2000.ipm.technical.command.util.SimpleCommandInfo;
import com.blockhaus2000.ipm.technical.command.util.SimpleRawCommandContext;
import com.blockhaus2000.ipm.technical.command.util.SyntaxType;
import com.blockhaus2000.ipm.technical.command.util.exception.CommandException;
import com.blockhaus2000.ipm.util.ArrayUtil;
import com.blockhaus2000.ipm.util.CollectionUtil;
import com.blockhaus2000.ipm.util.CommonConstants;
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
                throw new IllegalStaticAccessException("The method <" + method + "> is non-static and the given object (\"" + obj
                        + "\") is null!");
            }
            if (method.getParameterTypes().length != 1 || !method.getParameterTypes()[0].equals(CommandContext.class)) {
                throw new IllegalMethodSignatureException("The signature of the method \"" + method
                        + "\" is incorrect (More detailed: Your arguments are not enough or too many, "
                        + "or your arguments types are not valid)! "
                        + "Please change your method signature to this (you can replace \"context\" "
                        + "with the name you like more): \"" + method.getName() + "(" + CommandContext.class.getName()
                        + " context)\"");
            }

            final Command commandAnot = method.getAnnotation(Command.class);
            for (String alias : commandAnot.aliases()) {
                alias = alias.toLowerCase().trim();

                if (alias.isEmpty()) {
                    continue;
                }

                final Map<Character, SyntaxType> flagData = new HashMap<Character, SyntaxType>();
                for (String rawFlagData : commandAnot.flags()) {
                    rawFlagData = rawFlagData.trim();

                    if (rawFlagData.isEmpty()) {
                        continue;
                    }
                    if (!rawFlagData.matches(Constants.FLAG_REGEX)) {
                        throw new CommandException("The flag \"" + rawFlagData + "\" does not match the regex \""
                                + Constants.FLAG_REGEX + "\"");
                    }

                    flagData.put(rawFlagData.charAt(0),
                            rawFlagData.contains(":") ? SyntaxType.getFromName(rawFlagData.split(":")[1].toLowerCase()) : null);
                }

                if (!commands.containsKey(alias)) {
                    commands.put(alias, new HashSet<CommandInfo>());
                }
                commands.get(alias).add(new SimpleCommandInfo(commandAnot, clazz, obj, method, flagData));
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
     *      com.blockhaus2000.ipm.technical.command.CommandSender,
     *      java.lang.String...)
     */
    @Override
    public boolean execute(String label, final CommandSender sender, final String... rawArgs) {
        label = label == null ? null : label.toLowerCase().trim();

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
            final Command commandAnot = rawCommandContext.getCommandAnot();

            if (!sender.hasPermission(commandAnot.permission())) {
                commandEventData.add(new CommandEventData(rawCommandContext, CommandEventType.NO_PERMISSION));
                continue;
            }

            final CommandContext commandContext = parseArguments(rawCommandContext, commandEventData);
            if (commandContext == null) {
                continue;
            }
            invoke(commandContext);

            executed = true;
        }

        if (!executed) {
            CommandEventManager.getInstance().fire(new CommandEvent(commandEventData));
        }

        return executed;
    }

    /**
     * Parses the arguments of the given {@link RawCommandContext}.
     *
     * @param rawCommandContext
     *            The {@link RawCommandContext} containing all information for
     *            parsing arguments.
     * @param commandEventData
     *            The {@link List} of {@link CommandEventData} that will be
     *            fired. Used to deploy events.
     * @return A {@link CommandContext} containing the parsed arguments.
     */
    private CommandContext parseArguments(final RawCommandContext rawCommandContext, final List<CommandEventData> commandEventData) {
        assert rawCommandContext != null : "RawCommandContext cannot be null!";

        final List<String> rawArgs = new LinkedList<String>(Arrays.asList(rawCommandContext.getRawArgs()));

        final SortedSet<Integer> toRemove = new TreeSet<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(final Integer o1, final Integer o2) {
                return -o1.compareTo(o2); // Sort against the normal order.
            }
        });
        final Map<Character, Tag<?>> flags = new HashMap<Character, Tag<?>>();
        for (final Entry<Character, SyntaxType> entry : rawCommandContext.getFlagData().entrySet()) {
            final Character key = entry.getKey();
            final SyntaxType value = entry.getValue();

            final String flagKey = CommonConstants.DASH + key;
            int flagIndex = -1;
            for (int i = 0; i < rawArgs.size(); i++) {
                if (rawArgs.get(i).equals(flagKey)) {
                    flagIndex = i;
                    break;
                }
            }

            if (flagIndex == -1) {
                flags.put(key, value == null ? new Tag<Boolean>(false) : null);
                continue;
            }
            toRemove.add(flagIndex);
            if (value == null) {
                flags.put(key, new Tag<Boolean>(true));
                continue;
            }
            if (flagIndex + 1 >= rawArgs.size()) {
                commandEventData.add(new CommandEventData(rawCommandContext, CommandEventType.MISSING_FLAG_VALUE));
                continue;
            }

            try {
                switch (value) {
                case STRING:
                    flags.put(key, new Tag<String>(rawArgs.get(flagIndex + 1)));
                    break;
                case LONG:
                    flags.put(key, new Tag<Long>(Long.parseLong(rawArgs.get(flagIndex + 1))));
                    break;
                case DOUBLE:
                    flags.put(key, new Tag<Double>(Double.parseDouble(rawArgs.get(flagIndex + 1))));
                    break;
                case STRING_VARARG:
                    String parsedVarArg = parseVarArg(rawArgs, flagIndex + 1, rawArgs.get(flagIndex + 1).charAt(0));
                    parsedVarArg = parsedVarArg.substring(1, parsedVarArg.length() - 1);

                    for (int i = 1; i < parsedVarArg.split(" ").length; i++) {
                        toRemove.add(flagIndex + i + 1);
                    }

                    flags.put(key, new Tag<String>(parsedVarArg));
                    break;
                }
                toRemove.add(flagIndex + 1);
            } catch (final NumberFormatException ex) {
                commandEventData.add(new CommandEventData(rawCommandContext, CommandEventType.INCONSTISTENT_FLAG_VALUE));
                return null;
            }
        }
        for (final Integer index : toRemove) {
            rawArgs.remove(index.intValue());
        }

        final Command commandAnot = rawCommandContext.getCommandAnot();
        if (rawArgs.size() < commandAnot.min()) {
            commandEventData.add(new CommandEventData(rawCommandContext, CommandEventType.NOT_ENOUGH_ARGUMENTS));
            return null;
        }
        if (commandAnot.max() != -1 && rawArgs.size() > commandAnot.max()) {
            commandEventData.add(new CommandEventData(rawCommandContext, CommandEventType.TOO_MANY_ARGUMENTS));
            return null;
        }

        final List<Tag<?>> args = new ArrayList<Tag<?>>(CollectionUtil.toTagCollection(rawArgs, ArrayList.class));
        return new SimpleCommandContext(rawCommandContext, args, flags);
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
     * Parses the given arguments into a list. If <code>quote</code> is
     * <code>"</code> or <code>'</code>, the end will be automaticly detected.
     * Otherwise, it runs to the end of the list.
     *
     * @param args
     *            The arguments to parse.
     * @param startIndex
     *            The index where to start the parsing.
     * @param quote
     *            If <code>"</code> or <code>'</code>, it automaticly detects
     *            the end of the var arg. Otherwise, it runs to the end of the
     *            list.
     * @return A parsed var arg.
     */
    private String parseVarArg(final List<String> args, final int startIndex, final Character quote) {
        int endIndex = args.size() - 1;
        if (quote.equals('"') || quote.equals('\'')) {
            for (int i = startIndex; i < args.size(); i++) {
                final String cur = args.get(i);
                if (cur.endsWith(quote.toString())) {
                    endIndex = i;
                    break;
                }
            }
        }

        return ArrayUtil.joinString(CommonConstants.BLANK, startIndex, endIndex + 1, args.toArray(new String[args.size()]));
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
