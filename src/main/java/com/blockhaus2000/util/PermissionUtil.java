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

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.blockhaus2000.bukkit.util.DynamicPluginCommand;
import com.blockhaus2000.bukkit.util.SimpleDynamicPluginCommand;

/**
 * This class is a utility class for easy permission check.
 *
 */
public final class PermissionUtil {
    private PermissionUtil() {
        // Utility classes should not have a public, protected or visible
        // constructor.
    }

    /**
     * Checks that the given {@link CommandSender} has the given permission.
     *
     * @param sender
     *            The {@link CommandSender} that has to have the given
     *            permission.
     * @param perm
     *            The permission that the given {@link CommandSender} has to
     *            have.
     * @return A boolean value that the given {@link CommandSender} has the
     *         given {@link PermissionUtil}. Will return <code>true</code> in
     *         the following cases:
     *         <ul>
     *         <li>the given permission is <code>null</code>
     *         <li>the given permission is empty</li>
     *         <li>the given {@link CommandSender} is not an instance of
     *         {@link Player}</li>
     *         <li>the given {@link CommandSender} has the given permission</li>
     *         </ul>
     *         Otherwise, this will return <code>false</code>.
     */
    public static boolean hasPermission(final CommandSender sender, final String perm) {
        return perm == null || sender == null || perm.length() == 0 || !(sender instanceof Player) || sender.isOp()
                || sender.hasPermission(perm);
    }

    /**
     * Checks that the given {@link CommandSender} has the permission of the
     * given {@link DynamicPluginCommand}. Will call
     * {@link PermissionUtil#hasPermission(CommandSender, String)} with the
     * permission of the given {@link DynamicPluginCommand}.
     *
     * @param sender
     *            The {@link CommandSender} that has to have the permission of
     *            the given {@link DynamicPluginCommand}.
     * @param cmd
     *            The {@link DynamicPluginCommand} that contains the permission
     *            for check for.
     * @return The returned value of
     *         {@link PermissionUtil#hasPermission(CommandSender, String)}
     * @see com.blockhaus2000.util.PermissionUtil#hasPermission(org.bukkit.command.CommandSender,
     *      java.lang.String)
     */
    public static boolean hasPermission(final CommandSender sender, final SimpleDynamicPluginCommand cmd) {
        return PermissionUtil.hasPermission(sender, cmd.getPermission());
    }
}
