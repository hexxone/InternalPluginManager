/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.util.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * 
 * @author Blockhaus2000
 */
public class RawCommandContext extends CommandInfo {
    private final CommandSender sender;
    private final Command command;
    private final String label;
    private final String[] rawArgs;

    public RawCommandContext(final CommandInfo commandInfo, final CommandSender sender, final Command command,
            final String label, final String[] rawArgs) {
        super(commandInfo.getCommandAnot(), commandInfo.getClazz(), commandInfo.getObject(), commandInfo.getMethod(), commandInfo
                .getSyntax());
        this.sender = sender;
        this.command = command;
        this.label = label;
        this.rawArgs = rawArgs;
    }

    // Getter
    public CommandSender getSender() {
        return sender;
    }

    public Command getCommand() {
        return command;
    }

    public String getLabel() {
        return label;
    }

    public String[] getRawArgs() {
        return rawArgs;
    }
}
