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

/**
 * This utility class simpilfies the usage of bitwise operations.
 *
 */
public final class BitUtil {
    // Suppress Checkstyle warnings (Magic Number).
    /**
     * <code>3</code>
     *
     */
    private static final int _3 = 3;
    /**
     * <code>4</code>
     *
     */
    private static final int _4 = 4;
    /**
     * <code>5</code>
     *
     */
    private static final int _5 = 5;
    /**
     * <code>6</code>
     *
     */
    private static final int _6 = 6;
    /**
     * <code>7</code>
     *
     */
    private static final int _7 = 7;
    /**
     * <code>8</code>
     *
     */
    private static final int _8 = 8;

    // Masks.
    /**
     * <code>0x00000000000000ffL</code>
     *
     */
    private static final long MASK_FIRST_BYTE = 0x00000000000000ffL;
    /**
     * <code>0x000000000000ff00L</code>
     *
     */
    private static final long MASK_SECOND_BYTE = 0x000000000000ff00L;
    /**
     * <code>0x0000000000ff0000L</code>
     *
     */
    private static final long MASK_THIRD_BYTE = 0x0000000000ff0000L;
    /**
     * <code>0x00000000ff000000L</code>
     *
     */
    private static final long MASK_FOURTH_BYTE = 0x00000000ff000000L;
    /**
     * <code>0x000000ff00000000L</code>
     *
     */
    private static final long MASK_FIFTH_BYTE = 0x000000ff00000000L;
    /**
     * <code>0x0000ff0000000000L</code>
     *
     */
    private static final long MASK_SIXTH_BYTE = 0x0000ff0000000000L;
    /**
     * <code>0x00ff000000000000L</code>
     *
     */
    private static final long MASK_SEVENTH_BYTE = 0x00ff000000000000L;
    /**
     * <code>0xff00000000000000L</code>
     *
     */
    private static final long MASK_EIGTH_BYTE = 0xff00000000000000L;

    /**
     * Constructor of BitUtil.
     *
     */
    private BitUtil() {
        // Utility classes should not have a visible constructor.
    }

    /**
     * Joins the given bytes to one byte array.
     *
     * @param bytes
     *            The bytes to join.
     * @return The joined byte array.
     */
    public static byte[] toByteArray(final byte... bytes) {
        final byte[] result = new byte[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            result[i] = bytes[i];
        }

        return result;
    }

    /**
     * Converts every given short to a byte array (a short with the value
     * <code>0x1234</code> will be converted into a byte array, where the first
     * element is <code>0x12</code> and the seconds element is <code>0x34</code>
     * ) and joins them.
     *
     * @param shorts
     *            The shorts to convert and join.
     * @return The converted and joined shorts.
     */
    public static byte[] toByteArray(final short... shorts) {
        final byte[] result = new byte[shorts.length * 2];

        for (int i = 0; i < shorts.length * 2; i = i + 2) {
            final short s = shorts[i / 2];
            result[i] = (byte) ((s & BitUtil.MASK_SECOND_BYTE) >> BitUtil._8);
            result[i + 1] = (byte) (s & BitUtil.MASK_FIRST_BYTE);
        }

        return result;
    }

    /**
     * Acts like {@link BitUtil#toByteArray(short...)}, but with ints.
     *
     * @param ints
     *            The ints to convert and join.
     * @return The converted and joined ints.
     */
    public static byte[] toByteArray(final int... ints) {
        final byte[] result = new byte[ints.length * BitUtil._4];

        for (int i = 0; i < ints.length * BitUtil._4; i = i + BitUtil._4) {
            final int j = ints[i / BitUtil._4];
            result[i] = (byte) ((j & BitUtil.MASK_FOURTH_BYTE) >> BitUtil._8 * BitUtil._3);
            result[i + 1] = (byte) ((j & BitUtil.MASK_THIRD_BYTE) >> BitUtil._8 * 2);
            result[i + 2] = (byte) ((j & BitUtil.MASK_SECOND_BYTE) >> BitUtil._8);
            result[i + BitUtil._3] = (byte) (j & BitUtil.MASK_FIRST_BYTE);
        }

        return result;
    }

    /**
     * Acts like {@link BitUtil#toByteArray(short...)}, but with ints.
     *
     * @param longs
     *            The longs to convert and join.
     * @return The converted and joined longs.
     */
    public static byte[] toByteArray(final long... longs) {
        final byte[] result = new byte[longs.length * BitUtil._8];

        for (int i = 0; i < longs.length * BitUtil._8; i = i + BitUtil._8) {
            final long l = longs[i / BitUtil._8];
            result[i] = (byte) ((l & BitUtil.MASK_EIGTH_BYTE) >> BitUtil._8 * BitUtil._7);
            result[i + 1] = (byte) ((l & BitUtil.MASK_SEVENTH_BYTE) >> BitUtil._8 * BitUtil._6);
            result[i + 2] = (byte) ((l & BitUtil.MASK_SIXTH_BYTE) >> BitUtil._8 * BitUtil._5);
            result[i + BitUtil._3] = (byte) ((l & BitUtil.MASK_FIFTH_BYTE) >> BitUtil._8 * BitUtil._4);
            result[i + BitUtil._4] = (byte) ((l & BitUtil.MASK_FOURTH_BYTE) >> BitUtil._8 * BitUtil._3);
            result[i + BitUtil._5] = (byte) ((l & BitUtil.MASK_THIRD_BYTE) >> BitUtil._8 * 2);
            result[i + BitUtil._6] = (byte) ((l & BitUtil.MASK_SECOND_BYTE) >> BitUtil._8);
            result[i + BitUtil._7] = (byte) (l & BitUtil.MASK_FIRST_BYTE);
        }

        return result;
    }

