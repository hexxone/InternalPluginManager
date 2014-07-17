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
 * This utility class for the checkstyle Maven plugin is a workaround to
 * suppress some warnings from checkstyle.
 *
 * <p>
 * <b> NOTE: Only use this methods if you really know what you are doing! </b>
 * </p>
 * <p>
 * <b> NOTE: Please document the usage of one of this methods! Not in the
 * JavaDocs, but in a regular comment. Use a comment like
 * <code>This fails silent. Suppressing warnings from checkstyle.</code> </b>
 * </p>
 *
 */
public final class CheckstyleUtil {
    private CheckstyleUtil() {
        // Utility classes should not have a visible constructor.
    }

    /**
     * Call this method to suppress warnings like
     * "must be one statement in catch-block".
     *
     */
    public static void failsSilent() {
    }
}
