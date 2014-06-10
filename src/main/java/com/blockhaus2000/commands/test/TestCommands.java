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
package com.blockhaus2000.commands.test;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.blockhaus2000.minecraft.util.command.Command;
import com.blockhaus2000.minecraft.util.command.CommandPriority;
import com.blockhaus2000.minecraft.util.command.CommandSenderType;
import com.blockhaus2000.minecraft.util.command.event.CommandEvent;
import com.blockhaus2000.minecraft.util.command.event.CommandEventPackage;
import com.blockhaus2000.util.command.CommandContext;

@SuppressWarnings("javadoc")
public class TestCommands implements Listener {
    public static final String SUCCEED = "[TEST SUCCESSFUL]";
    public static final String FAILED = "[TEST FAILED]";

    private static boolean enabled = false;

    @Command(aliases = { "testcase1", "tc1" },
            permission = "",
            desc = "Testcase 1 for use in unit tests.",
            usage = "In unit tests.",
            min = 0,
            max = -1,
            priority = CommandPriority.NORMAL,
            flags = { "" },
            syntax = "",
            sender = { CommandSenderType.PLAYER, CommandSenderType.CONSOLE, CommandSenderType.COMMAND_BLOCK },
            secondLevelCommand = "",
            autoSetMaxOnSyntax = true,
            help = "No help available!")
    public void testcase1(final CommandContext context) {
        testFor(context);
    }

    @Command(aliases = { "testcase2", "tc2" },
            permission = "",
            desc = "Testcase 2 for use in unit tests.",
            usage = "In unit tests.",
            min = 1,
            max = -1,
            priority = CommandPriority.NORMAL,
            flags = { "" },
            syntax = "",
            sender = { CommandSenderType.PLAYER, CommandSenderType.CONSOLE, CommandSenderType.COMMAND_BLOCK },
            secondLevelCommand = "",
            autoSetMaxOnSyntax = true,
            help = "No help available!")
    public void testcase2(final CommandContext context) {
        testFor(context);
    }

    @Command(aliases = { "testcase3", "tc3" },
            permission = "",
            desc = "Testcase 3 for use in unit tests.",
            usage = "In unit tests.",
            min = 1,
            max = -1,
            priority = CommandPriority.NORMAL,
            flags = { "" },
            syntax = "",
            sender = { CommandSenderType.PLAYER, CommandSenderType.CONSOLE, CommandSenderType.COMMAND_BLOCK },
            secondLevelCommand = "",
            autoSetMaxOnSyntax = true,
            help = "No help available!")
    public void testcase3(final CommandContext context) {
        testFor(context);
    }

    @Command(aliases = { "testcase4", "tc4" },
            permission = "",
            desc = "Testcase 4 for use in unit tests.",
            usage = "In unit tests.",
            min = 0,
            max = 1,
            priority = CommandPriority.NORMAL,
            flags = { "" },
            syntax = "",
            sender = { CommandSenderType.PLAYER, CommandSenderType.CONSOLE, CommandSenderType.COMMAND_BLOCK },
            secondLevelCommand = "",
            autoSetMaxOnSyntax = true,
            help = "No help available!")
    public void testcase4(final CommandContext context) {
        testFor(context);
    }

    @Command(aliases = { "testcase5", "tc5" },
            permission = "",
            desc = "Testcase 5 for use in unit tests.",
            usage = "In unit tests.",
            min = 0,
            max = 1,
            priority = CommandPriority.NORMAL,
            flags = { "" },
            syntax = "",
            sender = { CommandSenderType.PLAYER, CommandSenderType.CONSOLE, CommandSenderType.COMMAND_BLOCK },
            secondLevelCommand = "",
            autoSetMaxOnSyntax = true,
            help = "No help available!")
    public void testcase5(final CommandContext context) {
        testFor(context);
    }

