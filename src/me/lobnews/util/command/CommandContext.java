/*
 * This file is part of RedstoneWorldManager
 * 
 */
package me.lobnews.util.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * 
 * @author Blockhaus2000
 */
public class CommandContext extends CommandSyntax {
    private final Command bukkitCommand;
    private final CommandSender sender;
    private final List<String> args;
    private final String label;

    public CommandContext(final CommandSyntax syntax, final Command bukkitCommand, final CommandSender sender,
            final List<String> args, final String label) {
        super(syntax.getCommand(), syntax.getClass(), syntax.getObject(), syntax.getMethod());
        this.bukkitCommand = bukkitCommand;
        this.sender = sender;
        this.args = args;
        this.label = label;
    }

    public Command getBukkitCommand() {
        return bukkitCommand;
    }

    public CommandSender getSender() {
        return sender;
    }

    public List<String> getArgs() {
        return args;
    }

    public String getLabel() {
        return label;
    }
}
