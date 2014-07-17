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

import java.util.ArrayList;
import java.util.List;

/**
 * This utility class has some methods to simplify the usage of some collection
 * operations.
 *
 */
public final class CollectionUtil {
    private CollectionUtil() {
        // utlility classes should not have a visible constructor.
    }

    /**
     * Replaces all occurres of the given regex in the given {@link List} with
     * the given replacement.
     *
     * <p>
     * <b> NOTE: The given {@link List} will not be changed. This method works
     * immutable. </b>
     * </p>
     *
     * @param regex
     *            The regex to search.
     * @param replacement
     *            The replacement.
     * @param rLst
     *            The {@link List} that contains the regex.
     * @return The {@link List} with the replaced Strings.
     */
    public static List<String> replaceAll(final String regex, final String replacement, final List<String> rLst) {
        final List<String> lst = new ArrayList<String>(rLst);

        for (int i = 0; i < lst.size(); i++) {
            lst.set(i, lst.get(i).replaceAll(regex, replacement));
        }

        return lst;
    }
}
