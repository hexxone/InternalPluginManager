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
package com.blockhaus2000.main.bukkit;

import com.blockhaus2000.plugin.IpmServer;
import com.blockhaus2000.plugin.SimpleIpmServer;

/**
 * This is a utility class with static methods.
 *
 * @author Blockhaus2000
 */
public class InternalPluginManager {
    private InternalPluginManager() {
        // Utility classes should not have a visible
        // constructor.
    }

    /**
     *
     * @return An {@link IpmServer}.
     */
    public static IpmServer getServer() {
        return SimpleIpmServer.getInstance();
    }
}
