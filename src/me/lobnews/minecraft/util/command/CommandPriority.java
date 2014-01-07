/*
 * This file is part of RedstoneWorldManager
 * 
 */
package me.lobnews.minecraft.util.command;

/**
 * 
 * @author Blockhaus2000
 */
public enum CommandPriority {
    LOWEST(0), LOW(1), NORMAL(2), HIGH(3), HIGHEST(4), MONITOR(5);

    private final int level;

    private CommandPriority(final int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
