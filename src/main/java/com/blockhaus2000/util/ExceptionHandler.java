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
import com.blockhaus2000.main.bukkit.IpmMain;
import com.blockhaus2000.plugin.IpmPlugin;
import com.blockhaus2000.util.resources.MainPluginResource;

/**
 * This class is the util class for handling {@link Exception}s. No methods in
 * thid class wil throw another {@link Exception}, except the
 * {@link IllegalArgumentException} if an given {@link Exception} is
 * <code>null</code>.
 *
 * @author Blockhaus2000
 */
public final class ExceptionHandler {
    @MainPluginResource
    private static IpmMain main;

    private ExceptionHandler() {
        // Utility classes should not have a public, protected or visible
        // constructor.
    }

    // /**
    // * Checks that a valid call (with {@link ExceptionHandler#main} is not
    // * <code>null</code>) is possible.
    // *
    // * @return <code>true</code> is a valid call is possible.
    // */
    // public static boolean isCallPossible() {
    // return ExceptionHandler.main == null;
    // }

    /**
     * Handles the given {@link Exception}. This method will print out the stack
     * trace and disables the main plugin ({@link IpmMain}, if the
     * <code>disable == true</code>.
     *
     * @param ex
     *            The Exception that has to be handled.
     * @param disable
     *            The flag to enable/disable main plugin deactivation.
     */
    public static void handle(final Exception ex, final boolean disable) {
        assert ex != null : "Ex cannot be null!";

        ex.printStackTrace();

        if (disable) {
            PluginUtil.disable(ExceptionHandler.main);
        }
    }

    /**
     * Handles the given {@link Exception} with the method
     * {@link ExceptionHandler#handle(Exception, boolean)} with
     * <code>disable = false</code>.
     *
     * @param ex
     *            The Exception that has to be handled.
     * @see ExceptionHandler#handle(Exception, boolean)
     */
    public static void handle(final Exception ex) {
        ExceptionHandler.handle(ex, false);
    }

    /**
     * Handles the given {@link Exception}. This method will print out the stack
     * trace and disables the given {@link IpmPlugin}.
     *
     * @param ex
     *            The Exception that has to be handled.
     * @param plugin
     *            The {@link IpmPlugin} to disable.
     */
    public static void handle(final Exception ex, final IpmPlugin plugin) {
        ExceptionHandler.handle(ex);

        InternalPluginManager.getServer().getPluginManager().disable(plugin);
    }
}
