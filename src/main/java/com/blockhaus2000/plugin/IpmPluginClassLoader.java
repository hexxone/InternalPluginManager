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
package com.blockhaus2000.plugin;

/**
 * This class loader is used to provide class usage through plugins/jars.
 * 
 * @author Blockhaus2000
 */
public interface IpmPluginClassLoader {
    /**
     * Searchs for the given (full-qualified) class name. If
     * <code>global == true</code>, this will search in all registered plugins.
     * 
     * @param name
     *            The full-qualified name of the class to search for.
     * @param global
     *            A <code>bollean</code> that indicates that the
     *            {@link IpmPluginLoader} has to search for the given class in
     *            all registered plugins.
     * @return The class, if found.
     * @throws ClassNotFoundException
     *             Will be thrown if the class could not be find.
     */
    public Class<?> findClass(final String name, final boolean global) throws ClassNotFoundException;

    /**
     * Will call {@link IpmPluginClassLoader#findClass(String, boolean)} with
     * <code>global = true</code>.
     * 
     * @param name
     *            The full-qualified name of the class to search for.
     * @return The class, if found
     * @throws ClassNotFoundException
     *             Will be thrown if the class could not be find.
     */
    public Class<?> findClass(final String name) throws ClassNotFoundException;
}
