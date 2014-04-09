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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.blockhaus2000.util.Tag;

/**
 * This class represents the data from a {@link CommandContext}. That includes
 * the arguments and flags.
 *
 * @author Blockhaus2000
 */
public class ContextData {
    private final List<Tag<?>> args;
    private final Map<Character, Tag<?>> flags;

    /**
     * Intances a new {@link ContextData} with the given arguments and flags.
     *
     * @param args
     *            The arguments that has to be saved.
     * @param flags
     *            The flags that has to be saved.
     */
    public ContextData(final List<Tag<?>> args, final Map<Character, Tag<?>> flags) {
        this.args = args;
        this.flags = flags;
    }

    /**
     * Intances a new {@link ContextData} with the given arguments and flags.
     *
     * @param rawArgs
     *            The arguments that has to be saved.
     * @param flags
     *            The flags that has to be saved.
     */
    public ContextData(final String[] rawArgs, final Map<Character, Tag<?>> flags) {
        List<Tag<?>> args = new ArrayList<Tag<?>>();

        for (String target : rawArgs) {
            args.add(new Tag<String>(target));
        }

        this.args = args;
        this.flags = flags;
    }

    /**
     *
     * @return The arguments represented by a {@link Tag} {@link List}.
     */
    public List<Tag<?>> getArgs() {
        return args;
    }

    /**
     *
     * @return The flags represented by a {@link Character} {@link Tag}
     *         {@link List}.
     */
    public Map<Character, Tag<?>> getFlags() {
        return flags;
    }
}
