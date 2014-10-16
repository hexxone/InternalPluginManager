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
package com.blockhaus2000.ipmx.network.packet.request.packet;

import java.util.Arrays;

import com.blockhaus2000.ipm.base.BitUtil;
import com.blockhaus2000.ipmx.network.packet.PacketID;
import com.blockhaus2000.ipmx.network.packet.request.RequestPacket;

/**
 * Represents the packet for requesting plugin information.
 *
 */
public class PluginInformationRequestPacket implements RequestPacket {
    /**
     * The plugin name to request information for.
     *
     */
    private final String pluginName;

    /**
     * Constructor of PluginInformationRequestPacket.
     *
     * @param pluginName
     *            The plugin name to request information for.
     */
    public PluginInformationRequestPacket(final String pluginName) {
        this.pluginName = pluginName;
    }

    /**
     * Creates a plugin information request packet out of the given
     * <code>byte[]</code>, that represents sendable data.
     *
     * @param requestData
     *            The <code>byte[]</code> to read the requested plugin name
     *            from. Must be a valid packet.
     */
    public PluginInformationRequestPacket(final byte[] requestData) {
        final int requestPluginNameLength = BitUtil.fromBytes(requestData[1], requestData[2], requestData[3], requestData[4]);

        final String requestPluginName = BitUtil.fromBytesToString(Arrays
                .copyOfRange(requestData, 5, requestPluginNameLength + 5));

        this.pluginName = requestPluginName;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipmx.network.packet.request.RequestPacket#getRequestData()
     */
    @Override
    public byte[][] getRequestData() {
        final byte[][] requestData = new byte[4][];

        requestData[0] = BitUtil.toByteArray(PacketID.REQUEST_PLUGIN_INFORMATION.getId());
        requestData[1] = BitUtil.toByteArray(this.pluginName.length());
        requestData[2] = BitUtil.toByteArray(this.pluginName);
        requestData[3] = BitUtil.toByteArray(PacketID.TECHNICAL_PACKET_END.getId());

        return requestData;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipmx.network.packet.Packet#getPacketID()
     */
    @Override
    public PacketID getPacketID() {
        return PacketID.REQUEST_PLUGIN_INFORMATION;
    }

    /**
     *
     * @return {@link PluginInformationRequestPacket#pluginName}
     */
    public String getPluginName() {
        return this.pluginName;
    }
}
