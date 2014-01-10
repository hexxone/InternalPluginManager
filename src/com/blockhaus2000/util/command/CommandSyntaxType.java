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

    INTEGER_VARARG("Integer..."),

    DOUBLE("Double"),

    DOUBLE_VARARG("Double...");

    private final String typeString;

    private CommandSyntaxType(final String typeString) {
        this.typeString = typeString;
    }

    public String getTypeString() {
        return typeString;
    }

    public boolean isVarArg() {
        switch (this) {
        case DOUBLE_VARARG:
            /* FALL-THROUGH */
        case INTEGER_VARARG:
            /* FALL-THROUGH */
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

    public Class<?> getTypeClass() {
        switch (this) {
        case DOUBLE:
            return Double.class;
        case DOUBLE_VARARG:
            return Double[].class;
        case INTEGER:
            return Integer.class;
        case INTEGER_VARARG:
            return Integer[].class;
        case STRING:
            return String.class;
        case STRING_VARARG:
            return String[].class;
        }

        return null;
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
