/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014-2015 Fabian Damken
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
 */
package com.blockhaus2000.ipmx.network.packet.response.packet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.blockhaus2000.ipm.base.BitUtil;
import com.blockhaus2000.ipmx.network.packet.PacketID;
import com.blockhaus2000.ipmx.network.packet.response.ResponsePacket;

/**
 * This packet is the response for a request for the information about a
 * specific plugin.
 *
 */
public class PluginInformationResponsePacket implements ResponsePacket {
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
    /**
     * <code>9</code>
     *
     */
    private static final int _9 = 9;
    /**
     * <code>10</code>
     *
     */
    private static final int _10 = 10;
    /**
     * <code>11</code>
     *
     */
    private static final int _11 = 12;
    /**
     * <code>12</code>
     *
     */
    private static final int _12 = 12;
    /**
     * <code>13</code>
     *
     */
    private static final int _13 = 13;
    /**
     * <code>14</code>
     *
     */
    private static final int _14 = 14;
    /**
     * <code>15</code>
     *
     */
    private static final int _15 = 15;
    /**
     * <code>16</code>
     *
     */
    private static final int _16 = 16;
    /**
     * <code>17</code>
     *
     */
    private static final int _17 = 17;
    /**
     * <code>18</code>
     *
     */
    private static final int _18 = 18;

    /**
     * The plugin name.
     *
     */
    private final String pluginName;
    /**
     * The plugin version.
     *
     */
    private final String pluginVersion;
    /**
     * The plugin author (can be <code>null</code>).
     *
     */
    private final String pluginAuthor;
    /**
     * The plugin dependencies (can be empty).
     *
     */
    private final String[] pluginDependencies;
    /**
     * Whether the plugin is enabled.
     *
     */
    private final boolean pluginEnabled;

    /**
     * Constructor of PluginInformationResponsePacket.
     *
     * @param pluginName
     *            The plugin name.
     * @param pluginVersion
     *            The plugin version.
     * @param pluginAuthor
     *            The plugin author. Can be <code>null</code>.
     * @param pluginDependencies
     *            The plugin dependencies. Can be empty.
     * @param pluginEnabled
     *            Whether the plugin is enabled.
     */
    public PluginInformationResponsePacket(final String pluginName, final String pluginVersion, final String pluginAuthor,
            final String[] pluginDependencies, final boolean pluginEnabled) {
        assert pluginName != null : "PluginName cannot be null!";
        assert pluginVersion != null : "PluginVersion cannot be null!";

        this.pluginName = pluginName;
        this.pluginVersion = pluginVersion;
        this.pluginAuthor = pluginAuthor == null || pluginAuthor.isEmpty() ? null : pluginAuthor;
        this.pluginDependencies = pluginDependencies == null || pluginDependencies.length == 0 ? new String[0]
                : pluginDependencies;
        this.pluginEnabled = pluginEnabled;
    }

