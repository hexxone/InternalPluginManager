/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.util.command;

import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.blockhaus2000.util.Tag;

/**
 * 
 * @author Blockhaus2000
 */
public class CommandContext extends RawCommandContext {
    private final List<Tag<?>> args;
    private final Map<Character, Tag<?>> flags;

    public CommandContext(final CommandInfo commandInfo, final CommandSender sender, final Command command, final String label,
            final String[] rawArgs, final List<Tag<?>> args, final Map<Character, Tag<?>> flags) {
        super(commandInfo, sender, command, label, rawArgs);
        this.args = args;
        this.flags = flags;
    }

    public CommandContext(final RawCommandContext rawContext, final List<Tag<?>> args, final Map<Character, Tag<?>> flags) {
        this(rawContext, rawContext.getSender(), rawContext.getCommand(), rawContext.getLabel(), rawContext.getRawArgs(), args,
                flags);
    }

    // Getter
    public List<Tag<?>> getArgs() {
        return args;
    }

    public Map<Character, Tag<?>> getFlags() {
        return flags;
    }
}
