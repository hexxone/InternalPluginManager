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

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Blockhaus2000
 */
@SuppressWarnings("javadoc")
public class StringUtilTest {
    @Test
    public void joinString() {
        final String[] array = new String[] { "e0", "e1", "e2", "e3", "e4" };

        Assert.assertEquals("e0, e1, e2, e3, e4", StringUtil.joinString(", ", array));
        Assert.assertEquals("e1, e2, e3, e4", StringUtil.joinString(1, ", ", array));
        Assert.assertEquals("e2, e3, e4", StringUtil.joinString(2, ", ", array));
        Assert.assertEquals("e3, e4", StringUtil.joinString(3, ", ", array));
        Assert.assertEquals("e4", StringUtil.joinString(4, ", ", array));
    }

    @Test
    public void escape() {
        Assert.assertEquals("\\H\\e\\l\\l\\o\\ \\W\\o\\r\\l\\d\\!", StringUtil.escape("Hello World!"));
    }

    @Test
    public void replaceLast() {
        final String str = "Hello World!";

        Assert.assertEquals("Hello !", StringUtil.replaceLast("World", "", str));
        Assert.assertEquals("Hello rld!", StringUtil.replaceLast("[A-Z][a-z]", "", str));
        Assert.assertEquals("Hello WXrld!", StringUtil.replaceLast("o", "X", str));
        Assert.assertEquals("Hello Worl-", StringUtil.replaceLast(".{2,3}", "-", str));
    }
}