    @Command(aliases = { "testcase6", "tc6" },
            permission = "",
            desc = "Testcase 6 for use in unit tests.",
            usage = "In unit tests.",
            min = 0,
            max = -1,
            priority = CommandPriority.NORMAL,
            flags = { "a", "b:", "c:Integer", "d:String..." },
            syntax = "",
            sender = { CommandSenderType.PLAYER, CommandSenderType.CONSOLE, CommandSenderType.COMMAND_BLOCK },
            secondLevelCommand = "",
            autoSetMaxOnSyntax = true,
            help = "No help available!")
    public void testcase6(final CommandContext context) {
        testFor(context);
    }

    @Command(aliases = { "testcase7", "tc7" },
            permission = "",
            desc = "Testcase 7 for use in unit tests.",
            usage = "In unit tests.",
            min = 0,
            max = -1,
            priority = CommandPriority.NORMAL,
            flags = { "a", "b:", "c:Integer", "d:String..." },
            syntax = "",
            sender = { CommandSenderType.PLAYER, CommandSenderType.CONSOLE, CommandSenderType.COMMAND_BLOCK },
            secondLevelCommand = "",
            autoSetMaxOnSyntax = true,
            help = "No help available!")
    public void testcase7(final CommandContext context) {
        testFor(context);
    }

    @Command(aliases = { "testcase8", "tc8" },
            permission = "",
            desc = "Testcase 8 for use in unit tests.",
            usage = "In unit tests.",
            min = 0,
            max = -1,
            priority = CommandPriority.NORMAL,
            flags = { "a", "b:", "c:Integer", "d:String..." },
            syntax = "",
            sender = { CommandSenderType.PLAYER, CommandSenderType.CONSOLE, CommandSenderType.COMMAND_BLOCK },
            secondLevelCommand = "",
            autoSetMaxOnSyntax = true,
            help = "No help available!")
    public void testcase8(final CommandContext context) {
        testFor(context);
    }

    @Command(aliases = { "testcase9", "tc9" },
            permission = "",
            desc = "Testcase 9 for use in unit tests.",
            usage = "In unit tests.",
            min = 0,
            max = -1,
            priority = CommandPriority.NORMAL,
            flags = { "a", "b:", "c:Integer", "d:String..." },
            syntax = "",
            sender = { CommandSenderType.PLAYER, CommandSenderType.CONSOLE, CommandSenderType.COMMAND_BLOCK },
            secondLevelCommand = "",
            autoSetMaxOnSyntax = true,
            help = "No help available!")
    public void testcase9(final CommandContext context) {
        testFor(context);
    }

    @Command(aliases = { "testcase10", "tc10" },
            permission = "",
            desc = "Testcase 10 for use in unit tests.",
            usage = "In unit tests.",
            min = 0,
            max = -1,
            priority = CommandPriority.NORMAL,
            flags = { "a", "b:", "c:Integer", "d:String..." },
            syntax = "",
            sender = { CommandSenderType.PLAYER, CommandSenderType.CONSOLE, CommandSenderType.COMMAND_BLOCK },
            secondLevelCommand = "",
            autoSetMaxOnSyntax = true,
            help = "No help available!")
    public void testcase10(final CommandContext context) {
        testFor(context);
    }

    @Command(aliases = { "testcase11", "tc11" },
            permission = "",
            desc = "Testcase 11 for use in unit tests.",
            usage = "In unit tests.",
            min = 0,
            max = -1,
            priority = CommandPriority.NORMAL,
            flags = { "a", "b:", "c:Integer", "d:String..." },
            syntax = "",
            sender = { CommandSenderType.PLAYER, CommandSenderType.CONSOLE, CommandSenderType.COMMAND_BLOCK },
            secondLevelCommand = "",
            autoSetMaxOnSyntax = true,
            help = "No help available!")
    public void testcase11(final CommandContext context) {
        testFor(context);
    }

