/*
 * This file is part of RedstoneWorldManager
 * 
 */
package me.lobnews.rwm.bukkit.test;

import me.lobnews.util.OutputStream;
import me.lobnews.util.command.CommandContext;
import me.lobnews.util.command.SingleCommand;

/**
 * 
 * @author Blockhaus2000
 */
public class TestCommand implements SingleCommand {
    @Override
    public void onCommand(final CommandContext context) {
        OutputStream.sendMessage(context.getSender(), "Test message. It works!");
    }
}
