/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.util;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.blockhaus2000.bukkit.util.DynamicPluginCommand;

/**
 * 
 * @author Blockhaus2000
 */
public class PermissionUtil {
    public static boolean hasPermission(final CommandSender sender, final String perm) {
        if (perm == null || perm.length() == 0 || !(sender instanceof Player) || sender.isOp() || sender.hasPermission(perm)) {
            return true;
        }

        return false;
    }

    public static boolean hasPermission(final CommandSender sender, final DynamicPluginCommand cmd) {
        return hasPermission(sender, cmd.getPermission());
    }
}
