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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

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
