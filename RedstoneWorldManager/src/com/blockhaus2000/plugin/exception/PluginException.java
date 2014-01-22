/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.plugin.exception;

/**
 * 
 * @author Blockhaus2000
 */
public class PluginException extends RuntimeException {
    private static final long serialVersionUID = -7270008574817315415L;

    public PluginException() {
        super();
    }

    public PluginException(final String description) {
        super(description);
    }

    public PluginException(final Throwable cause) {
        super(cause);
    }

    public PluginException(final String description, final Throwable cause) {
        super(description, cause);
    }
}
