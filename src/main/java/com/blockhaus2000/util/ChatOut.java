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
package com.blockhaus2000.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * This class is a utility class to simplify the message system.
 *
 * @author Blockhaus2000
 */
public class ChatOut {
    private static Logger logger = Bukkit.getServer().getLogger();

    /**
     * Logs the given message with the given {@link Level}.
     *
     * @param level
     *            The {@link Level} that has to be used for logging.
     * @param msg
     *            The message that has to be logged.
     */
    public static synchronized <T> void log(final Level level, final T msg) {
        ChatOut.logger.log(level, msg.toString());
    }

    /**
     * Logs the given message with the log level {@link Level#INFO}.
     *
     * @param msg
     *            The message that has to be logged.
     */
    public static synchronized <T> void log(final T msg) {
        ChatOut.log(Level.INFO, msg);
    }

    /**
     * Logs the given {@link Collection} with the given {@link Level}.
     *
     * <p>
     * <b> NOTE: Every entry of the given {@link Collection} will be logged
     * alone. To join the messages, use
     * {@link StringUtil#joinString(String, java.util.List)}. </b>
     * </p>
     *
     * @param level
     *            The {@link Level} that has to be used for logging.
     * @param msg
     *            The message that has to be logged.
     */
    public static synchronized <T> void log(final Level level, final Collection<T> msg) {
        for (T target : msg) {
            ChatOut.log(level, target);
        }
    }

    /**
     * Logs the given {@link Collection} with the log level {@link Level#INFO}.
     *
     * <p>
     * <b> NOTE: Every entry of the given {@link Collection} will be logged
     * alone. To join the messages, use
     * {@link StringUtil#joinString(String, java.util.List)}. </b>
     * </p>
     *
     * @param msg
     *            The message that has to be logged.
     */
    public static synchronized <T> void log(final Collection<T> msg) {
        ChatOut.log(Level.INFO, msg);
    }

    /**
     * Logs the given array (implemented with var args) with the given
     * {@link Level}.
     *
     * <p>
     * <b> NOTE: Every entry of the given array will be logged alone. To join
     * the messages, use {@link StringUtil#joinString(String, String...)}. </b>
     * </p>
     *
     * @param level
     *            The {@link Level} that has to be used for logging.
     * @param msg
     *            The message that has to be logged.
     */
    public static synchronized <T> void log(final Level level, final T... msg) {
        ChatOut.log(level, Arrays.asList(msg));
    }

    /**
     * Logs the given array (implemented with var args) with the log level
     * {@link Level#INFO}.
     *
     * <p>
     * <b> NOTE: Every entry of the given array will be logged alone. To join
     * the messages, use {@link StringUtil#joinString(String, String...)}. </b>
     * </p>
     *
     * @param msg
     *            The message that has to be logged.
     */
    public static synchronized <T> void log(final T... msg) {
        ChatOut.log(Level.INFO, msg);
    }

    /**
     * Sends the given message to the given {@link CommandSender}. If the
     * {@link CommandSender} is an {@link ConsoleCommandSender} , this will log
     * the given message.
     *
     * @param sender
     *            The {@link CommandSender} that has to receive the message.
     * @param msg
     *            The message that has to be sended.
     */
    public static synchronized <T> void sendMessage(final CommandSender sender, final T msg) {
        if (!(sender instanceof Player)) {
            ChatOut.log(msg);
            return;
        }

        ((Player) sender).sendMessage(msg.toString());
    }

    /**
     * Sends the given message to the given {@link CommandSender}. If the
     * {@link CommandSender} is an {@link ConsoleCommandSender} , this will log
     * the given message.
     *
     * <p>
     * <b> NOTE: Every entry of the given {@link Collection} will be sended
     * alone. To join the messages, use
     * {@link StringUtil#joinString(String, String...)}. </b>
     * </p>
     *
     * @param sender
     *            The {@link CommandSender} that has to receive the message.
     * @param msg
     *            The message that has to be sended.
     */
    public static synchronized <T> void sendMessage(final CommandSender sender, final Collection<T> msg) {
        for (T target : msg) {
            ChatOut.sendMessage(sender, target);
        }
    }

    /**
     * Sends the given message to the given {@link CommandSender}. If the
     * {@link CommandSender} is an {@link ConsoleCommandSender} , this will log
     * the given message.
     *
     * <p>
     * <b> NOTE: Every entry of the given array will be sended alone. To join
     * the messages, use {@link StringUtil#joinString(String, String...)}. </b>
     * </p>
     *
     * @param sender
     *            The {@link CommandSender} that has to receive the message.
     * @param msg
     *            The message that has to be sended.
     */
    public static synchronized <T> void sendMessage(final CommandSender sender, final T... msg) {
        ChatOut.sendMessage(sender, Arrays.asList(msg));
    }

    /**
     *
     * @return The {@link Logger} that is used for logging.
     */
    public static Logger getLogger() {
        return ChatOut.logger;
    }

    /**
     *
     * @param logger
     *            The {@link Logger} that has to be used for logging.
     */
    public static void setLogger(final Logger logger) {
        ChatOut.logger = logger;
    }
}