    @Command(aliases = { "testcase12", "tc12" },
            permission = "",
            desc = "Testcase 12 for use in unit tests.",
            usage = "In unit tests.",
            min = 0,
            max = -1,
            priority = CommandPriority.NORMAL,
            flags = { "a", "b:", "c:Integer", "d:String..." },
            syntax = "",
            sender = { CommandSenderType.PLAYER, CommandSenderType.CONSOLE, CommandSenderType.COMMAND_BLOCK },
            secondLevelCommand = "",
            autoSetMaxOnSyntax = true,
            help = "No help available!")
    public void testcase12(final CommandContext context) {
        testFor(context);
    }

    @Command(aliases = { "testcase13", "tc13" },
            permission = "",
            desc = "Testcase 13 for use in unit tests.",
            usage = "In unit tests.",
            min = 0,
            max = -1,
            priority = CommandPriority.NORMAL,
            flags = { "" },
            syntax = "String Integer String...",
            sender = { CommandSenderType.PLAYER, CommandSenderType.CONSOLE, CommandSenderType.COMMAND_BLOCK },
            secondLevelCommand = "",
            autoSetMaxOnSyntax = true,
            help = "No help available!")
    public void testcas13(final CommandContext context) {
        testFor(context);
    }

    @Command(aliases = { "testcase14", "tc14" },
            permission = "",
            desc = "Testcase 14 for use in unit tests.",
            usage = "In unit tests.",
            min = 0,
            max = -1,
            priority = CommandPriority.NORMAL,
            flags = { "" },
            syntax = "String Integer String...",
            sender = { CommandSenderType.PLAYER, CommandSenderType.CONSOLE, CommandSenderType.COMMAND_BLOCK },
            secondLevelCommand = "",
            autoSetMaxOnSyntax = true,
            help = "No help available!")
    public void testcase14(final CommandContext context) {
        testFor(context);
    }

    @Command(aliases = { "testcase15", "tc15" },
            permission = "",
            desc = "Testcase 15 for use in unit tests.",
            usage = "In unit tests.",
            min = 0,
            max = -1,
            priority = CommandPriority.NORMAL,
            flags = { "" },
            syntax = "String Integer String...",
            sender = { CommandSenderType.PLAYER, CommandSenderType.CONSOLE, CommandSenderType.COMMAND_BLOCK },
            secondLevelCommand = "",
            autoSetMaxOnSyntax = true,
            help = "No help available!")
    public void testcase15(final CommandContext context) {
        testFor(context);
    }

    @Command(aliases = { "testcase16", "tc16" },
            permission = "",
            desc = "Testcase 16 for use in unit tests.",
            usage = "In unit tests.",
            min = 0,
            max = -1,
            priority = CommandPriority.NORMAL,
            flags = { "" },
            syntax = "String Integer String...",
            sender = { CommandSenderType.PLAYER, CommandSenderType.CONSOLE, CommandSenderType.COMMAND_BLOCK },
            secondLevelCommand = "",
            autoSetMaxOnSyntax = true,
            help = "No help available!")
    public void testcase16(final CommandContext context) {
        testFor(context);
    }

    @EventHandler
    public void onCommandEventPackage(final CommandEventPackage eventPackage) {
        System.out.println(eventPackage);

        if (!TestCommands.enabled) {
            return;
        }

        final CommandEvent<?> event = eventPackage.getEvents().get(0);
        printResult(TestCase.getFromString(event.getCommand().getCommandAnot().aliases()[0]).asExpected(null, event));
    }

    private void testFor(final CommandContext context) {
        if (!TestCommands.enabled) {
            return;
        }

        printResult(TestCase.getFromString(context.getCommandAnot().aliases()[0]).asExpected(context, null));
    }

    private void printResult(final boolean result) {
        System.out.println(result ? TestCommands.SUCCEED : TestCommands.FAILED);
    }

    public static boolean toggleEnabled() {
        return TestCommands.enabled = !TestCommands.enabled;
    }
}
