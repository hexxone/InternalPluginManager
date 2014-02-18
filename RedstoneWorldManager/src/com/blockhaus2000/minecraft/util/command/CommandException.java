/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.minecraft.util.command;

/**
 * 
 * @author Blockhaus2000
 */
public class CommandException extends RuntimeException {
    private static final long serialVersionUID = -8928926369449212788L;

    public CommandException() {
        super();
    }

    public CommandException(final String description) {
        super(description);
    }

    public CommandException(final Throwable cause) {
        super(cause);
    }

    public CommandException(final String description, final Throwable cause) {
        super(description, cause);
    }
}
