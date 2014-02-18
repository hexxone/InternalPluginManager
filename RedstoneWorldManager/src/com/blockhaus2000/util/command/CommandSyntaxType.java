/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.util.command;

/**
 * 
 * @author Blockhaus2000
 */
public enum CommandSyntaxType {
    STRING("String"),

    STRING_VARARG("String..."),

    INTEGER("Integer"),

    DOUBLE("Double");

    private final String typeString;

    private CommandSyntaxType(final String typeString) {
        this.typeString = typeString;
    }

    public String getTypeString() {
        return typeString;
    }

    public boolean isVarArg() {
        switch (this) {
        case STRING_VARARG:
            return true;

        case DOUBLE:
            /* FALL-THROUGH */
        case INTEGER:
            /* FALL-THROUGH */
        case STRING:
            /* FALL-THROUGH */
        }

        return false;
    }

    public static CommandSyntaxType getTypeByString(final String typeString) {
        for (CommandSyntaxType target : CommandSyntaxType.values()) {
            if (target.getTypeString().equalsIgnoreCase(typeString)) {
                return target;
            }
        }

        return null;
    }
}
