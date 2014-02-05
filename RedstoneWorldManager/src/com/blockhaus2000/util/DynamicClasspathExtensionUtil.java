/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 
 * @author Blockhaus2000
 */
public class DynamicClasspathExtensionUtil {
    public static void addJarToClassPath(final ClassLoader loader, final File jar) {
        assert loader != null : "Loader cannot be null!";
        assert jar != null : "Jar cannot be null!";

        if (!(loader instanceof URLClassLoader)) {
            throw new IllegalArgumentException("Loader ha to be an instance of URLClassLoader!");
        }

        Method addUrlMethod = null;

        try {
            addUrlMethod = URLClassLoader.class.getDeclaredMethod("addURL", new Class<?>[] { URL.class });
        } catch (NoSuchMethodException | SecurityException ex) {
            ExceptionHandler.handle(ex);
        }

        addUrlMethod.setAccessible(true);

        try {
            addUrlMethod.invoke(loader, jar.toURI().toURL());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | MalformedURLException ex) {
            ExceptionHandler.handle(ex);
        }
    }

    public static void addJarToClassPath(final File jar) {
        addJarToClassPath(Thread.currentThread().getContextClassLoader(), jar);
    }

    public static void addJarsToClassPath(final ClassLoader loader, final File[] jars) {
        for (File target : jars) {
            addJarToClassPath(loader, target);
        }
    }

    public static void addJarsToClasPath(final File[] jars) {
        for (File target : jars) {
            addJarToClassPath(target);
        }
    }
}
