/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014 Fabian Damken
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.blockhaus2000.ipm.util;

/**
 * This utility class provides some method for the array using.
 *
 */
public final class ArrayUtil {
    /**
     * Constructor of CheckstyleUtil.
     *
     */
    private ArrayUtil() {
        // Utility classes should not have a visible constructor.
    }

    /**
     * Joins the given string array together.
     *
     * @param rawDelimiter
     *            The delimiter to use. If <code>null</code>, a blank (
     *            <code>" "</code>) will be used.
     * @param start
     *            The start index. Included.
     * @param end
     *            The end index. Excluded.
     * @param arr
     *            The string array to join.
     * @return The joined string.
     */
    public static String joinString(final String rawDelimiter, final int start, final int end, final String... arr) {
        assert arr != null : "Arr cannot be null!";

        final String delimiter = rawDelimiter == null ? CommonStringConstants.BLANK : rawDelimiter;
        final int endIndex = end == -1 ? arr.length : end;

        final StringBuffer buffer = new StringBuffer();
        for (int i = start; i < endIndex; i++) {
            buffer.append(arr[i] + delimiter);
        }
        return buffer.substring(0, buffer.length() - delimiter.length());
    }

    /**
     * Delegates to {@link ArrayUtil#joinString(String, int, int, String...)}
     * with <code>start = 0</code> and <code>end = -1</code>.
     *
     * @param delimiter
     *            Passed to
     *            {@link ArrayUtil#joinString(String, int, int, String...)}.
     * @param arr
     *            Passed to
     *            {@link ArrayUtil#joinString(String, int, int, String...)}.
     * @return See {@link ArrayUtil#joinString(String, int, int, String...)}.
     * @see com.blockhaus2000.ipm.util.ArrayUtil#joinString(java.lang.String,
     *      int, int, java.lang.String...)
     */
    public static String joinString(final String delimiter, final String... arr) {
        return ArrayUtil.joinString(delimiter, 0, -1, arr);
    }

    /**
     * Delegates to {@link ArrayUtil#joinString(String, String...)} with
     * <code>delimiter = null</code>.
     *
     * @param arr
     *            Passed to
     *            {@link ArrayUtil#joinString(String, int, int, String...)}.
     * @return See {@link ArrayUtil#joinString(String, int, int, String...)}.
     * @see com.blockhaus2000.ipm.util.ArrayUtil#joinString(java.lang.String,
     *      int, int, java.lang.String...)
     */
    public static String joinString(final String... arr) {
        return ArrayUtil.joinString(null, arr);
    }
}
