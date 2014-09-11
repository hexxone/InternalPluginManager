/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014 Fabian Damken
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.blockhaus2000.ipm.technical.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation is used to tag methods, so they will be detected from the
 * {@link CommandManager} to register the implemented commands.
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    /**
     * The aliases for the command. The command will be registered with all of
     * these alisaes, and you can execute with all of these.
     *
     * @return The {@link String}[] of the aliases.
     */
    String[] aliases();

    /**
     * The permission that is used for this command. If the executer does not
     * have the permission, a
     * {@link com.blockhaus2000.ipm.technical.command.event.CommandEvent} will
     * be fired with the
     * {@link com.blockhaus2000.ipm.technical.command.event.CommandEventData.CommandEventType}
     * {@link com.blockhaus2000.ipm.technical.command.event.CommandEventData.CommandEventType#NO_PERMISSION}
     * and the method will not be invoked. If the permission is an empty
     * {@link String}, no permission will be used.
     *
     * <p>
     * Default: <code>""</code>
     * </p>
     *
     * @return The permission for this command.
     */
    String permission() default "";

    /**
     * The minimum arguments for this command. If a uses enter not enough
     * arguments, a
     * {@link com.blockhaus2000.ipm.technical.command.event.CommandEvent} will
     * be fired with the
     * {@link com.blockhaus2000.ipm.technical.command.event.CommandEventData.CommandEventType}
     * {@link com.blockhaus2000.ipm.technical.command.event.CommandEventData.CommandEventType#NOT_ENOUGH_ARGUMENTS}
     * and the method will not be invoked.
     *
     * <p>
     * Default: <code>0</code>
     * </p>
     *
     * @return The minimum arguments of this command.
     */
    int min() default 0;

    /**
     * The maximal arguments for this command. If a uses enter too many
     * arguments, a
     * {@link com.blockhaus2000.ipm.technical.command.event.CommandEvent} will
     * be fired with the
     * {@link com.blockhaus2000.ipm.technical.command.event.CommandEventData.CommandEventType}
     * {@link com.blockhaus2000.ipm.technical.command.event.CommandEventData.CommandEventType#TOO_MANY_ARGUMENTS}
     * and the method will not be invoked. If it is <code>-1</code>, you can
     * enter infinite arguments.
     *
     * <p>
     * Default: <code>-1</code>
     * </p>
     *
     * @return The minimum arguments of this command.
     */
    int max() default -1;

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
    CommandPriority priority() default CommandPriority.NORMAL;

    /**
     * The flags for this command. Flags are an efficient way to allow toggles
     * in commands (For example, a user uses <code>-h</code> in his command, the
     * flag will be setted to <code>true</code>). Value flags are also
     * supported. You can enable them with a <code>:</code> after the flag name.
     * If the value is not available, a
     * {@link com.blockhaus2000.ipm.technical.command.event.CommandEvent} will
     * be fired with the
     * {@link com.blockhaus2000.ipm.technical.command.event.CommandEventData.CommandEventType}
     * {@link com.blockhaus2000.ipm.technical.command.event.CommandEventData.CommandEventType#UNAVAILABLE_FLAG_VALUE}
     * . If you use value flags, you have to enter the type of the flag data
     * that is expected (like {@link Double}). Write the type after the
     * <code>:</code>, like <code>p:string</code>. These types are available:
     * <ul>
     * <li>String, write <code>a:string</code></li>
     * <li>Long, write <code>a:long</code></li>
     * <li>Double, write <code>a:double</code></li>
     * <li>String_VarArg, write <code>string_vararg</code></li>
     * </ul>
     * If the type is inconstistent, a
     * {@link com.blockhaus2000.ipm.technical.command.event.CommandEvent} with
     * the
     * {@link com.blockhaus2000.ipm.technical.command.event.CommandEventData.CommandEventType}
     * {@link com.blockhaus2000.ipm.technical.command.event.CommandEventData.CommandEventType#INCONSTISTENT_FLAG_VALUE}
     * will be fired.
     *
     * <p>
     * <b> NOTE: Flags have to match the regex
     * {@link com.blockhaus2000.ipm.technical.command.util.Constants#FLAG_REGEX}
     * !</b>
     * </p>
     *
     * @return A {@link String}[] of flags for this command.
     */
    String[] flags() default "";

    /**
     * This is the syntax of the command. The syntax should sepcify the
     * type-usage for this command (for example
     * <code>String Long String_VarArg</code>). The {@link CommandManager}
     * checks for you, that there are enough arguments (otherwise, it should
     * call an event) and parse the argument Strings into Longs or Doubles. With
     * VarArgs, the {@link CommandManager} joins the next arguments to a long
     * String. You can seperate the types with a blank or a comma and a blank (
     * <code>, </code>). You can also use multiple commas or blanks. It doesnt
     * effect the delimiter system. The delimiter only have to match the regex
     * <code>,* +</code>. Every other delimiter will build an unavailable type.
     *
     * <p>
     * <b> NOTE: If you use this feature, the min/max settings are fully
     * ignored! </b>
     * </p>
     * <p>
     * <b> NOTE: A string vararg is only allowed at the end. </b>
     * </p>
     * <p>
     * <b> NOTE: The syntax has to match the regex
     * {@link com.blockhaus2000.ipm.technical.command.util.Constants#SYNTAX_REGEX}
     * ! </b>
     * </p>
     *
     * @return The syntax for this command.
     */
    String syntax() default "";

    /**
     * This is the second-level-command of the command. So the command method
     * calling will be extended so the second-level-command has to equals the
     * second arguments of the executed command. (for example, the second level
     * command is <code>all</code> and one of the command alisas is
     * <code>kill</code>, <code>/kill all</code> has to be executed to invoke
     * the method that is tagged with this annotation).
     *
     * <p>
     * <b> NOTE: This will not effect the max. argument setting, the min.
     * argument setting or the command syntax setting! The second-level-command
     * will be removed completly. </b>
     * </p>
     *
     * @return The second-level-command for this command.
     */
    String secondLevelCommand() default "";

    /**
     * Enables the auto maximal argument setting if you use a specific command
     * syntax (specified in {@link Command#syntax()}).
     *
     * @return The boolean value that auto maximal setting on syntax
     *         specification is enabled.
     * @see Command#syntax()
     */
    boolean autoSetMaxOnSyntax() default true;
}
