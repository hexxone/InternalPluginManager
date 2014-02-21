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
package com.blockhaus2000.plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import com.blockhaus2000.plugin.exception.InvalidPluginDescriptionException;
import com.blockhaus2000.plugin.exception.PluginException;
import com.blockhaus2000.util.ExceptionHandler;
import com.blockhaus2000.util.ReflectionUtil;

/**
 * 
 * @author Blockhaus2000
 */
public class IpmPluginDescriptionFile {
    private final Yaml yaml = new Yaml(new SafeConstructor());

    private final File file;

    private String name;
    private String version;
    private Class<? extends IpmPlugin> main;

    public IpmPluginDescriptionFile(final InputStream stream, final File file) throws InvalidPluginDescriptionException,
            PluginException, IOException {
        this.file = file;

        loadMap(asMap(yaml.load(stream)));
        stream.close();
    }

    @SuppressWarnings("unchecked")
    private void loadMap(final Map<?, ?> map) throws InvalidPluginDescriptionException, PluginException {
        // load name
        final String name = map.get("name").toString();

        if (name == null || !name.matches("^[A-Za-z0-9_.-]+$")) {
            throw new InvalidPluginDescriptionException("The name \"" + name
                    + "\" does not match the regx \"^[A-Za-z0-9_.-]+$\" or is not set!");
        }

        this.name = name;

        // load version
        final String version = map.get("version").toString();

        if (version == null || version.length() <= 0) {
            throw new InvalidPluginDescriptionException("The version is empty or not set!");
        }

        this.version = version;

        // load man class
        final String mainClassPath = map.get("main").toString();

        if (mainClassPath == null) {
            throw new InvalidPluginDescriptionException("The main class is not set!");
        }

        // DynamicClasspathExtensionUtil.addJarToClassPath(file);

        Class<?> main = null;

        try {
            main = new IpmPluginClassLoader(new URL[] { file.toURI().toURL() }).findClass(mainClassPath);
        } catch (MalformedURLException ex) {
            ExceptionHandler.handle(ex);
            return;
        } catch (ClassNotFoundException ex) {
            throw new InvalidPluginDescriptionException("The setted main class (\"" + mainClassPath + "\") cannot be found!", ex);
        }

        if (!ReflectionUtil.hasSuperclass(main, IpmPlugin.class)) {
            throw new PluginException("The main class \"" + main + "\" does not extend \"" + IpmPlugin.class + "\"");
        }

        this.main = (Class<? extends IpmPlugin>) main;
    }

    private Map<?, ?> asMap(final Object obj) throws InvalidPluginDescriptionException {
        assert obj instanceof Map : obj + " is not correct structured!";

        return (Map<?, ?>) obj;
    }

    // Getter
    public Yaml getYaml() {
        return yaml;
    }

    public File getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public Class<? extends IpmPlugin> getMain() {
        return main;
    }
}
