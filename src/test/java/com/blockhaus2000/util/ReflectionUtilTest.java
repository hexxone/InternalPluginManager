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
public class ReflectionUtilTest {
    private String testField = "testFieldValue";

    @Test
    public void getField() {
        try {
            Assert.assertEquals(getClass().getDeclaredField("testField"), ReflectionUtil.getField(getClass(), "testField"));
            Assert.assertEquals(getClass().getDeclaredField("testField"),
                    ReflectionUtil.getField(ReflectionUtilTest.class, "testField"));
            Assert.assertEquals(ReflectionUtilTest.class.getDeclaredField("testField"),
                    ReflectionUtil.getField(getClass(), "testField"));
            Assert.assertEquals(ReflectionUtilTest.class.getDeclaredField("testField"),
                    ReflectionUtil.getField(ReflectionUtilTest.class, "testField"));

            Assert.assertEquals(getClass().getDeclaredField("testField"),
                    ReflectionUtil.getField(new ReflectionUtilTestInner().getClass(), "testField"));
            Assert.assertEquals(getClass().getDeclaredField("testField"),
                    ReflectionUtil.getField(ReflectionUtilTestInner.class, "testField"));
            Assert.assertEquals(ReflectionUtilTest.class.getDeclaredField("testField"),
                    ReflectionUtil.getField(new ReflectionUtilTestInner().getClass(), "testField"));
            Assert.assertEquals(ReflectionUtilTest.class.getDeclaredField("testField"),
                    ReflectionUtil.getField(ReflectionUtilTestInner.class, "testField"));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    public void getFieldValue() {
        Assert.assertEquals(testField, ReflectionUtil.getFieldValue(this, "testField"));
        Assert.assertEquals(testField, ReflectionUtil.getFieldValue(new ReflectionUtilTestInner(), "testField"));
    }

    @Test
    public void hasSuperclass() {
        Assert.assertFalse(ReflectionUtil.hasSuperclass(ReflectionUtilTest.class, Number.class));
        Assert.assertFalse(ReflectionUtil.hasSuperclass(getClass(), Number.class));
        Assert.assertTrue(ReflectionUtil.hasSuperclass(ReflectionUtilTestInner.class, ReflectionUtilTest.class));
        Assert.assertTrue(ReflectionUtil.hasSuperclass(ReflectionUtilTestInner.class, getClass()));
        Assert.assertTrue(ReflectionUtil.hasSuperclass(new ReflectionUtilTestInner().getClass(), ReflectionUtilTest.class));
        Assert.assertTrue(ReflectionUtil.hasSuperclass(new ReflectionUtilTestInner().getClass(), getClass()));
    }

    @Test
    public void setField() {
        try {
            ReflectionUtil.setField(this, "testField", "changedValue");
            Assert.assertEquals("changedValue", testField);
            testField = "testFieldValue";
        } catch (Exception ex) {
            testField = "testFieldValue";
            throw new RuntimeException(ex);
        }

        testField = "testFieldValue";
    }

    private class ReflectionUtilTestInner extends ReflectionUtilTest {
    }
}
