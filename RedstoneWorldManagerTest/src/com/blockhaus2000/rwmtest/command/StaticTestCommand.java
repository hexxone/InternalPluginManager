/*
 * This file is part of RedstoneWorldManagerTest
 * 
 */
package com.blockhaus2000.rwmtest.command;

import com.blockhaus2000.minecraft.util.command.Command;
import com.blockhaus2000.util.OutputStream;
import com.blockhaus2000.util.command.CommandContext;

/**
 * 
 * @author Blockhaus2000
 */
public class StaticTestCommand {
    @Command(aliases = { "test", "t" },
             desc = "This is the first test command (static)")
    public static void testCommand(final CommandContext context) {
        OutputStream.sendMessage(context.getSender(), "It works!");
    }
}
