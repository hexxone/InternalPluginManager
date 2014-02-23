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

import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import com.blockhaus2000.plugin.exception.InvalidPluginDescriptionException;
import com.blockhaus2000.plugin.exception.PluginException;

/**
 * 
 * @author Blockhaus2000
 */
public class SimpleIpmPluginDescription implements IpmPluginDescription {
    private final Yaml yaml = new Yaml(new SafeConstructor());

    private String name;
    private String version;
    private String main;

    private String[] authors;
    private String[] depends;

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPluginDescription#load(java.io.InputStream,
     *      java.io.File)
     */
    @Override
    public void load(final InputStream stream) throws PluginException {
        assert stream != null : "Stream cannot be null!";

        Map<?, ?> data = asMap(yaml.load(stream));

        // load name
        String name = (String) data.get("name");

        if (name == null || name.length() == 0 || !name.matches("^[A-Za-z0-9_.-]+$")) {
            throw new InvalidPluginDescriptionException(
                    "The name is not set, empty or does not match the regex \"^[A-Za-z0-9_.-]+$\"!");
        }

        this.name = name;

        // load version
        String version = (String) data.get("version");

        if (version == null || version.length() == 0) {
            throw new InvalidPluginDescriptionException("The version is not set or empty!");
        }

        this.version = version;

        // load main
        String main = (String) data.get("main");

        if (main == null || version.length() == 0) {
            throw new InvalidPluginDescriptionException("The main class is not set or empty!");
        }

        this.main = main;

        // load authors
        authors = ((String) data.get("authors")).split(", *");

        // load depends
        depends = ((String) data.get("depends")).split(", *");
    }

    private Map<?, ?> asMap(final Object obj) {
        assert obj instanceof Map : "Obj has to be an instance of Map!";
        return (Map<?, ?>) obj;
    }

    // Getter
    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPluginDescription#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPluginDescription#getVersion()
     */
    @Override
    public String getVersion() {
        return version;
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPluginDescription#getMain()
     */
    @Override
    public String getMain() {
        return main;
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPluginDescription#getAuthors()
     */
    @Override
    public String[] getAuthors() {
        return authors;
    }

    /**
     * 
     * @see com.blockhaus2000.plugin.IpmPluginDescription#getDepends()
     */
    @Override
    public String[] getDepends() {
        return depends;
    }
}
