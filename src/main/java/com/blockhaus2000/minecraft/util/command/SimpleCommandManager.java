/* This file is part of InternalPluginManager
 *
 * Copyright 2014 Blockhaus2000
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  see the License for the specific language governing permissions and
 *  Limitations under the License.
 */
package com.blockhaus2000.minecraft.util.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.blockhaus2000.main.bukkit.InternalPluginManager;
import com.blockhaus2000.minecraft.util.command.event.CommandEvent;
import com.blockhaus2000.minecraft.util.command.event.CommandEventPackage;
import com.blockhaus2000.minecraft.util.command.event.IllegalSenderCommandEvent;
import com.blockhaus2000.minecraft.util.command.event.IllegalSyntaxCommandEvent;
import com.blockhaus2000.minecraft.util.command.event.IllegalSyntaxType;
import com.blockhaus2000.minecraft.util.command.event.NoPermissionCommandEvent;
import com.blockhaus2000.minecraft.util.command.event.NotEnoughArgumentsCommandEvent;
import com.blockhaus2000.minecraft.util.command.event.TooManyArgumentsCommandEvent;
import com.blockhaus2000.minecraft.util.command.event.UnknownSecondLevelCommandEvent;
import com.blockhaus2000.plugin.SimpleIpmServer;
import com.blockhaus2000.util.ArrayUtil;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.PermissionUtil;
import com.blockhaus2000.util.StringUtil;
import com.blockhaus2000.util.Tag;
import com.blockhaus2000.util.command.CommandContext;
import com.blockhaus2000.util.command.CommandInfo;
import com.blockhaus2000.util.command.CommandSyntax;
import com.blockhaus2000.util.command.CommandSyntaxType;
import com.blockhaus2000.util.command.ContextData;
import com.blockhaus2000.util.command.RawCommandContext;

/**
 * Represents a {@link CommandManager}.
 *
 * @see com.blockhaus2000.minecraft.util.command.CommandManager
 */
public final class SimpleCommandManager implements CommandManager {
    private static SimpleCommandManager instance;

    /**
     * The regex that flags has to match with this {@link CommandManager}
     * implementation.
     *
     */
    public static final String flagRegex = "^[a-zA-Z](:("
            + (StringUtil.joinString("|", ArrayUtil.toStringArray(Arrays.asList(CommandSyntaxType.values()))) + "|String...")
                    .replace(".", "\\.").toLowerCase() + ")?)?$";

    private final Map<String, ArrayList<CommandInfo>> cmds = new HashMap<String, ArrayList<CommandInfo>>();

