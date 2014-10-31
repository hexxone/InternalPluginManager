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
package com.blockhaus2000.ipmx.network.packet.response.packet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.blockhaus2000.ipm.base.BitUtil;
import com.blockhaus2000.ipmx.network.packet.PacketID;
import com.blockhaus2000.ipmx.network.packet.response.ResponsePacket;

/**
 * This packet represents the response of the server for a request for a whole
 * plugin list.
 *
 */
public class PluginListResponsePacket implements ResponsePacket {
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
     * <code>8</code>
     *
     */
    private static final int _8 = 8;

    /**
     * The whole plugin list.
     *
     */
    private final String[] plugins;

    /**
     * Constructor of PluginListResponsePacket.
     *
     * @param plugins
     *            The whole plugin list.
     */
    public PluginListResponsePacket(final String[] plugins) {
        this.plugins = plugins == null || plugins.length == 0 ? new String[0] : plugins;
    }

    /**
     * Constructs a plugin list response packet from the given, sendable data (a
     * <code>byte[]</code>).
     *
     * @param responseData
     *            The <code>byte[]</code> to read the data from.
     */
    public PluginListResponsePacket(final byte[] responseData) {
        assert responseData != null : "ResponseData cannot be null!";

        final int responsePluginCount = BitUtil.fromBytes(responseData[1], responseData[2],
                responseData[PluginListResponsePacket._3], responseData[PluginListResponsePacket._4]);
        final List<String> pluginsList = new ArrayList<String>();

        final List<List<Integer>> indexes = new LinkedList<List<Integer>>();
        for (int i = 0; i < responsePluginCount; i++) {
            final List<Integer> curIndexes = new LinkedList<Integer>();

            final int start = (i == 0 ? PluginListResponsePacket._8 : indexes.get(indexes.size() - 1).get(1)
                    + PluginListResponsePacket._4) + 1;
            final int end = BitUtil.fromBytes(responseData[start - PluginListResponsePacket._4], responseData[start
                    - PluginListResponsePacket._3], responseData[start - 2], responseData[start - 1])
                    + start;

            curIndexes.add(start);
            curIndexes.add(end);
            indexes.add(curIndexes);
        }

        for (final List<Integer> lst : indexes) {
            pluginsList.add(BitUtil.fromBytesToString(Arrays.copyOfRange(responseData, lst.get(0), lst.get(1))));
        }

        this.plugins = pluginsList.toArray(new String[pluginsList.size()]);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipmx.network.packet.response.ResponsePacket#getResponseData()
     */
    @Override
    public byte[][] getResponseData() {
        final List<byte[]> rawResponseData = new LinkedList<byte[]>();

        rawResponseData.add(BitUtil.toByteArray(PacketID.RESPONSE_PLUGIN_LIST.getId()));
        rawResponseData.add(BitUtil.toByteArray(this.plugins.length));
        for (final String pluginName : this.plugins) {
            rawResponseData.add(BitUtil.toByteArray(pluginName.length()));
            rawResponseData.add(BitUtil.toByteArray(pluginName));
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
        return PacketID.RESPONSE_PLUGIN_LIST;
    }

    /**
     *
     * @return {@link PluginListResponsePacket#plugins}
     */
    public String[] getPlugins() {
        return this.plugins;
    }
}
