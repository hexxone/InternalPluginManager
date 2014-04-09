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
package com.blockhaus2000.main.bukkit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.blockhaus2000.minecraft.util.command.Command;
import com.blockhaus2000.plugin.IpmPlugin;
import com.blockhaus2000.plugin.update.SimpleUpdater;
import com.blockhaus2000.util.ChatOut;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.Tag;
import com.blockhaus2000.util.command.CommandContext;

/**
 *
 * @author Blockhaus2000
 */
public class Commands { // TODO
    @Command(aliases = { "internalpluginmanager", "ipm" },
             desc = "The basic InternalPluginManager command. Type \"/<internalpluginmanager|ipm> help\" for help.",
             secondLevelCommand = "update",
             permission = "internalpluginmanager.ipm.update",
             max = 0)
    @SuppressWarnings("javadoc")
    public void updateAll(final CommandContext context) {
        List<Tag<?>> pluginNames = new ArrayList<Tag<?>>();

        for (IpmPlugin target : InternalPluginManager.getServer().getPluginManager().getPlugins()) {
            pluginNames.add(new Tag<String>(target.getName()));
        }

        update(new CommandContext(context, pluginNames, context.getFlags()));

        ChatOut.sendMessage(context.getSender(), "Updated all plugins to the latest version!");
    }

    @Command(aliases = { "internalpluginmanager", "ipm" },
             desc = "The basic InternalPluginManager command. Type \"/<internalpluginmanager|ipm> help\" for help.",
             secondLevelCommand = "update",
             permission = "internalpluginmanager.ipm.update")
    @SuppressWarnings("javadoc")
    public void update(final CommandContext context) {
        final CommandSender sender = context.getSender();

        for (Tag<?> target : context.getArgs()) {
            final IpmPlugin targetPlugin = InternalPluginManager.getServer().getPluginManager()
                    .getPlugin((String) target.getData());

            if (targetPlugin == null) {
                ChatOut.sendMessage(sender, "Cannot find plugin \"" + (String) target.getData() + "\"!");
                continue;
            }

            final String targetPluginName = targetPlugin.getName();

            try {
                if (!SimpleUpdater.getInstance().hasUpdate(targetPlugin)) {
                    ChatOut.sendMessage(sender, "No update is available for \"" + targetPluginName + "\"!");
                    return;
                }

                SimpleUpdater.getInstance().update(targetPlugin);
            } catch (Exception ex) {
                ChatOut.sendMessage(sender, "Cannot update \"" + targetPluginName + "\". See server log for a full error report.");
                ExceptionHandler.handle(ex);
                continue;
            }

            ChatOut.sendMessage(sender, "Updated \"" + targetPluginName + "\" to the latest version!");
        }
    }

    @Command(aliases = { "internalpluginmanager", "ipm" },
             desc = "The basic InternalPluginManager command. Type \"/<internalpluginmanager|ipm> help\" for help.",
             secondLevelCommand = "help",
             permission = "internalpluginmanager.ipm.help")
    @SuppressWarnings("javadoc")
    public void help(final CommandContext context) {
        ChatOut.sendMessage(context.getSender(), "Not supported yet!"); // TODO
    }
}