    private SimpleCommandManager() {
        // Nothing to do (only to provide singleton pattern)
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.minecraft.util.command.CommandManager#register(java.lang.Class,
     *      java.lang.Object)
     */
    @Override
    public Set<CommandInfo> register(final Class<?> clazz, final Object obj) throws CommandException {
        assert clazz != null : "Clazz cannot be null!";

        final Set<CommandInfo> registered = new HashSet<CommandInfo>();

        for (Method targetMethod : clazz.getMethods()) {
            if (!targetMethod.isAnnotationPresent(Command.class)) {
                continue;
            }

            assert Modifier.isStatic(targetMethod.getModifiers()) || obj != null : "The method \"" + targetMethod
                    + "\" is non-static and the given Object is null.";
            assert targetMethod.toString().split("\\(")[1].equals("com.blockhaus2000.util.command.CommandContext)") : "The "
                    + "arguments of the method \"" + targetMethod
                    + "\" are not correct. The only argument has to be \"CommandContext\"";

            final Command cmdAnot = targetMethod.getAnnotation(Command.class);

            for (String targetFlag : cmdAnot.flags()) {
                // We have to check for it, because maybe a developer thinks
                // "Oh, why don't add an empty flag?". So we have to skip them.
                if (targetFlag.length() == 0) {
                    continue;
                }

                targetFlag = targetFlag.trim().toLowerCase();
                assert targetFlag.matches(SimpleCommandManager.flagRegex) : "The flag \"" + targetFlag
                        + "\" does not match the regex \"" + SimpleCommandManager.flagRegex + "\"!";
            }

            for (String rawTargetAlias : cmdAnot.aliases()) {
                final String targetAlias = rawTargetAlias.toLowerCase();

                // We have to check for it, because maybe a developer thinks
                // "Oh, why don't add an empty alias?". So we have to skip them.
                if (targetAlias.length() == 0) {
                    continue;
                }

                if (!cmds.containsKey(targetAlias)) {
                    cmds.put(targetAlias, new ArrayList<CommandInfo>());
                }

                final CommandInfo commandInfo = new CommandInfo(cmdAnot, clazz, obj, targetMethod, new CommandSyntax(
                        cmdAnot.syntax()));

                cmds.get(targetAlias).add(commandInfo);
                // We have to sort them to enable the command priority.
                Collections.sort(cmds.get(targetAlias));

                registered.add(commandInfo);
            }
        }

        return registered;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.minecraft.util.command.CommandManager#register(java.lang.Class)
     */
    @Override
    public Set<CommandInfo> register(final Class<?> clazz) throws CommandException {
        return register(clazz, null);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.minecraft.util.command.CommandManager#register(java.lang.Object)
     */
    @Override
    public Set<CommandInfo> register(final Object obj) throws CommandException {
        return register(obj.getClass(), obj);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.command.CommandExecutor#onCommand(org.bukkit.command.CommandSender,
     *      org.bukkit.command.Command, java.lang.String, java.lang.String[])
     */
    @Override
    public boolean onCommand(final CommandSender sender, final org.bukkit.command.Command cmd, String label, String[] bArgs) {
        assert sender != null : "Sender cannot be null!";
        assert cmd != null : "Cmd cannot be null!";
        assert bArgs != null : "BArgs cannot be null!";
        assert label != null : "Label cannot be null!";
        assert label.length() != 0 : "Label cannot be empty!";

        label = label.contains(":") ? label.split(":")[1] : label;
        label = label.toLowerCase();

        if (!cmds.containsKey(label)) {
            throw new CommandException("The given command \"" + label + "\" is not registered.");
        }

        boolean executed = false;

        final List<CommandEvent<?>> events = new ArrayList<CommandEvent<?>>();

        for (CommandInfo target : cmds.get(label)) {
            final Command cmdAnot = target.getCommandAnot();

            final RawCommandContext rawContext = new RawCommandContext(target, sender, cmd, label, bArgs);

            if (!isSenderCorrect(sender, cmdAnot)) {
                events.add(new IllegalSenderCommandEvent(rawContext));
                continue;
            }

            final CommandSyntax syntax = target.getSyntax();

            final int min = syntax.isNull() ? cmdAnot.min() : syntax.size();
            final int max = syntax.isNull() ? cmdAnot.max() : syntax.endsWithVarArg() && cmdAnot.autoSetMaxOnSyntax() ? -1
                    : syntax.size();

            final boolean secondLevelCmdSet = cmdAnot.secondLevelCommand().length() != 0;

            if (secondLevelCmdSet) {
                // Thanks to the shortcuts in expressions, we do not have to
                // check for the length of the array bArgs before.
                if (bArgs.length >= 1 && cmdAnot.secondLevelCommand().equalsIgnoreCase(bArgs[0])) {
                    List<String> tempArgs = new LinkedList<String>(Arrays.asList(bArgs));
                    tempArgs.remove(0);
                    bArgs = ArrayUtil.toStringArray(tempArgs);
                } else {
                    events.add(new UnknownSecondLevelCommandEvent(rawContext));
                    continue;
                }
            }

            final List<Tag<?>> args;

            try {
                args = parseFlags(target, bArgs).getArgs();
            } catch (IndexOutOfBoundsException dummy) {
                events.add(new IllegalSyntaxCommandEvent(rawContext, IllegalSyntaxType.UNAVAILABLE_FLAG_VALUE));
                continue;
            } catch (NumberFormatException dummy) {
                events.add(new IllegalSyntaxCommandEvent(rawContext, IllegalSyntaxType.NUMBER_SYNTAX_IS_STRING));
                continue;
            }

            if (args.size() < min) {
                events.add(new NotEnoughArgumentsCommandEvent(rawContext));
                continue;
            }

            if (max != -1 && args.size() > max) {
                events.add(new TooManyArgumentsCommandEvent(rawContext));
                continue;
            }

            final ContextData contextData;

            try {
                contextData = parseCommand(target, bArgs);
            } catch (NumberFormatException ex) {
                events.add(new IllegalSyntaxCommandEvent(rawContext, IllegalSyntaxType.NUMBER_SYNTAX_IS_STRING));
                continue;
            }

            if (!PermissionUtil.hasPermission(sender, cmdAnot.permission())) {
                events.add(new NoPermissionCommandEvent(rawContext));
                continue;
            }

            try {
                target.getMethod().invoke(target.getObject(),
                        new CommandContext(rawContext, contextData.getArgs(), contextData.getFlags()));
                executed = true;
            } catch (IllegalArgumentException ex) {
                ExceptionHandler.handle(ex);
            } catch (IllegalAccessException ex) {
                ExceptionHandler.handle(ex);
            } catch (InvocationTargetException ex) {
                throw new CommandException("An exception is thrown in the called method!", ex);
            }
        }

        if (!executed) {
            for (CommandEvent<?> target : events) {
                Bukkit.getServer().getPluginManager().callEvent(target);
            }

            Bukkit.getServer().getPluginManager().callEvent(new CommandEventPackage(events));
        }

        return executed;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.command.TabCompleter#onTabComplete(org.bukkit.command.CommandSender,
     *      org.bukkit.command.Command, java.lang.String, java.lang.String[])
     */
    @Override
    public List<String> onTabComplete(final CommandSender sender, final org.bukkit.command.Command cmd, String label,
            final String[] args) {
        assert sender != null : "Sender cannot be null!";
        assert cmd != null : "Cmd cannot be null!";
        assert args != null : "Args cannot be null!";
        assert label != null : "Label cannot be null!";
        assert label.length() != 0 : "Label cannot be empty!";

        label = label.contains(":") ? label.split(":")[1] : label;
        label = label.toLowerCase();

        final List<String> result = new ArrayList<String>();

        if (!cmds.containsKey(label)) {
            return result; // return empty list if the command can not be found
        }

        final List<CommandInfo> cmdInfos = cmds.get(label);

        // Only if the args-length if beneath 2, the user has not entered the
        // second-level-command yet.
        if (args.length < 2) {
            for (CommandInfo cmdInfo : cmdInfos) {
                final String secLevCmd = cmdInfo.getCommandAnot().secondLevelCommand();

                if (secLevCmd == null || secLevCmd.length() == 0 || !secLevCmd.startsWith(args[0].toLowerCase())) {
                    continue;
                }

                result.add(secLevCmd);
            }

            if (result.size() > 0) {
                return result;
            }
        }

        for (CommandInfo cmdInfo : cmdInfos) {
            final String secondLevelCommand = cmdInfo.getCommandAnot().secondLevelCommand();
            if (secondLevelCommand == null || secondLevelCommand.length() == 0 || secondLevelCommand.equalsIgnoreCase(args[0])) {
                for (String str : InternalPluginManager.getServer().getTabCompletionManager()
                        .performTabComplete(cmdInfo, sender, cmd, label, args)) {
                    result.add(str);
                }
            }
        }
        return result;
    }

    private ContextData parseCommand(final CommandInfo cmd, final List<String> rawArgs) throws IndexOutOfBoundsException,
            NumberFormatException {
        assert cmd != null : "Cmd cannot be null!";
        assert rawArgs != null : "RawArgs cannot be null!";

        final ContextData contextData = parseFlags(cmd, rawArgs);
        return parseArguments(cmd, contextData.getArgs(), contextData.getFlags());
    }

    private ContextData parseCommand(final CommandInfo cmd, final String... args) throws IndexOutOfBoundsException,
            NumberFormatException {
        return parseCommand(cmd, Arrays.asList(args));
    }

    private ContextData parseFlags(final CommandInfo cmd, final List<String> rawArgs) throws IndexOutOfBoundsException,
            NumberFormatException {
        assert cmd != null : "Cmd cannot be null!";
        assert rawArgs != null : "RawArgs cannot be null!";

        final List<String> args = new LinkedList<String>(rawArgs);

        final Map<Character, Tag<?>> flags = new HashMap<Character, Tag<?>>();

        for (String target : cmd.getCommandAnot().flags()) {
            if (target.length() == 0) {
                continue;
            }

            boolean found = false;

            final char flagIndex = target.charAt(0);
            final String argFlag = "-" + flagIndex;

            flags.put(flagIndex, null);

            if (target.contains(":")) {
                if (args.contains(argFlag)) {
                    int valueIndex = args.indexOf(argFlag) + 1;

                    final String value = args.get(valueIndex).trim();

                    final String[] splitted = target.split(":");
                    switch (CommandSyntaxType.getTypeByString(splitted.length == 1 ? "String" : splitted[1])) {
                    case DOUBLE:
                        flags.put(flagIndex, new Tag<Double>(Double.parseDouble(value.trim())));
                        break;
                    case INTEGER:
                        flags.put(flagIndex, new Tag<Integer>(Integer.parseInt(value.trim())));
                        break;
                    case LONG:
                        flags.put(flagIndex, new Tag<Long>(Long.parseLong(value.trim())));
                        break;
                    case STRING:
                        flags.put(flagIndex, new Tag<String>(value.trim()));
                        break;
                    case STRING_VARARG:
                        if (!value.startsWith("\"")) {
                            flags.put(flagIndex, new Tag<String>(value.trim()));
                            break;
                        }

                        final List<String> values = new ArrayList<String>();

                        for (int i = valueIndex; i < args.size(); i++) {
                            final String targetValue = args.get(i);
                            values.add(targetValue);

                            if (targetValue.endsWith("\"")) {
                                break;
                            }
                        }

                        for (int i = 0; i < values.size() - 1; i++) {
                            args.remove(valueIndex);
                        }

                        flags.put(flagIndex,
                                new Tag<String>(StringUtil.joinString(" ", ArrayUtil.toStringArray(values)).replace("\"", "")
                                        .trim()));
                        break;
                    }

                    found = true;

                    args.remove(valueIndex);
                }
            } else {
                found = args.contains(argFlag);

                flags.put(flagIndex, new Tag<Boolean>(found));
            }

            if (found) {
                while (args.contains(argFlag)) {
                    args.remove(argFlag);
                }
            }
        }

        return new ContextData(ArrayUtil.toStringArray(args), flags);
    }

    private ContextData parseFlags(final CommandInfo cmd, final String[] args) throws IndexOutOfBoundsException,
            NumberFormatException {
        return parseFlags(cmd, Arrays.asList(args));
    }

    private ContextData parseArguments(final CommandInfo cmd, final String[] rawArgsArray, final Map<Character, Tag<?>> flags)
            throws IndexOutOfBoundsException, NumberFormatException {
        assert cmd != null : "Cmd cannot be null!";
        assert rawArgsArray != null : "RawArgsArray cannot be null!";
        assert flags != null : "Flags cannot be null!";

        final List<String> rawArgs = Arrays.asList(rawArgsArray);

        final List<Tag<?>> args = new ArrayList<Tag<?>>();

        final CommandSyntax syntax = cmd.getSyntax();

        if (syntax == null || syntax.isNull() || syntax.size() == 0) {
            for (String targetRawArg : rawArgs) {
                final String targetArg = targetRawArg.trim();

                if (targetArg.length() == 0) {
                    continue;
                }

                args.add(new Tag<String>(targetArg));
            }
        } else {
            for (int i = 0; i < syntax.getSyntax().size(); i++) {
                final String arg = rawArgs.get(i).trim();

                switch (syntax.getSyntax().get(i)) {
                case DOUBLE:
                    args.add(new Tag<Double>(Double.valueOf(arg.trim())));
                    break;
                case INTEGER:
                    args.add(new Tag<Integer>(Integer.valueOf(arg.trim())));
                    break;
                case LONG:
                    args.add(new Tag<Long>(Long.valueOf(arg.trim())));
                    break;
                case STRING:
                    args.add(new Tag<String>(arg.trim()));
                    break;
                case STRING_VARARG:
                    args.add(new Tag<String>(StringUtil.joinString(i, " ", rawArgs).trim()));
                    break;
                }
            }
        }

        return new ContextData(args, flags);
    }

    private ContextData parseArguments(final CommandInfo cmd, final List<Tag<?>> rawArgs, final Map<Character, Tag<?>> flags)
            throws IndexOutOfBoundsException, NumberFormatException {
        final List<String> args = new ArrayList<String>();

        for (Tag<?> target : rawArgs) {
            args.add((String) target.getData());
        }

        return parseArguments(cmd, args.toString().replace("[", "").replace("]", "").split(","), flags);
    }

    private boolean isSenderCorrect(final CommandSender sender, final Command cmd) {
        for (CommandSenderType target : cmd.sender()) {
            switch (target) {
            case COMMAND_BLOCK:
                if (sender instanceof BlockCommandSender) {
                    return true;
                }

                break;
            case CONSOLE:
                if (sender instanceof ConsoleCommandSender) {
                    return true;
                }

                break;
            case PLAYER:
                if (sender instanceof Player) {
                    return true;
                }

                break;
            }
        }

        return false;
    }

    /**
     * Will provide singleton.
     *
     * @return An instance of {@link SimpleIpmServer}.
     */
    public static SimpleCommandManager getInstance() {
        if (SimpleCommandManager.instance == null) {
            return SimpleCommandManager.instance = new SimpleCommandManager();
        }

        return SimpleCommandManager.instance;
    }
}
