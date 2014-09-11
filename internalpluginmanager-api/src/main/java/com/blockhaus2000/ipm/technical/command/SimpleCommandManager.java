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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;

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
import com.blockhaus2000.ipm.util.CommonStringConstants;
import com.blockhaus2000.ipm.util.Tag;
import com.blockhaus2000.ipm.util.exception.IllegalMethodSignatureException;
import com.blockhaus2000.ipm.util.exception.IllegalStaticAccessException;

/**
 * This is an implementation of {@link CommandManager}.
 *
 */
public final class SimpleCommandManager implements CommandManager {
    /**
     * The InternalPluginManager system logger.
     *
     */
    private static final Logger LOGGER = Logger.getLogger(CommonConstants.INTERNALPLUGINMANAGER_SYSTEM_LOGGER_NAME);

    /**
     * THE instance of the {@link SimpleCommandManager}.
     *
     */
    private static final CommandManager INSTANCE = new SimpleCommandManager();

    /**
     * The {@link CommandInfo} comparator.
     *
     */
    private static final Comparator<CommandInfo> COMMAND_INFO_COMPARATOR = new Comparator<CommandInfo>() {
        @Override
        public int compare(final CommandInfo o1, final CommandInfo o2) {
            return Integer.valueOf(o1.getCommandAnot().priority().getPriority()).compareTo(
                    o2.getCommandAnot().priority().getPriority());
        }
    };

    /**
     * A comparator to sort {@link Integer}s against their regular order
     * (reverse).
     *
     */
    private static final Comparator<Integer> REVERSE_INTEGER_COMPARATOR = new Comparator<Integer>() {
        @Override
        public int compare(final Integer o1, final Integer o2) {
            return -o1.compareTo(o2);
        }
    };

