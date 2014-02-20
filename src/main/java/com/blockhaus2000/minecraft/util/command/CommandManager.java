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
 *  package com.blockhaus2000.bukkit.util;
 */
package com.blockhaus2000.minecraft.util.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.blockhaus2000.minecraft.util.command.event.CommandEvent;
import com.blockhaus2000.minecraft.util.command.event.NoPermissionCommandEvent;
import com.blockhaus2000.minecraft.util.command.event.NotEnoughArgumentsCommandEvent;
import com.blockhaus2000.minecraft.util.command.event.TooManyArgumentsCommandEvent;
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
 * 
 * @author Blockhaus2000
 */
public class CommandManager implements CommandExecutor {
    private static CommandManager instance;

    private final String flagRegex = "[a-zA-Z]:?";
    private final Map<String, ArrayList<CommandInfo>> cmds = new HashMap<String, ArrayList<CommandInfo>>();

    private CommandManager() {
        // Nothing to do (only to provide singleton pattern)
    }

    public List<CommandInfo> register(final Class<?> clazz, final Object obj) throws CommandException {
        assert clazz != null : "Clazz cannot be null!";

        List<CommandInfo> registered = new ArrayList<CommandInfo>();

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
                assert !targetFlag.matches(flagRegex) : "The flag \"" + targetFlag + "\" does not match the regex \"" + flagRegex
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

    public List<CommandInfo> register(final Class<?> clazz) throws CommandException {
        return register(clazz, null);
    }

    public List<CommandInfo> register(final Object obj) throws CommandException {
        return register(obj.getClass(), obj);
    }

    public boolean execute(final CommandSender sender, final org.bukkit.command.Command cmd, final String[] bArgs,
            final String label) throws CommandException {
        assert sender != null : "Sender cannot be null!";
        assert cmd != null : "Cmd cannot be null!";
        assert bArgs != null : "BArgs cannot be null!";
        assert label != null : "Label cannot be null!";
        assert label.length() != 0 : "Label cannot be empty!";

        if (!cmds.containsKey(label)) {
            throw new CommandException("The given command \"" + label + "\" is not registered.");
        }

        boolean executed = false;

        List<CommandEvent<?>> events = new ArrayList<CommandEvent<?>>();

        for (CommandInfo target : cmds.get(label)) {
            Command cmdAnot = target.getCommandAnot();

            RawCommandContext rawContext = new RawCommandContext(target, sender, cmd, label, bArgs);

            if (!PermissionUtil.hasPermission(sender, cmdAnot.permission())) {
                events.add(new NoPermissionCommandEvent(rawContext));
                continue;
            }

            CommandSyntax syntax = target.getSyntax();

            int min = syntax.isNull() ? cmdAnot.min() : syntax.size();
            int max = syntax.isNull() ? cmdAnot.max() : (syntax.endsWithVarArg() ? -1 : syntax.size());

            List<Tag<?>> args = parseFlags(target, bArgs).getArgs();

            if (args.size() < min) {
                events.add(new NotEnoughArgumentsCommandEvent(rawContext));
                continue;
            }

            if (max != -1 && args.size() > max) {
                events.add(new TooManyArgumentsCommandEvent(rawContext));
                continue;
            }

            ContextData contextData = parseCommand(target, bArgs);

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

        for (CommandEvent<?> target : events) {
            target.setCancelled(executed);
            Bukkit.getServer().getPluginManager().callEvent(target);
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

    private ContextData parseFlags(final CommandInfo cmd, final List<String> rawArgs) {
        assert cmd != null : "Cmd cannot be null!";
        assert rawArgs != null : "RawArgs cannot be null!";

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
                if (rawArgs.contains(argFlag)) {
                    int valueIndex = rawArgs.indexOf(argFlag);

                    if (valueIndex < rawArgs.size()) {
                        flags.put(flagIndex, new Tag<String>(rawArgs.get(valueIndex)));
                        found = true;
                    }
                }
            } else {
                found = rawArgs.contains(argFlag);

                flags.put(flagIndex, new Tag<Boolean>(found));
            }

            if (found) {
                while (rawArgs.contains(argFlag)) {
                    rawArgs.remove(argFlag);
                }
            }
        }

        return new ContextData((String[]) rawArgs.toArray(), flags);
    }

    private ContextData parseFlags(final CommandInfo cmd, final String[] args) {
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
                args.add(new Tag<String>(targetArg));
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
                case STRING:
                    args.add(new Tag<String>(rawArgs.get(i).trim()));
                    break;
                case STRING_VARARG:
                    args.add(new Tag<String>(StringUtil.joinString(i, "", rawArgs)));
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

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        return execute(sender, cmd, args, label);
    }

    public static CommandManager getInstance() {
        if (instance == null) {
            return instance = new CommandManager();
        }

        return instance;
    }
}
