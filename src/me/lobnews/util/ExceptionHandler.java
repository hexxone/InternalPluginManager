/*
 * This file is part of RedstoneWorldManager
 * 
 */
package me.lobnews.util;

import org.apache.commons.lang.Validate;

import me.lobnews.rwm.bukkit.Main;
import me.lobnews.util.resources.MainPluginResource;

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
    private static Main main;

    /**
     * Handles the given {@link Exception}. This method will print out the stack
     * trace and disables the main plugin ({@link Main}, if the
     * <code>disable == true</code>.
     * 
     * @param ex
     *            The Exception that has to be handled.
     * @param disable
     *            The flag to enable/disable main plugin deactivation.
     * @throws IllegalArgumentException
     *             Will be throwed if the given {@link Exception} is
     *             <code>null</code>.
     */
    public static void handle(final Exception ex, final boolean disable) throws IllegalArgumentException {
        Validate.notNull(ex, "Ex cannot be null.");

        ex.printStackTrace();
        PluginUtil.disable(main);
    }

    /**
     * Handles the given {@link Exception} with the method
     * {@link ExceptionHandler#handle(Exception, boolean)} with
     * <code>disable = false</code>.
     * 
     * @param ex
     *            The Exception that has to be handled.
     * @throws IllegalArgumentException
     *             Will be throwed if
     *             {@link ExceptionHandler#handle(Exception, boolean)} will
     *             throw it.
     * @see ExceptionHandler#handle(Exception, boolean)
     */
    public static void handle(final Exception ex) throws IllegalArgumentException {
        handle(ex, false);
    }
}
