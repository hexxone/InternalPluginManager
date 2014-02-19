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

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * 
 * @author Blockhaus2000
 */
public class PluginUtil {
    public static void disable(final Plugin plugin, final List<String> messages) {
        Bukkit.getServer().getPluginManager().disablePlugin(plugin);
        sendMessages(messages);
    }

    public static void disable(final Plugin plugin) {
        disable(plugin, null);
    }

    public static void enable(final Plugin plugin, final List<String> messages) {
        Bukkit.getServer().getPluginManager().enablePlugin(plugin);
        sendMessages(messages);
    }

    public static void enable(final Plugin plugin) {
        enable(plugin, null);
    }

    private static void sendMessages(final List<String> messages) {
        if (messages == null) {
            return;
        }

        OutputStream.log(messages);
    }
}
