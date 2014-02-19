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
 *  package com.blockhaus2000.bukkit.util;
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
        assert loader instanceof URLClassLoader : "Loader has to be an instance of URLClassLoader!";

        Method addUrlMethod = null;

        try {
            addUrlMethod = URLClassLoader.class.getDeclaredMethod("addURL", new Class<?>[] { URL.class });
        } catch (SecurityException ex) {
            ExceptionHandler.handle(ex);
        } catch (NoSuchMethodException ex) {
            ExceptionHandler.handle(ex);
        }

        addUrlMethod.setAccessible(true);

        try {
            addUrlMethod.invoke(loader, jar.toURI().toURL());
        } catch (IllegalArgumentException ex) {
            ExceptionHandler.handle(ex);
        } catch (MalformedURLException ex) {
            ExceptionHandler.handle(ex);
        } catch (IllegalAccessException ex) {
            ExceptionHandler.handle(ex);
        } catch (InvocationTargetException ex) {
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
