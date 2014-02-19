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

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Blockhaus2000
 */
public class StringUtil {
    public static String joinString(int i, final String delimiter, final List<String> list) {
        if (i < 0) {
            throw new StringIndexOutOfBoundsException("I cannot be < 0!");
        }

        if (i > list.size()) {
            throw new StringIndexOutOfBoundsException("I cannot be > list.size()");
        }

        if (list.size() == 0) {
            return "";
        }

        StringBuffer buffer = new StringBuffer(list.get(0));

        for (i++; i < list.size(); i++) {
            buffer.append(delimiter + list.get(i));
        }

        return buffer.toString();
    }

    public static String joinString(final int i, final String delimiter, final String... array) {
        return joinString(delimiter, Arrays.asList(array));
    }

    public static String joinString(final String delimiter, final List<String> list) {
        return joinString(0, delimiter, list);
    }

    public static String joinString(final String delimiter, final String... array) {
        return joinString(0, delimiter, array);
    }
}
