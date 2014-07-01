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

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.blockhaus2000.main.bukkit.InternalPluginManager;
import com.blockhaus2000.minecraft.util.command.Command;
import com.blockhaus2000.plugin.IpmPlugin;
import com.blockhaus2000.util.ChatOut;
import com.blockhaus2000.util.StringUtil;
import com.blockhaus2000.util.command.CommandContext;

/**
 * This class provides some command for the integrated, disableble plugin
 * manager.
 *
 * @author Blockhaus2000
 */
@SuppressWarnings("javadoc")
public class PluginManagerCommands {
    // Updates all plugins (!!! NOT WORKING YET !!!).
    @Command(aliases = { "internalpluginmanager", "ipm" },
             desc = "Updates all plugins to the latest available version.",
             usage = "/<internalpluginmanager|ipm> update",
             secondLevelCommand = "update",
             permission = "internalpluginmanager.ipm.update",
             max = 0)
    public void updateAll(final CommandContext context) { // TODO
        ChatOut.sendMessage(context.getSender(), "Not supported yet!");

        // List<Tag<?>> pluginNames = new ArrayList<Tag<?>>();
        //
        // for (IpmPlugin target :
        // InternalPluginManager.getServer().getPluginManager().getPlugins()) {
        // pluginNames.add(new Tag<String>(target.getName()));
        // }
        //
        // update(new CommandContext(context, pluginNames, context.getFlags()));
        //
        // ChatOut.sendMessage(context.getSender(),
        // "Updated all plugins to the latest version!");
    }

    // Cast to List<String>
    @Command(aliases = { "internalpluginmanager", "ipm" },
             desc = "Updates the specified plugins to the latest available versions.",
             usage = "/<internalpluginmanager|ipm> update <plugins...>",
             secondLevelCommand = "update",
             permission = "internalpluginmanager.ipm.update",
             syntax = "String...")
    public void update(final CommandContext context) { // TODO
        ChatOut.sendMessage(context.getSender(), "Not supported yet!");

        // final CommandSender sender = context.getSender();
        //
        // for (String target : (List<String>)
        // context.getArgs().get(0).getData()) {
        // final IpmPlugin targetPlugin =
        // InternalPluginManager.getServer().getPluginManager().getPlugin(target);
        //
        // if (targetPlugin == null) {
        // ChatOut.sendMessage(sender, "Cannot find plugin \"" + target +
        // "\"!");
        // continue;
        // }
        //
        // final String targetPluginName = targetPlugin.getName();
        //
        // try {
        // if (!SimpleUpdater.getInstance().hasUpdate(targetPlugin)) {
        // ChatOut.sendMessage(sender, "No update is available for \"" +
        // targetPluginName + "\"!");
        // return;
        // }
        //
        // SimpleUpdater.getInstance().update(targetPlugin);
        // } catch (Exception ex) {
        // ChatOut.sendMessage(sender, "Cannot update \"" + targetPluginName +
        // "\". See server log for a full error report.");
        // ExceptionHandler.handle(ex);
        // continue;
        // }
        //
        // ChatOut.sendMessage(sender, "Updated \"" + targetPluginName +
        // "\" to the latest version!");
        // }
    }

    @Command(aliases = { "internalpluginmanager", "ipm" },
             desc = "Lists all loaded plugins.",
             usage = "/<internalpluginmanager|ipm> list",
             secondLevelCommand = "list",
             permission = "internalpluginmanager.ipm.list")
    public void list(final CommandContext context) {
        final StringBuffer buffer = new StringBuffer("Loaded Plugins: ");
        for (IpmPlugin target : InternalPluginManager.getServer().getPluginManager().getPlugins()) {
            buffer.append((target.isEnabled() ? ChatColor.GREEN : ChatColor.RED) + target.getName() + ChatColor.WHITE + ", ");
        }

        ChatOut.sendMessage(context.getSender(), StringUtil.replaceLast(",", "", buffer.toString()));
    }

    @Command(aliases = { "internalpluginmanager", "ipm" },
             desc = "Disables all given plugins.",
             usage = "/<internalpluginmanager|ipm> disable <plugins...>",
             secondLevelCommand = "disable",
             permission = "internalpluginmanager.ipm.disable",
             syntax = "String...")
    public void disable(final CommandContext context) {
        doEnableDisableAction(context, EnableDisableAction.DISABLE);
    }

    @Command(aliases = { "internalpluginmanager", "ipm" },
             desc = "Enables all given plugins.",
             usage = "/<internalpluginmanager|ipm> enable <plugins...>",
             secondLevelCommand = "enable",
             permission = "internalpluginmanager.ipm.enabled",
             syntax = "String...")
    public void enable(final CommandContext context) {
        doEnableDisableAction(context, EnableDisableAction.ENABLE);
    }

    @Command(aliases = { "internalpluginmanager", "ipm" },
             desc = "Reloads (soft or hard) all given plugins.",
             usage = "/<internalpluginmanager|ipm> reload [-h: Hard Reload] <plugins...>",
             secondLevelCommand = "reload",
             permission = "internalpluginmanager.ipm.reload",
             syntax = "String...",
             flags = "h")
    public void reload(final CommandContext context) {
        if ((Boolean) context.getFlags().get('h').getData()) {
            doEnableDisableAction(context, EnableDisableAction.RELOAD_HARD);
        } else {
            doEnableDisableAction(context, EnableDisableAction.RELOAD_SOFT);
        }
    }

    private void doEnableDisableAction(final CommandContext context, final EnableDisableAction type) {
        final CommandSender sender = context.getSender();

        for (String pluginName : ((String) context.getArgs().get(0).getData()).split(" ")) {
            final IpmPlugin plugin = InternalPluginManager.getServer().getPluginManager().getPlugin(pluginName);

            if (plugin == null) {
                ChatOut.sendMessage(sender, "Cannot find plugin \"" + pluginName + "\"!");
                continue;
            }

            switch (type) {
            case DISABLE:
                InternalPluginManager.getServer().getPluginManager().disable(plugin);
                break;
            case ENABLE:
                InternalPluginManager.getServer().getPluginManager().enable(plugin);
                break;
            case RELOAD_SOFT:
                InternalPluginManager.getServer().getPluginManager().softReload(plugin);
                break;
            case RELOAD_HARD:
                InternalPluginManager.getServer().getPluginManager().reload(plugin);
                break;
            }

            ChatOut.sendMessage(sender, "Successfully " + type.getString() + " \"" + plugin.getName() + "\"");
        }
    }

    private static enum EnableDisableAction {
        ENABLE("enabled"),
        DISABLE("disabled"),
        RELOAD_SOFT("reloaded"),
        RELOAD_HARD("hard reloaded");

        private final String str;

        private EnableDisableAction(final String str) {
            this.str = str;
        }

        public String getString() {
            return str;
        }
    }
}
