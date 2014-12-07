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
package com.blockhaus2000.ipm.base;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests {@link BitUtil}.
 *
 */
public class BitUtilTest {
    /**
     * Tests {@link BitUtil#toByteArray(byte...)},
     * {@link BitUtil#toByteArray(short...)},
     * {@link BitUtil#toByteArray(int...)}, {@link BitUtil#toByteArray(long...)}
     * and {@link BitUtil#toByteArray(byte[][])}.
     *
     */
    @Test
    public void testToByteArray() {
        Assert.assertArrayEquals(new byte[] { 0x01 }, BitUtil.toByteArray((byte) 0x01));
        Assert.assertArrayEquals(new byte[] { 0x01, 0x10 }, BitUtil.toByteArray((byte) 0x01, (byte) 0x10));

        Assert.assertArrayEquals(new byte[] { 0x01, 0x02 }, BitUtil.toByteArray((short) 0x0102));
        Assert.assertArrayEquals(new byte[] { 0x01, 0x02, 0x10, 0x20 }, BitUtil.toByteArray((short) 0x0102, (short) 0x1020));

        Assert.assertArrayEquals(new byte[] { 0x01, 0x02, 0x03, 0x04 }, BitUtil.toByteArray(0x01020304));
        Assert.assertArrayEquals(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x10, 0x20, 0x30, 0x40 },
                BitUtil.toByteArray(0x01020304, 0x10203040));

        Assert.assertArrayEquals(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08 },
                BitUtil.toByteArray(0x0102030405060708L));
        Assert.assertArrayEquals(new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x10, 0x20, 0x30, 0x40, 0x50, 0x60,
                0x70, (byte) 0x80 }, BitUtil.toByteArray(0x0102030405060708L, 0x1020304050607080L));

        Assert.assertArrayEquals(
                new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15,
                        0x16, 0x17, 0x18, 0x19 },
                BitUtil.toByteArray(new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09 }, new byte[] { 0x10,
                        0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19 }));

        Assert.assertArrayEquals(new byte[] { (byte) 0x01 }, BitUtil.toByteArray(true));
        Assert.assertArrayEquals(new byte[] { (byte) 0x00 }, BitUtil.toByteArray(false));

        Assert.assertArrayEquals(new byte[] { 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38 }, BitUtil.toByteArray("12345678"));
        Assert.assertArrayEquals(new byte[] { 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36,
                0x37, 0x38 }, BitUtil.toByteArray("12345678", "12345678"));
        Assert.assertArrayEquals(new byte[] { 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36,
                0x37, 0x38 }, BitUtil.toByteArray("12345678", null, "12345678"));
    }

    /**
     * Tests {@link BitUtil#fromBytesToBoolean(byte)}.
     *
     */
    @Test
    public void testFromByteToBoolean() {
        Assert.assertEquals(true, BitUtil.fromBytesToBoolean((byte) 0x01));
        Assert.assertEquals(false, BitUtil.fromBytesToBoolean((byte) 0x00));
    }

    /**
     * Tests {@link BitUtil#fromBytesToString(byte...)}.
     *
     */
    @Test
    public void testFromBytesToString() {
        Assert.assertEquals("12345678", BitUtil.fromBytesToString((byte) 0x31, (byte) 0x32, (byte) 0x33, (byte) 0x34,
                (byte) 0x35, (byte) 0x36, (byte) 0x37, (byte) 0x38));
    }

    /**
     * Tests {@link BitUtil#fromBytes(byte)},
     * {@link BitUtil#fromBytes(byte, byte)},
     * {@link BitUtil#fromBytes(byte, byte, byte, byte)} and
     * {@link BitUtil#fromBytes(byte, byte, byte, byte, byte, byte, byte, byte)}
     * .
     *
     */
    @Test
    public void testFromBytes() {
        Assert.assertEquals(0x01, BitUtil.fromBytes((byte) 0x01));

        Assert.assertEquals(0x0102, BitUtil.fromBytes((byte) 0x01, (byte) 0x02));

        Assert.assertEquals(0x01020304, BitUtil.fromBytes((byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04));

        Assert.assertEquals(0x0102030405060708L, BitUtil.fromBytes((byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
                (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08));
    }
}
