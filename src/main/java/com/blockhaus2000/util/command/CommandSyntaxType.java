/* This file is part of InternalPluginManager
 * 
 * Copyright 2014 Blockhaus2000
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  see the License for the specific language governing permissions and
 *  Limitations under the License.
 */
package com.blockhaus2000.util.command;

/**
 * 
 * @author Blockhaus2000
 */
public enum CommandSyntaxType {
    STRING("String"),

    STRING_VARARG("String..."),

    LONG("Long"),

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
            /* FALL THROUGH */
        case INTEGER:
            /* FALL-THROUGH */
        case LONG:
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
