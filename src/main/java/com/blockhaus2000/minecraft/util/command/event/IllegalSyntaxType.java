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
package com.blockhaus2000.minecraft.util.command.event;

/**
 * Specifies the illegal syntax type in the {@link IllegalSyntaxCommandEvent}.
 * 
 * @author Blockhaus2000
 */
public enum IllegalSyntaxType {
    /**
     * Will be the specification if a flag value is not available. For example,
     * a command with the setted flag <code>p:</code> will be executed like
     * <code>/cmd -p Blockhaus2000</code>, the flag value is available. But if
     * the flag is used like a toggleflag (<code>/cmd -p</code>), an
     * {@link IllegalSyntaxCommandEvent} will be fired with this type.
     * 
     */
    UNAVAILABLE_FLAG_VALUE,

    /**
     * Will be the specification if an {@link Integer}, {@link Double} or
     * {@link Long} is a syntax element, but the command has been executed with
     * an unvalid/illegal value at the same position. For example, a command
     * with the syntax <code>String, Integer</code> will be executed like
     * <code>/cmd arg0 arg1</code>.
     * 
     */
    NUMBER_SYNTAX_IS_STRING;
}
