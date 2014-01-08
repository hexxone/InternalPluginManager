/*
 * This file is part of RedstoneWorldManager
 * 
 */
package me.lobnews.rwm.test;

import me.lobnews.minecraft.util.command.Command;
import me.lobnews.util.OutputStream;
import me.lobnews.util.command.CommandContext;

/**
 * 
 * @author Blockhaus2000
 */
public class TestCommand {
    @Command(aliases = { "testcommand", "testcmd", "tc" },
             desc = "Test Command.")
    public void testCommand(CommandContext context) {
        OutputStream.sendMessage(context.getSender(), "Hello World!");
    }
}
