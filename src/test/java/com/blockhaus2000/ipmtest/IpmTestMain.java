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
package com.blockhaus2000.ipmtest;

import com.blockhaus2000.main.bukkit.IpmMain;
import com.blockhaus2000.minecraft.util.command.Command;
import com.blockhaus2000.plugin.IpmPlugin;
import com.blockhaus2000.util.CommandUtil;
import com.blockhaus2000.util.ChatOut;
import com.blockhaus2000.util.command.CommandContext;
import com.blockhaus2000.util.command.SingleCommand;

/**
 * 
 * @author Blockhaus2000
 */
public class IpmTestMain extends IpmPlugin implements SingleCommand {
    @Override
    public void onDisable() {
        System.out.println("onDisable()");
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onEnable() {
        System.out.println("onEnable()");

        CommandUtil.registerCommands(this, IpmMain.getInstance());
    }

    @Override
    public void onLoad() {
        System.out.println("onLoad()");
    }

    @Command(aliases = { "ipmtest" },
             desc = "This is the first test command!")
    @Override
    public void onCommand(CommandContext context) {
        ChatOut.sendMessage(context.getSender(), "It works!");
    }
}
