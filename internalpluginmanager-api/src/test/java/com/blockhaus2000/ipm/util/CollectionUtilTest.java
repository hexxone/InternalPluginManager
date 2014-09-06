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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the {@link CollectionUtil}.
 *
 */
public class CollectionUtilTest {
    /**
     * Tests {@link CollectionUtil#indexOf(String, List)}.
     *
     */
    @Test
    public void indexOf() {
        final List<String> target = new ArrayList<String>(Arrays.asList("e0", "e1", "e2", "e3", "e4"));

        Assert.assertEquals(1, CollectionUtil.indexOf("e1", target));
        Assert.assertEquals(0, CollectionUtil.indexOf("e\\d", target));
        Assert.assertEquals(4, CollectionUtil.indexOf(".4", target));
        Assert.assertEquals(-1, CollectionUtil.indexOf("element", target));
    }

    /**
     * Tests {@link CollectionUtil#toTagCollection(Collection, Class)}.
     *
     */
    @Test
    @SuppressWarnings("unchecked")
    public void toTagList() {
        final List<Tag<String>> expected = new ArrayList<Tag<String>>(Arrays.asList(new Tag<String>("e1"), new Tag<String>("e2"),
                new Tag<String>("e3"), new Tag<String>("e4"), new Tag<String>("e5")));
        final List<String> target = new ArrayList<String>(Arrays.asList("e1", "e2", "e3", "e4", "e5"));

        Assert.assertEquals(new ArrayList<Tag<String>>(expected), CollectionUtil.toTagCollection(target, ArrayList.class));
        Assert.assertEquals(new HashSet<Tag<String>>(expected), CollectionUtil.toTagCollection(target, HashSet.class));
        Assert.assertEquals(new LinkedList<Tag<String>>(expected), CollectionUtil.toTagCollection(target, LinkedList.class));
    }
}
