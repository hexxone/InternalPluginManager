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

import com.blockhaus2000.commands.test.TestCommands;
import com.blockhaus2000.main.bukkit.IpmMain;
import com.blockhaus2000.minecraft.util.command.Command;
import com.blockhaus2000.util.ChatOut;
import com.blockhaus2000.util.command.CommandContext;

/**
 *
 * @author Blockhaus2000
 */
@SuppressWarnings("javadoc")
public class Commands {
    @Command(aliases = { "internalpluginmanager", "ipm" },
             desc = "Toggles the debug-mode.",
             secondLevelCommand = "debug",
             permission = "internalpluginmanager.ipm.debug")
    public void debug(final CommandContext context) {
        if (IpmMain.DEBUGGING_ENABLED) {
            ChatOut.sendMessage(context.getSender(), "Debug mode " + (TestCommands.toggleEnabled() ? "enabled" : "disabled")
                    + "!");
            ChatOut.sendMessage(context.getSender(), TestCommands.SUCCEED);
        } else {
            ChatOut.sendMessage(context.getSender(),
                    "Cannot toggle debug mode, because it was disable global with the system property "
                            + "\"internalpluginmanager.debug-mode\"!");
            ChatOut.sendMessage(context.getSender(), TestCommands.FAILED);
        }
    }
}
