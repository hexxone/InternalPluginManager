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
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Blockhaus2000
 */
@SuppressWarnings("javadoc")
public class ArrayUtilTest {
    private Collection<String> col;

    @Test
    public void removeFromArray() {
        final String[] testArray = new String[] { "e0", "e1", "e2", "e3", "e4" };

        Assert.assertArrayEquals(new String[] { "e1", "e2", "e3", "e4" }, ArrayUtil.removeFromArray(testArray, 0));
        Assert.assertArrayEquals(new String[] { "e0", "e2", "e3", "e4" }, ArrayUtil.removeFromArray(testArray, 1));
        Assert.assertArrayEquals(new String[] { "e0", "e1", "e3", "e4" }, ArrayUtil.removeFromArray(testArray, 2));
        Assert.assertArrayEquals(new String[] { "e0", "e1", "e2", "e4" }, ArrayUtil.removeFromArray(testArray, 3));
        Assert.assertArrayEquals(new String[] { "e0", "e1", "e2", "e3" }, ArrayUtil.removeFromArray(testArray, 4));
    }

    @Test
    public void toStringArray() {
        final String[] expected = new String[] { "e0", "e1", "e2", "e3", "e4" };

        col = new ArrayList<String>();
        fill();
        Assert.assertArrayEquals(expected, ArrayUtil.toStringArray(col));

        col = new TreeSet<String>();
        fill();
        Assert.assertArrayEquals(expected, ArrayUtil.toStringArray(col));
    }

    @Test
    public void toTagList() {
        final List<Tag<String>> expected = new ArrayList<Tag<String>>();
        expected.add(new Tag<String>("e0"));
        expected.add(new Tag<String>("e1"));
        expected.add(new Tag<String>("e2"));
        expected.add(new Tag<String>("e3"));
        expected.add(new Tag<String>("e4"));

        col = new ArrayList<String>();
        fill();
        Assert.assertEquals(expected, ArrayUtil.toTagList(col, ArrayList.class));

        col = new TreeSet<String>();
        fill();
        Assert.assertEquals(expected, ArrayUtil.toTagList(col, ArrayList.class));
    }

    private void fill() {
        col.add("e0");
        col.add("e1");
        col.add("e2");
        col.add("e3");
        col.add("e4");
    }
}
