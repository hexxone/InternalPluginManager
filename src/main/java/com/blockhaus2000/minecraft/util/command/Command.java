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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.blockhaus2000.minecraft.util.command.event.IllegalSyntaxCommandEvent;
import com.blockhaus2000.minecraft.util.command.event.IllegalSyntaxType;
import com.blockhaus2000.minecraft.util.command.event.NoPermissionCommandEvent;
import com.blockhaus2000.minecraft.util.command.event.NotEnoughArgumentsCommandEvent;
import com.blockhaus2000.minecraft.util.command.event.TooManyArgumentsCommandEvent;

/**
 * The annotation is used to tag methods, so they will be detected from the
 * {@link CommandManager} to register the implemented commands.
 * 
 * @author Blockhaus2000
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    /**
     * The aliases for the command. The command will be registered with all of
     * these alisaes, and you can execute with all of these.
     * 
     * @return The {@link String}[] of the aliases.
     */
    public String[] aliases();

    /**
     * The permission that is used for this command. If the executer does not
     * have the permission, a {@link NoPermissionCommandEvent} will be fired and
     * the method will not be invoked. If the permission is an empty
     * {@link String}, no permission will be used.
     * 
     * <p>
     * Default: <code>""</code>
     * </p>
     * 
     * @return The permission for this command.
     */
    public String permission() default "";

    /**
     * A short description of this command. Will be shown in "/help".
     * 
     * @return A short description for this command.
     */
    public String desc();

    /**
     * The usage of this command. it can be "/commands arg0 arg1 arg2" or
     * something like this.
     * 
     * <p>
     * Default: <code>""</code>
     * </p>
     * 
     * @return The usage of this command.
     */
    public String usage() default "";

    /**
     * The minimum arguments for this command. If a uses enter not enough
     * arguments, a {@link NotEnoughArgumentsCommandEvent} will be fired and the
     * method will not be invoked.
     * 
     * <p>
     * Default: <code>0</code>
     * </p>
     * 
     * @return The minimum arguments of this command.
     */
    public int min() default 0;

    /**
     * The maximal arguments for this command. If a uses enter too many
     * arguments, a {@link TooManyArgumentsCommandEvent} will be fired and the
     * method will not be invoked. If it is <code>-1</code>, you can enter
     * infinite arguments.
     * 
     * <p>
     * Default: <code>-1</code>
     * </p>
     * 
     * @return The minimum arguments of this command.
     */
    public int max() default -1;

    /**
     * The {@link CommandPriority} for this command. If some commands have a
     * same alias, the command with the highest priority will be executed first.
     * 
     * <p>
     * Default: {@link CommandPriority#NORMAL}
     * </p>
     * 
     * @return The priority of this command.
     */
    public CommandPriority priority() default CommandPriority.NORMAL;

    /**
     * The flags for this command. Flags are an efficient way to allow toggles
     * in commands (For example, a user uses <code>-h</code> in his command, the
     * flag will be setted to <code>true</code>). Value flags are also
     * supported. You can enable the with a <code>:</code> after the flag name.
     * If the value is not available, a {@link IllegalSyntaxCommandEvent} will
     * be thrown with the type {@link IllegalSyntaxType#UNAVAILABLE_FLAG_VALUE}.
     * 
     * <p>
     * <b> NOTE: Flags have to matches the regex <code>[A-Za-z]:?</code>. </b>
     * </p>
     * 
     * @return A {@link String}[] of flags for this command.
     */
    public String[] flags() default "";

    /**
     * This is the syntax of the command. The syntax should sepcify the
     * type-usage for this command (for example
     * <code>String Integer String...</code>). The {@link CommandManager} checks
     * for you, that there are enough arguments (otherwise, it should call an
     * event) and parse the argument Strings into Integers or Doubles. With
     * VarArgs, the {@link CommandManager} joins the next arguments to a long
     * String. You can seperate the types with a blank (<code> </code>) or a
     * comma and a blank (<code>, </code>). You can also use multiple commas or
     * blanks. It doesnt effect the delimiter system. The delimiter only have to
     * match the regex <code>,* +</code>. Every other delimiter will build an
     * unavailable type.
     * 
     * <p>
     * <b> NOTE: If you use this feature, the min/max settings are fully
     * ignored! </b>
     * </p>
     * 
     * @return The syntax for this command.
     */
    public String syntax() default "";

    /**
     * This is the second-level-command of the command. So the command method
     * calling will be extended so the second-level-command has to equals the
     * second arguments of the executed command. (for example, this is
     * <code>all</code> and one of the command alisas is <code>kill</code>,
     * <code>/kill all</code> has to be executed o enter the method that is
     * tagged with this annotation).
     * 
     * <p>
     * <b> NOTE: This will not effect the max. argument setting, the min.
     * argument setting or the command syntax setting! The second-level-command
     * will be removed completly. </b>
     * </p>
     * 
     * @return The second-level-command for this command.
     */
    public String secondLevelCommand() default "";

    /**
     * Enables the auto maximal setting if you use a specific command syntax
     * (sepcified in {@link Command#syntax()}).
     * 
     * @return The boolean value that auto maximal setting on syntax
     *         specification is enabled.
     * @see Command#syntax()
     */
    public boolean autoSetMaxOnSyntax() default true;

    /**
     * A long help for this command.
     * 
     * @return The long help of this command.
     */
    public String help() default "";
}
