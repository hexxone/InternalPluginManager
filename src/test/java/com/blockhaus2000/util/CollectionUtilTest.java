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
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class CollectionUtilTest {
    @Test
    public void replaceAll() {
        final List<String> lst = new ArrayList<String>();
        lst.add("Hello World!");
        lst.add("Hello World!");

        Assert.assertEquals(new ArrayList<String>(Arrays.asList("Hello !", "Hello !")),
                CollectionUtil.replaceAll("World", "", lst));
        Assert.assertEquals(new ArrayList<String>(Arrays.asList("ello orld!", "ello orld!")),
                CollectionUtil.replaceAll("[A-Z]", "", lst));
        Assert.assertEquals(new ArrayList<String>(Arrays.asList("H W", "H W")), CollectionUtil.replaceAll("[a-z]*!?", "", lst));
    }
}
