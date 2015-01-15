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
package com.blockhaus2000.ipmx.network.packet.request.packet;

import com.blockhaus2000.ipm.base.BitUtil;
import com.blockhaus2000.ipmx.network.packet.PacketID;
import com.blockhaus2000.ipmx.network.packet.request.RequestPacket;

/**
 * Represents the packet for requesting a list of all plugins.
 *
 */
public class PluginListRequestPacket implements RequestPacket {
    /**
     * Constructor of PluginListRequestPacket.
     *
     */
    public PluginListRequestPacket() {
        // nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipmx.network.packet.request.RequestPacket#getRequestData()
     */
    @Override
    public byte[][] getRequestData() {
        final byte[][] requestData = new byte[2][];

        requestData[0] = BitUtil.toByteArray(PacketID.REQUEST_PLUGIN_LIST.getId());
        requestData[1] = BitUtil.toByteArray(PacketID.TECHNICAL_PACKET_END.getId());

        return requestData;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipmx.network.packet.Packet#getPacketID()
     */
    @Override
    public PacketID getPacketID() {
        return PacketID.REQUEST_PLUGIN_LIST;
    }
}
