/*
 * This file is part of RedstoneWorldManager
 * 
 */
package me.lobnews.util.command;

import java.util.List;

import me.lobnews.util.Tag;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * 
 * @author Blockhaus2000
 */
public class CommandContext extends CommandInfo {
    private final Command command;
    private final CommandSender sender;
    private final String[] rawArgs;
    private final String label;
    private final List<Tag<?>> args;
    private final List<Tag<?>> flags;

    public CommandContext(final CommandInfo syntax, final Command command, final CommandSender sender, final String[] rawArgs,
            final String label, final List<Tag<?>> args, final List<Tag<?>> flags) {
        super(syntax.getCommandAnot(), syntax.getClass(), syntax.getObject(), syntax.getMethod());
        this.command = command;
        this.sender = sender;
        this.rawArgs = rawArgs;
        this.label = label;
        this.args = args;
        this.flags = flags;
    }

    public Command getCommand() {
        return command;
    }

    public CommandSender getSender() {
        return sender;
    }

    public String[] getRawArgs() {
        return rawArgs;
    }

    public String getLabel() {
        return label;
    }

    public List<Tag<?>> getArgs() {
        return args;
    }

    public List<Tag<?>> getFlags() {
        return flags;
    }
}
