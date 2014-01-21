/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.util.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.blockhaus2000.util.Tag;

/**
 * This class represents the data from a {@link CommandContext}. That includes
 * the arguments and flags.
 * 
 * @author Blockhaus2000
 */
public class ContextData {
    private final List<Tag<?>> args;
    private final Map<Character, Tag<?>> flags;

    /**
     * Intances a new {@link ContextData} with the given arguments and flags.
     * 
     * @param args
     *            The arguments that has to be saved.
     * @param flags
     *            The flags that has to be saved.
     */
    public ContextData(final List<Tag<?>> args, final Map<Character, Tag<?>> flags) {
        this.args = args;
        this.flags = flags;
    }

    public ContextData(final String[] rawArgs, final Map<Character, Tag<?>> flags) {
        List<Tag<?>> args = new ArrayList<Tag<?>>();

        for (String target : rawArgs) {
            args.add(new Tag<String>(target));
        }

        this.args = args;
        this.flags = flags;
    }

    /**
     * 
     * @return {@link ContextData#args}
     */
    public List<Tag<?>> getArgs() {
        return args;
    }

    /**
     * 
     * @return {@link ContextData#flags}
     */
    public Map<Character, Tag<?>> getFlags() {
        return flags;
    }
}
