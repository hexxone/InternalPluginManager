/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.util.command;

import java.lang.reflect.Method;

import com.blockhaus2000.minecraft.util.command.Command;

/**
 * 
 * @author Blockhaus2000
 */
public class CommandInfo implements Comparable<CommandInfo> {
    private final Command commandAnot;
    private final Class<?> clazz;
    private final Object object;
    private final Method method;
    private final CommandSyntax syntax;

    public CommandInfo(final Command command, final Class<?> clazz, final Object object, final Method method,
            final CommandSyntax syntax) {
        this.commandAnot = command;
        this.clazz = clazz;
        this.object = object;
        this.method = method;
        this.syntax = syntax;
    }

    public Command getCommandAnot() {
        return commandAnot;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public Object getObject() {
        return object;
    }

    public Method getMethod() {
        return method;
    }

    public CommandSyntax getSyntax() {
        return syntax;
    }

    @Override
    public int compareTo(CommandInfo that) {
        final int levelThis = this.getCommandAnot().priority().getLevel();
        final int levelThat = that.getCommandAnot().priority().getLevel();

        if (levelThis == levelThat) {
            return 0;
        }

        return levelThis < levelThat ? -1 : 1;
    }
}
