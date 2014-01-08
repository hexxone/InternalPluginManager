/*
 * This file is part of RedstoneWorldManager
 * 
 */
package me.lobnews.util.command;

import java.lang.reflect.Method;

import me.lobnews.minecraft.util.command.Command;

/**
 * 
 * @author Blockhaus2000
 */
public class CommandInfo implements Comparable<CommandInfo> {
    private Command commandAnot;
    private Class<?> clazz;
    private Object object;
    private Method method;

    public CommandInfo(final Command command, final Class<?> clazz, final Object object, final Method method) {
        this.commandAnot = command;
        this.clazz = clazz;
        this.object = object;
        this.method = method;
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
