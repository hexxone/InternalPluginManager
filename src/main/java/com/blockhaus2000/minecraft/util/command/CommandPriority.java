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
package com.blockhaus2000.minecraft.util.command;

/**
 * The priority for commands to regulate the execution order from high to low.
 * That means that the exection order is:
 * <ul>
 * <li>{@link #LOWEST}</li>
 * <li>{@link #LOW}</li>
 * <li>{@link #NORMAL}</li>
 * <li>{@link #HIGH}</li>
 * <li>{@link #HIGHEST}</li>
 * <li>{@link #MONITOR}</li>
 * </ul>
 *
 * @author Blockhaus2000
 */
public enum CommandPriority {
    /**
     * The lowest {@link CommandPriority}.
     */
    LOWEST(0),

    /**
     * The second-lowest {@link CommandPriority}.
     */
    LOW(1),

    /**
     * The normal {@link CommandPriority}.
     */
    NORMAL(2),

    /**
     * The second-highest {@link CommandPriority}.
     */
    HIGH(3),

    /**
     * The highest {@link CommandPriority}.
     */
    HIGHEST(4),

    /**
     * The super-high {@link CommandPriority}. Do not execute something with
     * this {@link CommandPriority}, only use it for monitoring!
     */
    MONITOR(5);

    private final int level;

    private CommandPriority(final int level) {
        this.level = level;
    }

    /**
     *
     * @return The level of the target {@link CommandPriority}.
     */
    public int getLevel() {
        return level;
    }
}
