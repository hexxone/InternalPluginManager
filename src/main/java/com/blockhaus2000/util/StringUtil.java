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

import java.util.Arrays;
import java.util.List;

/**
 * This class represents a util class for {@link String}s. You can use the
 * static methods in this class for basic {@link String} operations that are not
 * implemented in the Java SE, such as joinString or something else.
 *
 * @author Blockhaus2000
 */
public class StringUtil {
    private StringUtil() {
        // Utility classes should not have a public, protected or visible
        // constructor.
    }

    /**
     * Joins the give {@link List} to a {@link String}. The given delemiter will
     * be placed between the entrys of the given {@link List}. The index is the
     * index of the elemt of the given {@link List}, where the join algorithm
     * has to start.
     *
     * @param i
     *            The "join-start" index. Cannot be smaller than 0 or bigger or
     *            equals the size of the given {@link List}.
     * @param delimiter
     *            The delmiter that has to be placed between the elements of the
     *            given {@link List}.
     * @param list
     *            The {@link List} that has to be joined to a {@link String}. If
     *            it is <code>null</code> or empty, This method returns an empty
     *            {@link String}.
     * @return The joined {@link String} or, in the case that the given
     *         {@link List} is <code>null</code> or empty, an empty
     *         {@link String}.
     */
    public static String joinString(int i, final String delimiter, final List<String> list) {
        assert i >= 0 : "I cannot be < 0!";
        assert list == null || i < list.size() : "I cannot be > list.size()!";

        if (list == null || list.size() == 0) {
            return "";
        }

        StringBuffer buffer = new StringBuffer(list.get(i));

        for (i++; i < list.size(); i++) {
            buffer.append(delimiter + list.get(i));
        }

        return buffer.toString().replaceAll("(" + StringUtil.escape(delimiter) + ")+", delimiter);
    }

    /**
     * Joins the given {@link String}[] (implemented with var args) to a
     * {@link String}. Will call
     * {@link StringUtil#joinString(int, String, List)}.
     *
     * @param i
     *            The "join-start" index. Will be given to
     *            {@link StringUtil#joinString(int, String, List)}.
     * @param delimiter
     *            The delimiter that has to be placed between the elements of
     *            the given {@link List}. Will be given to
     *            {@link StringUtil#joinString(int, String, List)}.
     * @param array
     *            The {@link String}[] that has to be joined to a {@link String}
     *            . Will be given to
     *            {@link StringUtil#joinString(int, String, List)}.
     * @return The return of {@link StringUtil#joinString(int, String, List)}.
     * @see com.blockhaus2000.util.StringUtil#joinString(int, java.lang.String,
     *      java.util.List)
     */
    public static String joinString(final int i, final String delimiter, final String... array) {
        return StringUtil.joinString(i, delimiter, Arrays.asList(array));
    }

    /**
     * Joins the given {@link List} to a {@link String}. Will call
     * {@link StringUtil#joinString(int, String, List)} with <code>i = 0</code>.
     *
     * @param delimiter
     *            The delimiter that has to be placed between the elements of
     *            the given {@link List}. Will be given to
     *            {@link StringUtil#joinString(int, String, List)}.
     * @param list
     *            The {@link List} that has to be joined to a {@link String} .
     *            Will be given to
     *            {@link StringUtil#joinString(int, String, List)}.
     * @return The return of {@link StringUtil#joinString(int, String, List)}.
     * @see com.blockhaus2000.util.StringUtil#joinString(int, java.lang.String,
     *      java.util.List)
     */
    public static String joinString(final String delimiter, final List<String> list) {
        return StringUtil.joinString(0, delimiter, list);
    }

    /**
     * Joins the given {@link String}[] (implemented with var args) to a
     * {@link String}. Will call
     * {@link StringUtil#joinString(int, String, List)} with <code>i = 0</code>.
     *
     * @param delimiter
     *            The delimiter that has to be placed between the elements of
     *            the given {@link List}. Will be given to
     *            {@link StringUtil#joinString(int, String, List)}.
     * @param array
     *            The {@link String}[] that has to be joined to a {@link String}
     *            . Will be given to
     *            {@link StringUtil#joinString(int, String, List)}.
     * @return The return of {@link StringUtil#joinString(int, String, List)}.
     * @see com.blockhaus2000.util.StringUtil#joinString(int, java.lang.String,
     *      java.util.List)
     */
    public static String joinString(final String delimiter, final String... array) {
        return StringUtil.joinString(0, delimiter, array);
    }

    /**
     * This method will escape all chars of the given {@link String}. That means
     * that "abcdfg" will be escaped to "\\a\\b\\c\\d\\e\\f\\g".
     *
     * @param str
     *            The {@link String} that has to be escaped.
     * @return The escaped {@link String}.
     */
    public static String escape(final String str) {
        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {
            buffer.append("\\" + str.charAt(i));
        }

        return buffer.toString();
    }

    /**
     * This method replaces the last char sequence that matches the given regex.
     * It works such like {@link String#replaceFirst(String, String)}.
     *
     * @param regex
     *            The regex to replace.
     * @param replacement
     *            The replacement for the given regex.
     * @param str
     *            The String that contains the regex to replace.
     * @return The "replaced" String.
     * @see java.lang.String#replaceFirst(java.lang.String, java.lang.String)
     */
    public static String replaceLast(final String regex, final String replacement, final String str) {
        final StringBuffer buffer = new StringBuffer();

        final String[] splitted = str.split(regex);
        final int length = splitted.length;
        for (int i = 0; i < length; i++) {
            if (i == length - 1) {
                continue;
            }

            buffer.append(splitted[i]);
        }

        return buffer.toString();
    }
}
