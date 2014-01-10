/*
 * This file is part of RedstoneWorldManager
 * 
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

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.blockhaus2000.minecraft.util.command.event.CommandEvent;
import com.blockhaus2000.minecraft.util.command.event.NoPermissionCommandEvent;
import com.blockhaus2000.minecraft.util.command.event.NotEnoughArgumentsCommandEvent;
import com.blockhaus2000.minecraft.util.command.event.TooManyArgumentsCommandEvent;
import com.blockhaus2000.util.PermissionUtil;
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

    public void register(final Class<?> clazz, final Object obj) throws CommandException {
        for (Method targetMethod : clazz.getMethods()) {
            if (!targetMethod.isAnnotationPresent(Command.class)) {
                continue;
            }

            if (!Modifier.isStatic(targetMethod.getModifiers()) && obj == null) {
                throw new CommandException("The method " + targetMethod + " is non-static and the given Object is null.");
            }

            if (!targetMethod.getParameterTypes().equals(new Class<?>[] { CommandContext.class })) {
                throw new CommandException("The arguments of the method " + targetMethod
                        + " ar not correct. They only argument has to be \"CommandContext\"");
            }

            String[] flags = targetMethod.getAnnotation(Command.class).flags();
            if (flags.length != 0) {
                for (String targetFlag : flags) {
                    if (!targetFlag.matches(flagRegex)) {
                        throw new CommandException("The flag \"" + targetFlag + "\" does not match the regex \"" + flagRegex
                                + "\"!");
                    }
                }
            }

            for (String targetAlias : targetMethod.getAnnotation(Command.class).aliases()) {
                if (targetAlias.equals("")) {
                    continue;
                }

                if (!cmds.containsKey(targetAlias)) {
                    cmds.put(targetAlias, new ArrayList<CommandInfo>());
                }

                Command cmdAnot = targetMethod.getAnnotation(Command.class);
                cmds.get(targetAlias)
                        .add(new CommandInfo(cmdAnot, clazz, obj, targetMethod, new CommandSyntax(cmdAnot.syntax())));
                Collections.sort(cmds.get(targetAlias));
            }
        }
    }

    public void register(final Class<?> clazz) throws CommandException {
        register(clazz, null);
    }

    public void register(final Object obj) throws CommandException {
        register(obj.getClass(), obj);
    }

    public boolean execute(final CommandSender sender, final org.bukkit.command.Command cmd, final String[] bArgs,
            final String label) throws CommandException {
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

            int min = syntax == null ? cmdAnot.min() : syntax.size();
            int max = syntax == null ? cmdAnot.max() : syntax.size();

            if (bArgs.length < min) {
                events.add(new NotEnoughArgumentsCommandEvent(rawContext));
                continue;
            }

            if (max != -1 && bArgs.length > max) {
                events.add(new TooManyArgumentsCommandEvent(rawContext));
                continue;
            }

            ContextData contextData = parseCommandArguments(target, bArgs);

            try {
                target.getMethod().invoke(target.getObject(),
                        new CommandContext(rawContext, contextData.getArgs(), contextData.getFlags()));
                executed = true;
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        for (CommandEvent<?> target : events) {
            target.setExecuted(executed);
            Bukkit.getServer().getPluginManager().callEvent(target);
        }

        return executed;
    }

    private ContextData parseCommandArguments(final CommandInfo cmd, final List<String> rawArgs) {
        Validate.notNull(cmd, "Cmd cannot be null!");
        Validate.notNull(rawArgs, "RawArgs cannot be null!");

        Map<Character, Tag<?>> flags = null;

        flags = new HashMap<Character, Tag<?>>();

        for (String target : cmd.getCommandAnot().flags()) {
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

        List<Tag<?>> args = new ArrayList<Tag<?>>();

        for (String target : rawArgs) {
            args.add(new Tag<String>(target));
        }

        return new ContextData(args, flags);
    }

    private ContextData parseCommandArguments(final CommandInfo cmd, final String... args) {
        return parseCommandArguments(cmd, Arrays.asList(args));
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
