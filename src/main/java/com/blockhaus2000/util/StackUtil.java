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
package com.blockhaus2000.util;

/**
 * This is a utility class for the usage of the stack.
 *
 * @author Blockhaus2000
 */
public class StackUtil {
    private StackUtil() {
        // Utility classes should not have a public, protected or visible
        // constructor.
    }

    /**
     *
     * @return The {@link Caller} of the method that calls this method.
     */
    public static Caller getCaller() {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[3];
        return new Caller(caller.getClassName(), caller.getMethodName());
    }
}
