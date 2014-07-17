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
package com.blockhaus2000.util;

import com.blockhaus2000.main.bukkit.InternalPluginManager;
import com.blockhaus2000.minecraft.util.tabcompl.TabCompletionManager;

/**
 * The tab completer registration util help you to register your tab completer
 * correctly.
 *
 */
public final class TabCompleterRegistrationUtil {
    private TabCompleterRegistrationUtil() {
        // utility should not have a visible constructor
    }

    /**
     * Registers the given tab completer {@link Class} with the given
     * {@link Object}. Will call
     * {@link TabCompletionManager#register(Class, Object)} with
     * <code>plugin</code> as the target plugin.
     *
     * @param clazz
     *            The {@link Class} where the tab completers are located.
     * @param obj
     *            An {@link Object} of the given {@link Class}.
     * @see com.blockhaus2000.minecraft.util.tabcompl.TabCompletionManager#register(java.lang.Class,
     *      java.lang.Object)
     */
    public static void registerTabCompleters(final Class<?> clazz, final Object obj) {
        InternalPluginManager.getServer().getTabCompletionManager().register(clazz, obj);
    }

    /**
     * Registers commands. Will call
     * {@link TabCompleterRegistrationUtil#registerTabCompleters(Class, Object)}
     * with <code>obj = null</code>.
     *
     * @param clazz
     *            The {@link Class} where the commands are located.
     * @see com.blockhaus2000.util.TabCompleterRegistrationUtil#registerTabCompleters(java.lang.Class,
     *      java.lang.Object)
     */
    public static void registerTabCompleters(final Class<?> clazz) {
        TabCompleterRegistrationUtil.registerTabCompleters(clazz, null);
    }

    /**
     * Registers commands. Will call
     * {@link TabCompleterRegistrationUtil#registerTabCompleters(Class, Object)}
     * with the {@link Class} of the given {@link Object}.
     *
     * @param obj
     *            The {@link Object} where the commands are located.
     * @see com.blockhaus2000.util.TabCompleterRegistrationUtil#registerTabCompleters(java.lang.Class,
     *      java.lang.Object)
     */
    public static void registerTabCompleters(final Object obj) {
        TabCompleterRegistrationUtil.registerTabCompleters(obj.getClass(), obj);
    }
}
