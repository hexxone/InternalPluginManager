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
 * This class represents a caller (for use in the {@link StackUtil}). It
 * contains the name of the class that contains the caller method.
 *
 */
public class Caller {
    private final String className;
    private final String methodName;

    /**
     * Instances a new {@link Caller}.
     *
     * @param className
     *            The name of the class that contains the caller method name.
     * @param methodName
     *            The name of the method that was the caller.
     */
    public Caller(final String className, final String methodName) {
        this.className = className;
        this.methodName = methodName;
    }

    /**
     *
     * @return The name of the class that contains the caller method.
     */
    public String getClassName() {
        return className;
    }

    /**
     *
     * @return The name of the caller method.
     */
    public String getMethodName() {
        return methodName;
    }
}
