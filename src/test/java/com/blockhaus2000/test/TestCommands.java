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
package com.blockhaus2000.test;

import com.blockhaus2000.minecraft.util.command.Command;
import com.blockhaus2000.util.Tag;
import com.blockhaus2000.util.command.CommandContext;

/**
 *
 * @author Blockhaus2000
 */
@SuppressWarnings("javadoc")
public class TestCommands {
    @Command(aliases = { "test", "t" },
             desc = "Do something :D",
             permission = "ipmtest.test",
             secondLevelCommand = "sl1",
             flags = { "l", "m:" },
             syntax = "String...")
    public void testCommandOne(final CommandContext context) {
        System.out.println(context.getLabel() + "1");

        System.out.println("Args:");
        for (Tag<?> target : context.getArgs()) {
            System.out.println("<" + target.toString() + ">");
        }

        for (char target : context.getFlags().keySet()) {
            System.out.println(target + ": " + context.getFlags().get(target));
        }
    }

    @Command(aliases = { "test2", "t" },
             desc = "Do something 2 :D",
             secondLevelCommand = "sl2")
    public void testCommand2(final CommandContext context) {
        System.out.println(context.getLabel() + "2");

        System.out.println("Args:");
        for (Tag<?> target : context.getArgs()) {
            System.out.println("<" + target.toString() + ">");
        }

        for (char target : context.getFlags().keySet()) {
            System.out.println(target + ": " + context.getFlags().get(target));
        }
    }
}