    /**
     * Contains all registered commands.
     *
     * <ul>
     * <li>Key (String): The name of the command that is stored in the value.</li>
     * <li>Value (Set{@code <CommandInfo> } A list of all {@link CommandInfo}s
     * for one command.</li>
     * </ul>
     */
    private final Map<String, List<CommandInfo>> commands = new HashMap<String, List<CommandInfo>>();

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
    public <T> Set<CommandInfo> register(final Class<T> clazz, final T obj) {
        assert clazz != null : "Clazz cannot be null!";
        assert obj == null || clazz.equals(obj.getClass()) : "Obj has to be null or an object of clazz!";

        SimpleCommandManager.LOGGER.fine("Registering class \"" + clazz.getName() + "\" with object \"" + obj + "\".");

        // Contains all successful registered commands.
        final Set<CommandInfo> registered = new HashSet<CommandInfo>();

        for (final Method method : clazz.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Command.class)) {
                SimpleCommandManager.LOGGER.finer("Skipping method \"" + method.getName() + "\" (annotation not present).");

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

            SimpleCommandManager.LOGGER.fine("Parsing method \"" + method.getName() + "\" with annotation data \"" + commandAnot
                    + "\".");
            SimpleCommandManager.LOGGER.finer("Parsing flags.");

            // The flag data is parsed here to improve the performance.
            // If the flag is a toggle flag, the SyntaxType is null.
            final Map<Character, SyntaxType> flagData = new HashMap<Character, SyntaxType>();
            for (final String rawFlagdata : commandAnot.flags()) {
                // Flags should be trimmed.
                final String flagDataPart = rawFlagdata.trim();

                // Empty flags are be ignored, becase the default value
                // is an empty string.
                if (flagDataPart.isEmpty()) {
                    continue;
                }
                if (!flagDataPart.matches(Constants.FLAG_REGEX)) {
                    throw new CommandException("The flag \"" + flagDataPart + "\" does not match the regex \""
                            + Constants.FLAG_REGEX + "\"");
                }

                SimpleCommandManager.LOGGER.finest("Parsing flag \"" + flagDataPart + "\".");

                // The first char is ALWAYS the flag name, the regex checks
                // that. If the flag a value flag, the flag string contains
                // a colon (:), and directly after the colon stands the
                // syntax name. If there is no colon, the syntax type is
                // null. That means, the flag is a toggle flag.
                flagData.put(
                        flagDataPart.charAt(0),
                        flagDataPart.contains(CommonStringConstants.COLON) ? SyntaxType.getFromName(flagDataPart
                                .split(CommonStringConstants.COLON)[1].toLowerCase()) : null);
            }

            SimpleCommandManager.LOGGER.finer("Parsing syntax.");

            // The syntax string should be trimmed.
            final String rawSyntax = commandAnot.syntax().trim();
            // If the syntax string is empty, no syntax check will be
            // processed.
            final String syntax = rawSyntax.isEmpty() ? null : rawSyntax;
            // If the syntax is null, this list has to be null.
            final List<SyntaxType> syntaxData = syntax == null ? null : new ArrayList<SyntaxType>();
            // Only parse syntax if it is available.
            if (syntax != null) {
                if (!syntax.matches(Constants.SYNTAX_REGEX)) {
                    throw new CommandException("The syntax \"" + syntax + "\" does not match the regex \""
                            + Constants.SYNTAX_REGEX + "\"!");
                }
                for (final String syntaxName : syntax.split(", *")) {
                    SimpleCommandManager.LOGGER.finest("Parsing syntax element \"" + syntaxName + "\".");

                    // This cannot return null. The regex checks the name
                    // before.
                    syntaxData.add(SyntaxType.getFromName(syntaxName));
                }
            }

            final CommandInfo commandInfo = new SimpleCommandInfo(commandAnot, obj, method, flagData, syntaxData);

            SimpleCommandManager.LOGGER.finer("Registering command information (\"" + commandInfo + "\").");
            for (String alias : commandAnot.aliases()) {
                // Aliases should be lowercase and trimmed.
                alias = alias.toLowerCase().trim();

                if (alias.contains(CommonStringConstants.BLANK)) {
                    throw new CommandException("Aliases cannot contain blanks (\" \")!");
                }
                // Empty aliases are ignored, because you can not execute them
                // ;)
                if (alias.isEmpty()) {
                    continue;
                }

                SimpleCommandManager.LOGGER.finest("Registering for alias \"" + alias + "\"");

                // If the command map does not contain a list of command infos
                // for the current flag, an ArrayList is created.
                if (!this.commands.containsKey(alias)) {
                    SimpleCommandManager.LOGGER.finest("Adding new list.");

                    this.commands.put(alias, new ArrayList<CommandInfo>());
                }

                // Only if the command info was added successful, the command
                // info should be added to the "registered"-list.
                if (this.commands.get(alias).add(commandInfo)) {
                    // Sorts the command info in the priority order. A SortedSet
                    // cannot be used, because it does not add the value in case
                    // the priority is the same (if
                    // Comparator#compare(CommandInfo, CommandInfo) returns 0).
                    Collections.sort(this.commands.get(alias), SimpleCommandManager.COMMAND_INFO_COMPARATOR);
                    // Now, the command is registered successful.
                    registered.add(commandInfo);
                }
            }
        }

        SimpleCommandManager.LOGGER.fine("Registered " + registered.size() + " commands (\"" + registered + "\").");

        return registered;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.CommandManager#register(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> Set<CommandInfo> register(final T obj) {
        assert obj != null : "Obj cannot be null!";

        return this.register((Class<T>) obj.getClass(), obj);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.CommandManager#register(java.lang.Class)
     */
    @Override
    public <T> Set<CommandInfo> register(final Class<T> clazz) {
        return this.register(clazz, null);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.CommandManager#unregister(java.lang.Class)
     */
    @Override
    public Set<CommandInfo> unregister(final Class<?> clazz) {
        final Set<CommandInfo> unregistered = new HashSet<CommandInfo>();

        for (final Method method : clazz.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Command.class)) {
                continue;
            }

            for (final List<CommandInfo> commandInfos : this.commands.values()) {
                for (final Iterator<CommandInfo> iterator = commandInfos.iterator(); iterator.hasNext();) {
                    final CommandInfo commandInfo = iterator.next();
                    if (commandInfo.getMethod().getDeclaringClass().equals(clazz)) {
                        iterator.remove();
                        unregistered.add(commandInfo);
                    }
                }
            }
        }

