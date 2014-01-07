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
public class CommandSyntax implements Comparable<CommandSyntax> {
    private Command command;
    private Class<?> clazz;
    private Object object;
    private Method method;

    public CommandSyntax(final Command command, final Class<?> clazz, final Object object, final Method method) {
        this.command = command;
        this.clazz = clazz;
        this.object = object;
        this.method = method;
    }

    public Command getCommand() {
        return command;
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
    public int compareTo(CommandSyntax that) {
        final int levelThis = this.getCommand().priority().getLevel();
        final int levelThat = that.getCommand().priority().getLevel();

        if (levelThis == levelThat) {
            return 0;
        }

        return levelThis < levelThat ? -1 : 1;
    }
}
