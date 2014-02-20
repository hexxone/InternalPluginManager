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

import com.blockhaus2000.minecraft.util.command.Command;
import com.blockhaus2000.minecraft.util.command.CommandPriority;
import com.blockhaus2000.util.Tag;
import com.blockhaus2000.util.command.CommandContext;

/**
 * 
 * @author Blockhaus2000
 */
public class TestCommands {
    @Command(aliases = { "ipmtest1", "it1", "it" },
             desc = "IpmTest One",
             priority = CommandPriority.HIGHEST,
             syntax = "String, Integer, String")
    public void testCommandOne(CommandContext context) {
        System.out.println(context.getCommandAnot().desc());

        for (Tag<?> target : context.getArgs()) {
            System.out.println(target);
        }
    }

    @Command(aliases = { "ipmtest2", "it2", "it" },
             desc = "IpmTest Two",
             priority = CommandPriority.LOWEST,
             min = 1,
             max = 2)
    public static void testCommandTwo(CommandContext context) {
        System.out.println(context.getCommandAnot().desc());
    }
}
