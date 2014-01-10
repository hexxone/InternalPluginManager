/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.util.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Blockhaus2000
 */
public class CommandSyntax {
    private final List<Class<?>> syntax = new ArrayList<Class<?>>();
    
    public CommandSyntax(final String syntax) {
        
    }
    
    public List<Class<?>> parseCommandSyntax(final String syntaxString) {
        String[] syntax = syntaxString.split(" ");
    }
}
