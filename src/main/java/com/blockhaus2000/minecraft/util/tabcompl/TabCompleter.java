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
package com.blockhaus2000.minecraft.util.tabcompl;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation is used for mark a method that has to be used as a tab
 * completer.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TabCompleter {
    /**
     * This methods identifys the command labels that the tab completer has to
     * listen on.
     *
     * @return A {@link String}[] of the labels the tab completer has to listen
     *         on.
     */
    public String[] labels();

    /**
     * The second level commands that the tab completer has to listen on.
     *
     * @return A {@link String}[] of second level commands this tab completer
     *         has to listen on.
     */
    public String[] secondLevelCommands() default "";
}
