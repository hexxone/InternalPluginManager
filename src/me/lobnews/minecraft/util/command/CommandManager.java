/*
 * This file is part of RedstoneWorldManager
 * 
 */
package me.lobnews.minecraft.util.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.lobnews.util.Tag;
import me.lobnews.util.command.CommandContext;
import me.lobnews.util.command.CommandInfo;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * 
 * @author Blockhaus2000
 */
public class CommandManager implements CommandExecutor {
    private static CommandManager instance;

    private final Map<String, ArrayList<CommandInfo>> cmds = new HashMap<>();

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

            for (String targetAlias : targetMethod.getAnnotation(Command.class).aliases()) {
                if (targetAlias.equals("")) {
                    continue;
                }

                if (!cmds.containsKey(targetAlias)) {
                    cmds.put(targetAlias, new ArrayList<CommandInfo>());
                }

                cmds.get(targetAlias).add(new CommandInfo(targetMethod.getAnnotation(Command.class), clazz, obj, targetMethod));
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

        for (CommandInfo target : cmds.get(label)) {
            Map<ContextDataType, ArrayList<Tag<?>>> contextData = parseCommandArguments(bArgs);
            List<Tag<?>> args = contextData.get(ContextDataType.ARGS);
            List<Tag<?>> flags = contextData.get(ContextDataType.FLAGS);

            CommandContext context = new CommandContext(target, cmd, sender, bArgs, label, args, flags);

            try {
                target.getMethod().invoke(target.getObject(), context);
                executed = true;
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return executed;
    }

    private Map<ContextDataType, ArrayList<Tag<?>>> parseCommandArguments(final List<String> args) {
        return null; // TODO
    }

    private Map<ContextDataType, ArrayList<Tag<?>>> parseCommandArguments(final String... args) {
        return parseCommandArguments(Arrays.asList(args));
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