        return unregistered;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.CommandManager#unregister(java.lang.Object)
     */
    @Override
    public Set<CommandInfo> unregister(final Object obj) {
        return this.unregister(obj.getClass());
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.CommandManager#execute(java.lang.String,
     *      com.blockhaus2000.ipm.technical.command.CommandSender,
     *      java.lang.String...)
     */
    @Override
    public boolean execute(final String rawLabel, final CommandSender sender, final String... rawArgs) {
        final String label = rawLabel == null ? null : rawLabel.toLowerCase().trim();

        assert label != null && !label.isEmpty() : "Label cannot be null or empty!";
        assert sender != null : "Sender cannot be null!";
        assert rawArgs != null : "RawArgs cannot be null!";

        SimpleCommandManager.LOGGER.fine("\"" + sender + "\" executed \"" + label + "\" with arguments \""
                + Arrays.asList(rawArgs) + "\"");

        // If no registered commands could be found, it is impossible to execute
        // them.
        if (this.commands.get(label) == null) {
            SimpleCommandManager.LOGGER.fine("Command label not found.");

            return false;
        }

        // If an "error" occurres while parsing the command, a CommandEvent
        // should be thrown. The CommandEventData is stored here.
        final List<CommandEventData> commandEventData = new ArrayList<CommandEventData>();

        // This boolean is returned at the end.
        boolean executed = false;
        for (final CommandInfo commandInfo : this.commands.get(label)) {
            // if (commandInfo.getClazz() == null) {
            // continue;
            // }

            final Command commandAnot = commandInfo.getCommandAnot();

            SimpleCommandManager.LOGGER.finer("Executing \"" + commandInfo + "\".");

            // The trimmed second level command is used twice, so it is assigned
            // to a variable. Do NOT use this.
            final String rawSecondLevelCommand = commandAnot.secondLevelCommand().trim();
            // By default, no scl is set. Then, it should be null.
            final String secondLevelCommand = rawSecondLevelCommand.isEmpty() ? null : rawSecondLevelCommand;
            // Only handle a scl, if one is set.
            final String[] args;
            if (secondLevelCommand == null) {
                args = rawArgs;
            } else {
                // This RawCommandContext is only temporary and does not include
                // the correct raw args.
                final RawCommandContext tempRawCommandContext = new SimpleRawCommandContext(commandInfo, label, rawArgs, sender);

                // If the arguments are empty, no scl has been passed into the
                // execution.
                if (rawArgs.length == 0) {
                    commandEventData.add(new CommandEventData(tempRawCommandContext,
                            CommandEventType.UNAVAILABLE_SECOND_LEVEL_COMMAND));
                    continue;
                }
                // The scl has to match the first argument, because that is the
                // scl.
                if (!rawArgs[0].equalsIgnoreCase(secondLevelCommand)) {
                    commandEventData.add(new CommandEventData(tempRawCommandContext,
                            CommandEventType.UNKNOWN_SECOND_LEVEL_COMMAND));
                    continue;
                }
                // Now, remove the first argument from the argument array.
                args = Arrays.copyOfRange(rawArgs, 1, rawArgs.length);
            }

            SimpleCommandManager.LOGGER.finest("Second level command is set to \"" + secondLevelCommand + "\"");

            final SimpleRawCommandContext rawCommandContext = new SimpleRawCommandContext(commandInfo, label, args, sender);

            // Check permission.
            if (!sender.hasPermission(commandAnot.permission())) {
                commandEventData.add(new CommandEventData(rawCommandContext, CommandEventType.NO_PERMISSION));
                continue;
            }

            final CommandContext commandContext = this.parseArguments(rawCommandContext, commandEventData);
            // parseArguments(RawCommandContext, List<CommandEventData>) returns
            // null if an "error" occurres and adds a CommandEventData to the
            // list.
            if (commandContext == null) {
                continue;
            }

            SimpleCommandManager.LOGGER.finest("Command parsed to \"" + commandContext + "\".");

            // Now, invoke/execute the method that is associated with the
            // current command (alias).
            this.invoke(commandContext);

            // Now, the command has been executed successfully.
            executed = true;
        }

        SimpleCommandManager.LOGGER.finer("Created events: \"" + commandEventData + "\".");
        SimpleCommandManager.LOGGER.fine("Executed: " + executed);

        // If no command was executed, fire the CommandEvent.
        if (!executed && !commandEventData.isEmpty()) {
            CommandEventManager.getInstance().fire(new CommandEvent(commandEventData));
        }

        // Tell the "callers" if the command has been executed.
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

        SimpleCommandManager.LOGGER.fine("Parsing arguments for \"" + rawCommandContext + "\".");

        // A LinkedList has to be used, because later, some entries will be
        // removed. An ArrayList does not support this feature.
        final List<String> rawArgs = new LinkedList<String>(Arrays.asList(rawCommandContext.getRawArgs()));

        // Reverse order is required whilest removing the parsed arguments from
        // the argument list. Other sortings will crash
        // the system, cause a IndexOutOfBoundsException is thrown. With this,
        // it is save for work.
        final SortedSet<Integer> toRemove = new TreeSet<Integer>(SimpleCommandManager.REVERSE_INTEGER_COMPARATOR);

        SimpleCommandManager.LOGGER.finer("Parsing flags.");

        // Flag data is stored here. If a value flag is not set, the value is
        // null.
        final Map<Character, Tag<?>> flags = new HashMap<Character, Tag<?>>();
        // To iterate about an entry set is faster than to iterator about the
        // keys and get the values for them.
        for (final Entry<Character, SyntaxType> entry : rawCommandContext.getFlagData().entrySet()) {
            final Character key = entry.getKey();
            final SyntaxType value = entry.getValue();

            SimpleCommandManager.LOGGER.finest("Parsing flag \"" + key + "\".");

            // In the flag data, flags are stored with signle characters, but a
            // dash in front is needed to set them whilest executing the
            // command.
            // If the flag was not found, flag index is -1.
            final int flagIndex = rawArgs.indexOf(CommonStringConstants.DASH + key);

            if (flagIndex == -1) {
                // If the flag was not found, put false into the flag map, if
                // the flag is a toggle flag, otherwise null.
                flags.put(key, value == null ? new Tag<Boolean>(false) : null);
                continue;
            }
            // Add the flag index to the remove list.
            toRemove.add(flagIndex);
            if (value == null) {
                // The flag was found and the flag is a toggle flag. Add true to
                // the flag map.
                flags.put(key, new Tag<Boolean>(true));
                continue;
            }
            // Now, the flag must be a vakue flag. The value is behind the flag,
            // so the arguments must be as long as the incremented flag index.
            if (flagIndex + 1 >= rawArgs.size()) {
                commandEventData.add(new CommandEventData(rawCommandContext, CommandEventType.UNAVAILABLE_FLAG_VALUE));
                // An "error" occurred.
                return null;
            }

            try {
                switch (value) {
                    case STRING:
                        // Yay, nothing has to be parsed.
                        flags.put(key, new Tag<String>(rawArgs.get(flagIndex + 1)));
                        break;
                    case LONG:
                        flags.put(key, new Tag<Long>(Long.valueOf(rawArgs.get(flagIndex + 1))));
                        break;
                    case DOUBLE:
                        flags.put(key, new Tag<Double>(Double.valueOf(rawArgs.get(flagIndex + 1))));
                        break;
                    case STRING_VARARG:
                        // :/ VarArgs has to be parsed.
                        String parsedVarArg = this.parseVarArg(rawArgs, flagIndex + 1, rawArgs.get(flagIndex + 1).charAt(0));
                        parsedVarArg = parsedVarArg.substring(1, parsedVarArg.length() - 1);

                        // Add indexes to the remove set.
                        for (int i = 1; i < parsedVarArg.split(" ").length; i++) {
                            toRemove.add(flagIndex + i + 1);
                        }

                        flags.put(key, new Tag<String>(parsedVarArg));
                        break;
                }
                // This should be removed always because index + 1 is the value.
                toRemove.add(flagIndex + 1);
            } catch (final NumberFormatException dummy) {
                // Add an event instead of throwing the exception.
                commandEventData.add(new CommandEventData(rawCommandContext, CommandEventType.INCONSTISTENT_FLAG_VALUE));
                // An "error" occurred.
                return null;
            }
        }

        SimpleCommandManager.LOGGER.finer("Flags parsed to \"" + flags + "\".");
        SimpleCommandManager.LOGGER.finer("Removing parsed arguments.");

        // Remove all added indexes to remove. The set is sorted reversed, so
        // iterate about the set and remove every value is possible without an
        // IndexOutOfBoundsException.
        for (final Integer index : toRemove) {
            rawArgs.remove(index.intValue());
        }

        SimpleCommandManager.LOGGER.finer("Parsed arguments removed.");
        SimpleCommandManager.LOGGER.finer("Calculating/checking min/max arguments.");

        // Set min/max arguments.
        final Command commandAnot = rawCommandContext.getCommandAnot();
        final List<SyntaxType> syntaxData = rawCommandContext.getSyntaxData();
        final int min = syntaxData == null ? commandAnot.min() : syntaxData.size();
        final int max = syntaxData == null ? commandAnot.max() : !commandAnot.autoSetMaxOnSyntax()
                || syntaxData.get(syntaxData.size() - 1) == SyntaxType.STRING_VARARG ? -1 : syntaxData.size();

        SimpleCommandManager.LOGGER.finest("Minimal arguments set to " + min + ".");
        SimpleCommandManager.LOGGER.finest("Maximal arguments set to " + max + ".");

        // Check min/max arguments.
        if (rawArgs.size() < min) {
            commandEventData.add(new CommandEventData(rawCommandContext, CommandEventType.NOT_ENOUGH_ARGUMENTS));
            return null;
        }
        if (max != -1 && rawArgs.size() > max) {
            commandEventData.add(new CommandEventData(rawCommandContext, CommandEventType.TOO_MANY_ARGUMENTS));
            return null;
        }

        SimpleCommandManager.LOGGER.finer("Parsing arguments.");

        // Parse arguments
        final List<Tag<?>> args;
        if (syntaxData == null) {
            SimpleCommandManager.LOGGER.finest("No syntax available, nothing to parse.");

            args = new ArrayList<Tag<?>>(CollectionUtil.toTagCollection(rawArgs, ArrayList.class));
        } else {
            SimpleCommandManager.LOGGER.finest("Parsing syntax.");

            args = new ArrayList<Tag<?>>();
            try {
                for (int i = 0; i < syntaxData.size(); i++) {
                    final String arg = rawArgs.get(i);
                    switch (syntaxData.get(i)) {
                        case STRING:
                            // Yay, no parsing required.
                            args.add(new Tag<String>(arg));
                            break;
                        case LONG:
                            args.add(new Tag<Long>(Long.valueOf(arg)));
                            break;
                        case DOUBLE:
                            args.add(new Tag<Double>(Double.valueOf(arg)));
                            break;
                        case STRING_VARARG:
                            // :/ VarArgs parsing required.
                            args.add(new Tag<String>(this.parseVarArg(rawArgs, i, null)));
                            break;
                    }
                }
            } catch (final NumberFormatException dummy) {
                // Add an event instead of throwing the exception.
                commandEventData.add(new CommandEventData(rawCommandContext, CommandEventType.INCONSTISTENT_FLAG_VALUE));
                // An "error" occurred.
                return null;
            }
        }

        SimpleCommandManager.LOGGER.finer("Arguments parsed to \"" + args + "\".");

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

        SimpleCommandManager.LOGGER.fine("Invoking method \"" + method.getName() + "\"");

        // The accessible status should be resetted to the old state.
        final boolean oldAccessible = method.isAccessible();

        SimpleCommandManager.LOGGER.finest("Setting accessible to true.");

        method.setAccessible(true);
        try {
            SimpleCommandManager.LOGGER.finest("Invoking method \"" + method.getName() + "\" on object \"" + context.getObject()
                    + "\" with the argument \"" + context + "\".");

            method.invoke(context.getObject(), context);
        } catch (final IllegalArgumentException cause) {
            throw new CommandException("Maybe there is a wrong method signatur at method <" + method
                    + ">! Check documentation for signature details.", cause);
        } catch (final IllegalAccessException cause) {
            throw new CommandException("Not enough permissions.", cause);
        } catch (final InvocationTargetException cause) {
            throw new CommandException("An exception occurres in the command execution method!", cause);
        }

        SimpleCommandManager.LOGGER.finest("Resetting accessible to " + oldAccessible + ".");

        // Reset accessible status.
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
        if (quote != null && (quote.equals('"') || quote.equals('\''))) {
            for (int i = startIndex; i < args.size(); i++) {
                final String cur = args.get(i);
                if (cur.endsWith(quote.toString())) {
                    endIndex = i;
                    break;
                }
            }
        }

        return ArrayUtil.joinString(CommonStringConstants.BLANK, startIndex, endIndex + 1, args.toArray(new String[args.size()]));
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.technical.command.CommandManager#getCommands()
     */
    @Override
    public Map<String, List<CommandInfo>> getCommands() {
        final Map<String, List<CommandInfo>> result = new HashMap<String, List<CommandInfo>>();
        for (final Entry<String, List<CommandInfo>> entry : this.commands.entrySet()) {
            result.put(entry.getKey(), new ArrayList<CommandInfo>(entry.getValue()));
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
