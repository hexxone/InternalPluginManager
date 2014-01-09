/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.util;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Blockhaus2000
 */
public class StringUtil {
    public static String joinString(final String delimiter, final List<String> list) {
        if (list.size() == 0) {
            return "";
        }

        StringBuffer buffer = new StringBuffer(list.get(0));

        for (int i = 1; i < list.size(); i++) {
            buffer.append(delimiter + list.get(i));
        }

        return buffer.toString();
    }

    public static String joinString(final String delimiter, final String... array) {
        return joinString(delimiter, Arrays.asList(array));
    }
}
