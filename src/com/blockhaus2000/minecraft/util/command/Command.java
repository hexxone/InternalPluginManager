/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.minecraft.util.command;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Blockhaus2000
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    /**
     * The aliases for this {@link Command}. The first is the primary usage.
     * 
     * @return An {@link String}[] of aliases that can be used to execute this
     *         {@link Command}.
     */
    public String[] aliases();

    /**
     * The permission for this {@link Command}. If you didn't input something,
     * there will be no permission.
     * 
     * @return The permission for this {@link Command}.
     */
    public String permission() default "";

    /**
     * A short description. A long description can be added with
     * {@link Command#help()}.
     * 
     * @return A short description for this {@link Command}.
     */
    public String desc();

    /**
     * The {@link Command} usage.
     * 
     * @return The usage of the target {@link Command}.
     */
    public String usage() default "";

    /**
     * The minimum value of Arguments. It has to be >= 0.
     * 
     * @return The minimum value of arguments.
     */
    public int min() default 0;

    /**
     * The maximum value of arguments. -1 means, that there is no argument
     * limit.
     * 
     * @return The maximal value of arguments.
     */
    public int max() default -1;

    /**
     * The {@link CommandPriority} of the target {@link Command}. The default
     * {@link CommandPriority} is {@link CommandPriority#NORMAL}.
     * 
     * @return The {@link CommandPriority} of the target {@link Command}.
     */
    public CommandPriority priority() default CommandPriority.NORMAL;

    /**
     * Flags allow special processing for flags such as -h in the command,
     * allowing users to easily turn on a flag. This is a string with each
     * character being a flag. Use A-Z and a-z as possible flags. Appending a
     * flag with a : makes the flag character before a value flag, meaning that
     * if it is given it must have a value.
     * 
     * @return Flags matching regex "a-zA-Z"
     */
    public String[] flags() default "";

    /**
     * A long description for this {@link Command}.
     * 
     * @return A long description for this {@link Command}.
     */
    public String help() default "";
}
