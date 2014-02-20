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
package com.blockhaus2000.util;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 
 * @author Blockhaus2000
 */
public class ChatOut {
    private static final Logger logger = Bukkit.getServer().getLogger();

    public static <T> void log(final Level level, final T msg) {
        logger.log(level, msg.toString());
    }

    public static <T> void log(final T msg) {
        log(Level.INFO, msg);
    }

    public static <T> void log(final Level level, final List<T> msg) {
        for (T target : msg) {
            log(level, target);
        }
    }

    public static <T> void log(final List<T> msg) {
        log(Level.INFO, msg);
    }

    public static <T> void log(final Level level, final T... msg) {
        log(level, Arrays.asList(msg));
    }

    public static <T> void log(final T... msg) {
        log(Level.INFO, msg);
    }

    public static <T> void sendMessage(final CommandSender sender, final T msg) {
        if (!(sender instanceof Player)) {
            log(msg);
            return;
        }

        ((Player) sender).sendMessage(msg.toString());
    }

    public static <T> void sendMessage(final CommandSender sender, final List<T> msg) {
        for (T target : msg) {
            sendMessage(sender, target);
        }
    }

    public static <T> void sendMessage(final CommandSender sender, final T... msg) {
        sendMessage(sender, Arrays.asList(msg));
    }

    // Getter
    public static Logger getLogger() {
        return logger;
    }
}
