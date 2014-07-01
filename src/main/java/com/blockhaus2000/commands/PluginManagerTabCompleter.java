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
package com.blockhaus2000.commands;

import java.util.ArrayList;
import java.util.List;

import com.blockhaus2000.main.bukkit.InternalPluginManager;
import com.blockhaus2000.minecraft.util.tabcompl.TabCompleter;
import com.blockhaus2000.plugin.IpmPlugin;
import com.blockhaus2000.util.tabcompl.TabCompleterContext;

@SuppressWarnings("javadoc")
public class PluginManagerTabCompleter {
    @TabCompleter(labels = { "internalpluginmanager", "ipm" },
                  secondLevelCommands = { "update", "disable", "enable", "reload" })
    public List<String> general(final TabCompleterContext context) {
        final List<String> result = new ArrayList<String>();

        final String[] args = context.getArgs();
        final String searchString = args[args.length - 1].toLowerCase().trim();
        for (IpmPlugin plugin : InternalPluginManager.getServer().getPluginManager().getPlugins()) {
            if (plugin.getName().toLowerCase().startsWith(searchString)) {
                result.add(plugin.getName());
            }
        }

        return result;
    }
}