    /**
     * Constructs a plugin information response packet from the data of the
     * given <code>byte[]</code>.
     *
     * @param responseData
     *            The <code>byte[]</code> to read the data from.
     */
    public PluginInformationResponsePacket(final byte[] responseData) {
        assert responseData != null : "ResponseData cannot be null!";

        final int responsePluginNameLength = BitUtil.fromBytes(responseData[1], responseData[2],
                responseData[PluginInformationResponsePacket._3], responseData[PluginInformationResponsePacket._4]);
        final int responsePluginVersionLength = BitUtil.fromBytes(responseData[PluginInformationResponsePacket._5],
                responseData[PluginInformationResponsePacket._6], responseData[PluginInformationResponsePacket._7],
                responseData[PluginInformationResponsePacket._8]);
        final int responsePluginAuthorLength = BitUtil.fromBytes(responseData[PluginInformationResponsePacket._9],
                responseData[PluginInformationResponsePacket._10], responseData[PluginInformationResponsePacket._11],
                responseData[PluginInformationResponsePacket._12]);
        final int responsePluginDependencyLength = BitUtil.fromBytes(responseData[PluginInformationResponsePacket._13],
                responseData[PluginInformationResponsePacket._14], responseData[PluginInformationResponsePacket._15],
                responseData[PluginInformationResponsePacket._16]);
        final boolean responsePluginEnabled = BitUtil.fromBytesToBoolean(responseData[PluginInformationResponsePacket._17]);

        int index = PluginInformationResponsePacket._18;
        final String responsePluginName = BitUtil.fromBytesToString(Arrays.copyOfRange(responseData, index, index
                + responsePluginNameLength));
        index = index + responsePluginNameLength;

        final String responseVersion = BitUtil.fromBytesToString(Arrays.copyOfRange(responseData, index, index
                + responsePluginVersionLength));
        index = index + responsePluginVersionLength;

        final String responseAuthor = BitUtil.fromBytesToString(Arrays.copyOfRange(responseData, index, index
                + responsePluginAuthorLength));
        index = index + responsePluginAuthorLength;

        final List<String> dependencies = new ArrayList<String>();

        index = index + PluginInformationResponsePacket._3;
        final List<List<Integer>> indexes = new LinkedList<List<Integer>>();
        for (int i = 0; i < responsePluginDependencyLength; i++) {
            final List<Integer> curIndexes = new LinkedList<Integer>();

            final int start = (i == 0 ? index : indexes.get(indexes.size() - 1).get(1) + PluginInformationResponsePacket._4) + 1;
            final int end = BitUtil.fromBytes(responseData[start - PluginInformationResponsePacket._4], responseData[start
                    - PluginInformationResponsePacket._3], responseData[start - 2], responseData[start - 1])
                    + start - 1;

            curIndexes.add(start);
            curIndexes.add(end);
            indexes.add(curIndexes);
        }

        for (final List<Integer> lst : indexes) {
            dependencies.add(BitUtil.fromBytesToString(Arrays.copyOfRange(responseData, lst.get(0), lst.get(1) + 1)));
        }

        this.pluginName = responsePluginName;
        this.pluginVersion = responseVersion;
        this.pluginAuthor = responseAuthor == null || responseAuthor.isEmpty() ? null : responseAuthor;
        this.pluginDependencies = dependencies.toArray(new String[dependencies.size()]);
        this.pluginEnabled = responsePluginEnabled;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipmx.network.packet.response.ResponsePacket#getResponseData()
     */
    @Override
    public byte[][] getResponseData() {
        final List<byte[]> rawResponseData = new LinkedList<byte[]>();

        rawResponseData.add(BitUtil.toByteArray(PacketID.RESPONSE_PLUGIN_INFORMATION.getId()));
        rawResponseData.add(BitUtil.toByteArray(this.pluginName == null ? 0 : this.pluginName.length()));
        rawResponseData.add(BitUtil.toByteArray(this.pluginVersion == null ? 0 : this.pluginVersion.length()));
        rawResponseData.add(BitUtil.toByteArray(this.pluginAuthor == null ? 0 : this.pluginAuthor.length()));
        rawResponseData.add(BitUtil.toByteArray(this.pluginDependencies == null ? 0 : this.pluginDependencies.length));
        rawResponseData.add(BitUtil.toByteArray(this.pluginEnabled));
        if (this.pluginName != null) {
            rawResponseData.add(BitUtil.toByteArray(this.pluginName));
        }
        if (this.pluginVersion != null) {
            rawResponseData.add(BitUtil.toByteArray(this.pluginVersion));
        }
        if (this.pluginAuthor != null) {
            rawResponseData.add(BitUtil.toByteArray(this.pluginAuthor));
        }
        if (this.pluginDependencies != null) {
            for (final String s : this.pluginDependencies) {
                rawResponseData.add(BitUtil.toByteArray(s.length()));
                rawResponseData.add(BitUtil.toByteArray(s));
            }
        }
        rawResponseData.add(BitUtil.toByteArray(PacketID.TECHNICAL_PACKET_END.getId()));

        final byte[][] responseData = new byte[rawResponseData.size()][];
        for (int i = 0; i < rawResponseData.size(); i++) {
            responseData[i] = rawResponseData.get(i);
        }

        return responseData;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipmx.network.packet.Packet#getPacketID()
     */
    @Override
    public PacketID getPacketID() {
        return PacketID.RESPONSE_PLUGIN_INFORMATION;
    }

    /**
     *
     * @return {@link PluginInformationResponsePacket#pluginAuthor}
     */
    public String getPluginAuthor() {
        return this.pluginAuthor;
    }

    /**
     *
     * @return {@link PluginInformationResponsePacket#pluginName}
     */
    public String getPluginName() {
        return this.pluginName;
    }

    /**
     *
     * @return {@link PluginInformationResponsePacket#pluginVersion}
     */
    public String getPluginVersion() {
        return this.pluginVersion;
    }

    /**
     *
     * @return {@link PluginInformationResponsePacket#pluginDependencies}
     */
    public String[] getPluginDependencies() {
        return this.pluginDependencies;
    }

    /**
     *
     * @return {@link PluginInformationResponsePacket#pluginEnabled}
     */
    public boolean isPluginEnabled() {
        return this.pluginEnabled;
    }
}