    /**
     * Converts the given boolean into a byte (if the boolean is
     * <code>true</code>, <code>0x01</code>. Otherwise <code>0x00</code>). This
     * byte is packed into a byte array.
     *
     * @param bool
     *            The boolean to convert and pack into a byte array.
     * @return The converted boolean packed into a byte array.
     */
    public static byte[] toByteArray(final boolean bool) {
        return new byte[] { (byte) (bool ? 0x01 : 0x00) };
    }

    /**
     * Joins the given Strings and converty every String character to a byte.
     *
     * @param strings
     *            The Strings to join and convert.
     * @return The joined and converted Strings.
     */
    public static byte[] toByteArray(final String... strings) {
        final StringBuilder builder = new StringBuilder();
        for (final String s : strings) {
            builder.append(s);
        }
        final String str = builder.toString();

        final byte[] result = new byte[str.length()];

        final byte[] bytes = str.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            result[i] = bytes[i];
        }

        return result;
    }

    /**
     * Joins all given byte arrays to one array.
     *
     * @param bytes
     *            The byte arrays to join.
     * @return The joined byte arrays.
     */
    public static byte[] toByteArray(final byte[]... bytes) {
        int length = 0;
        for (final byte[] bs : bytes) {
            length = length + bs.length;
        }

        final byte[] result = new byte[length];

        int i = 0;
        for (final byte[] bs : bytes) {
            for (final byte b : bs) {
                result[i] = b;
                i++;
            }
        }

        return result;
    }

    /**
     * Converts the given byte into a boolean (<code>0x01</code> means
     * <code>true</code>, every other value <code>false</code>).
     *
     * @param b0
     *            The byte to convert into a boolean.
     * @return The converted byte.
     */
    public static boolean fromBytesToBoolean(final byte b0) {
        return b0 == (byte) 0x01;
    }

    /**
     * Converts the given byte array into a String.
     *
     * @param bytes
     *            The bytes to convert into a String.
     * @return The converted bytes.
     */
    public static String fromBytesToString(final byte... bytes) {
        final StringBuilder builder = new StringBuilder();
        for (final byte b : bytes) {
            builder.append((char) b);
        }
        return builder.toString();
    }

    /**
     * Does nothing.
     *
     * @param b0
     *            Will be returned.
     * @return <code>b0</code>
     */
    public static byte fromBytes(final byte b0) {
        return b0;
    }

    /**
     * Builds an integer from the given bytes. Work like
     * {@link BitUtil#toByteArray(short...)}, but only one number and the other
     * way.
     *
     * @param b0
     *            The first byte.
     * @param b1
     *            The seconds byte.
     * @return The joined short.
     */
    public static short fromBytes(final byte b0, final byte b1) {
        short result = 0;

        result = (short) (result | b0 << BitUtil._8);
        result = (short) (result | b1);

        return result;
    }

    /**
     * Works like {@link BitUtil#fromBytes(byte, byte)}, but with four bytes and
     * an integer.
     *
     * @param b0
     *            The first byte.
     * @param b1
     *            The seconds byte.
     * @param b2
     *            The third byte.
     * @param b3
     *            The fourth byte.
     * @return The joined int.
     */
    public static int fromBytes(final byte b0, final byte b1, final byte b2, final byte b3) {
        int result = 0;

        result = result | b0 << BitUtil._8 * BitUtil._3;
        result = result | b1 << BitUtil._8 * 2;
        result = result | b2 << BitUtil._8;
        result = result | b3;

        return result;
    }

    /**
     * Works like {@link BitUtil#fromBytes(byte, byte)}, but with eigth bytes
     * and a long.
     *
     * @param b0
     *            The first byte.
     * @param b1
     *            The seconds byte.
     * @param b2
     *            The third byte.
     * @param b3
     *            The fourth byte.
     * @param b4
     *            The fifth byte.
     * @param b5
     *            The sixth byte.
     * @param b6
     *            The seventh byte.
     * @param b7
     *            The eighth byte.
     * @return The joined long.
     */
    public static long fromBytes(final byte b0, final byte b1, final byte b2, final byte b3, final byte b4, final byte b5,
            final byte b6, final byte b7) {
        long result = 0;

        result = result | (long) b0 << BitUtil._8 * BitUtil._7;
        result = result | (long) b1 << BitUtil._8 * BitUtil._6;
        result = result | (long) b2 << BitUtil._8 * BitUtil._5;
        result = result | (long) b3 << BitUtil._8 * BitUtil._4;
        result = result | (long) b4 << BitUtil._8 * BitUtil._3;
        result = result | (long) b5 << BitUtil._8 * 2;
        result = result | (long) b6 << BitUtil._8;
        result = result | b7;

        return result;
    }
}
