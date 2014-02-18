/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.plugin.exception;

/**
 * 
 * @author Blockhaus2000
 */
public class InvalidPluginDescriptionException extends PluginException {
    private static final long serialVersionUID = 8398530293535767863L;

    public InvalidPluginDescriptionException() {
        super();
    }

    public InvalidPluginDescriptionException(String description, Throwable cause) {
        super(description, cause);
    }

    public InvalidPluginDescriptionException(String description) {
        super(description);
    }

    public InvalidPluginDescriptionException(Throwable cause) {
        super(cause);
    }
}
