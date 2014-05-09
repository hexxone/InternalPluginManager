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
import com.blockhaus2000.util.command.ContextData;
import com.blockhaus2000.util.command.RawCommandContext;

/**
 * Represents a {@link CommandManager}.
 *
 * @author Blockhaus2000
 * @see com.blockhaus2000.minecraft.util.command.CommandManager
 */
public class SimpleCommandManager implements CommandManager {
    private static SimpleCommandManager instance;

    private final String flagRegex = "[a-zA-Z]:?";
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

        Set<CommandInfo> registered = new HashSet<CommandInfo>();

        for (Method targetMethod : clazz.getMethods()) {
            if (!targetMethod.isAnnotationPresent(Command.class)) {
                continue;
            }

            assert Modifier.isStatic(targetMethod.getModifiers()) || obj != null : "The method \"" + targetMethod
                    + "\" is non-static and the given Object is null.";
            assert targetMethod.toString().split("\\(")[1].equals("com.blockhaus2000.util.command.CommandContext)") : "The "
                    + "arguments of the method \"" + targetMethod
                    + "\" are not correct. The only argument has to be \"CommandContext\"";

            String[] flags = targetMethod.getAnnotation(Command.class).flags();
            for (String targetFlag : flags) {
                if (targetFlag.length() == 0) {
                    continue;
                }

                assert targetFlag.matches(flagRegex) : "The flag \"" + targetFlag + "\" does not match the regex \"" + flagRegex
                        + "\"!";
            }

            for (String targetAlias : targetMethod.getAnnotation(Command.class).aliases()) {
                if (targetAlias.equals("")) {
                    continue;
                }

                if (!cmds.containsKey(targetAlias)) {
                    cmds.put(targetAlias, new ArrayList<CommandInfo>());
                }

                Command cmdAnot = targetMethod.getAnnotation(Command.class);
                CommandInfo commandInfo = new CommandInfo(cmdAnot, clazz, obj, targetMethod, new CommandSyntax(cmdAnot.syntax()));

                registered.add(commandInfo);

                cmds.get(targetAlias).add(commandInfo);
                Collections.sort(cmds.get(targetAlias));
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

        if (!cmds.containsKey(label)) {
            throw new CommandException("The given command \"" + label + "\" is not registered.");
        }

        boolean executed = false;

        List<CommandEvent<?>> events = new ArrayList<CommandEvent<?>>();

        for (CommandInfo target : cmds.get(label)) {
            Command cmdAnot = target.getCommandAnot();

            RawCommandContext rawContext = new RawCommandContext(target, sender, cmd, label, bArgs);

            if (!isSenderCorrect(sender, cmdAnot)) {
                events.add(new IllegalSenderCommandEvent(rawContext));
                continue;
            }

            CommandSyntax syntax = target.getSyntax();

            int min = syntax.isNull() ? cmdAnot.min() : syntax.size();
            int max = syntax.isNull() ? cmdAnot.max() : syntax.endsWithVarArg() && cmdAnot.autoSetMaxOnSyntax() ? -1 : syntax
                    .size();

            final boolean secondLevelCmdSet = cmdAnot.secondLevelCommand().length() != 0;

            if (secondLevelCmdSet) {
                if (cmdAnot.secondLevelCommand().equalsIgnoreCase(bArgs[0])) {
                    List<String> tempArgs = new LinkedList<String>(Arrays.asList(bArgs));
                    tempArgs.remove(0);
                    bArgs = ArrayUtil.toStringArray(tempArgs);
                } else {
                    events.add(new UnknownSecondLevelCommandEvent(rawContext));
                    continue;
                }
            }

            List<Tag<?>> args = null;

            try {
                args = parseFlags(target, bArgs).getArgs();
            } catch (IndexOutOfBoundsException ex) {
                events.add(new IllegalSyntaxCommandEvent(rawContext, IllegalSyntaxType.UNAVAILABLE_FLAG_VALUE));
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

            ContextData contextData = null;

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
                throw new CommandException("An exception is throw in the called method!", ex);
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

    private ContextData parseCommand(final CommandInfo cmd, final List<String> rawArgs) {
        assert cmd != null : "Cmd cannot be null!";
        assert rawArgs != null : "RawArgs cannot be null!";

        ContextData contextData = parseFlags(cmd, rawArgs);

        return parseArguments(cmd, contextData.getArgs(), contextData.getFlags());
    }

    private ContextData parseCommand(final CommandInfo cmd, final String... args) {
        return parseCommand(cmd, Arrays.asList(args));
    }

    private ContextData parseFlags(final CommandInfo cmd, final List<String> rawArgs) throws IndexOutOfBoundsException {
        assert cmd != null : "Cmd cannot be null!";
        assert rawArgs != null : "RawArgs cannot be null!";

        List<String> args = new LinkedList<String>(rawArgs);

        Map<Character, Tag<?>> flags = new HashMap<Character, Tag<?>>();

        for (String target : cmd.getCommandAnot().flags()) {
            if (target.length() == 0) {
                continue;
            }

            boolean found = false;

            char flagIndex = target.charAt(0);
            String argFlag = "-" + flagIndex;

            flags.put(flagIndex, null);

            if (target.endsWith(":")) {
                if (args.contains(argFlag)) {
                    int valueIndex = args.indexOf(argFlag) + 1;

                    flags.put(flagIndex, new Tag<String>(args.get(valueIndex)));
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

    private ContextData parseFlags(final CommandInfo cmd, final String[] args) throws IndexOutOfBoundsException {
        return parseFlags(cmd, Arrays.asList(args));
    }

    private ContextData parseArguments(final CommandInfo cmd, final String[] rawArgsArray, final Map<Character, Tag<?>> flags) {
        assert cmd != null : "Cmd cannot be null!";
        assert rawArgsArray != null : "RawArgsArray cannot be null!";
        assert flags != null : "Flags cannot be null!";

        List<String> rawArgs = Arrays.asList(rawArgsArray);

        List<Tag<?>> args = new ArrayList<Tag<?>>();

        CommandSyntax syntax = cmd.getSyntax();
        if (syntax == null || syntax.getSyntax() == null) {
            for (String targetArg : rawArgs) {
                args.add(new Tag<String>(targetArg.trim()));
            }
        } else {
            for (int i = 0; i < syntax.getSyntax().size(); i++) {
                switch (syntax.getSyntax().get(i)) {
                case DOUBLE:
                    args.add(new Tag<Double>(Double.valueOf(rawArgs.get(i).trim())));
                    break;
                case INTEGER:
                    args.add(new Tag<Integer>(Integer.valueOf(rawArgs.get(i).trim())));
                    break;
                case LONG:
                    args.add(new Tag<Long>(Long.valueOf(rawArgs.get(i).trim())));
                    break;
                case STRING:
                    args.add(new Tag<String>(rawArgs.get(i).trim()));
                    break;
                case STRING_VARARG:
                    args.add(new Tag<String>(StringUtil.joinString(i, " ", rawArgs)));
                    break;
                }
            }
        }

        return new ContextData(args, flags);
    }

    private ContextData parseArguments(final CommandInfo cmd, final List<Tag<?>> rawArgs, final Map<Character, Tag<?>> flags) {
        List<String> args = new ArrayList<String>();

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
