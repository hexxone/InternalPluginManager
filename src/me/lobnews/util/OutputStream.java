/*
 * This file is part of RedstoneWorldManager
 * 
 */
package me.lobnews.util;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 
 * @author Blockhaus2000
 */
public class OutputStream {
    private static final Logger logger = Bukkit.getServer().getLogger();

    public static <T> void log(final Level level, final T msg) {
        logger.log(level, msg.toString());
    }

    public static <T> void log(final T msg) {
        log(Level.INFO, msg);
    }

    public static <T> void log(final Level level, final List<T> msg) {
        for (T target : msg) {
            log(level, target);
        }
    }

    public static <T> void log(final List<T> msg) {
        log(Level.INFO, msg);
    }

    @SafeVarargs
    public static <T> void log(final Level level, final T... msg) {
        log(level, Arrays.asList(msg));
    }

    @SafeVarargs
    public static <T> void log(final T... msg) {
        log(Level.INFO, msg);
    }

    public static <T> void sendMessage(final CommandSender sender, final T msg) {
        if (!(sender instanceof Player)) {
            log(msg);
        }

        ((Player) sender).sendMessage(msg.toString());
    }

    public static <T> void sendMessage(final CommandSender sender, final List<T> msg) {
        for (T target : msg) {
            sendMessage(sender, target);
        }
    }

    @SafeVarargs
    public static <T> void sendMessage(final CommandSender sender, final T... msg) {
        sendMessage(sender, Arrays.asList(msg));
    }

    // Getter
    public static Logger getLogger() {
        return logger;
    }
}
