/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.plugin.exception;

/**
 * 
 * @author Blockhaus2000
 */
public class PluginNotFoundException extends PluginException {
    private static final long serialVersionUID = 3806938843214122582L;

    public PluginNotFoundException() {
        super();
    }

    public PluginNotFoundException(String description, Throwable cause) {
        super(description, cause);
    }

    public PluginNotFoundException(String description) {
        super(description);
    }

    public PluginNotFoundException(Throwable cause) {
        super(cause);
    }
}
