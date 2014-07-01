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
package com.blockhaus2000.util.tabcompl;

import java.lang.reflect.Method;

import com.blockhaus2000.minecraft.util.tabcompl.TabCompleter;

/**
 * The {@link TabCompleterInfo} is a utility class that enables storage about a
 * tab completer in one {@link Object}.
 *
 */
public class TabCompleterInfo {
    private final TabCompleter tabCompleter;

    private final Class<?> clazz;
    private final Object obj;
    private final Method method;

    /**
     * Creates a new {@link TabCompleterInfo}.
     *
     * @param tabCompleter
     *            The annotation the method is tagged with.
     * @param clazz
     *            The {@link Class} that contains the method.
     * @param obj
     *            An {@link Object} of the the given {@link Class}. Can be
     *            <code>null</code>.
     * @param method
     *            The method to invoke.
     */
    public TabCompleterInfo(final TabCompleter tabCompleter, final Class<?> clazz, final Object obj, final Method method) {
        assert tabCompleter != null : "TabCompleter can not be null!";
        assert clazz != null : "Clazz can not be null!";
        assert method != null : "Method can not be null!";

        this.tabCompleter = tabCompleter;
        this.clazz = clazz;
        this.obj = obj;
        this.method = method;
    }

    /**
     *
     * @return The annotation the method is tagged with.
     */
    public TabCompleter getTabCompleter() {
        return tabCompleter;
    }

    /**
     *
     * @return The {@link Class} that contains the method to invoke.
     */
    public Class<?> getClazz() {
        return clazz;
    }

    /**
     *
     * @return An {@link Object} of the {@link Class}. Can be <code>null</code>.
     */
    public Object getObj() {
        return obj;
    }

    /**
     *
     * @return The {@link Method} to invoke.
     */
    public Method getMethod() {
        return method;
    }
}
