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
package com.blockhaus2000.ipmx.network.packet.request;

import com.blockhaus2000.ipm.base.BitUtil;
import com.blockhaus2000.ipmx.network.exception.PacketFormatException;
import com.blockhaus2000.ipmx.network.packet.PacketID;
import com.blockhaus2000.ipmx.network.packet.request.packet.PluginInformationRequestPacket;
import com.blockhaus2000.ipmx.network.packet.request.packet.PluginListRequestPacket;

/**
 * The request factory is used to simplify the handling of request packets.
 *
 */
public class RequestPacketFactory {
    /**
     * Creates the sendable data (a byte array (<code>byte[]</code>)) from the
     * given request packet.
     *
     * @param requestPacket
     *            The request packet to read the data from.
     * @return A sendable <code>byte[]</code> that contains the data of the
     *         given request packet.
     */
    public static byte[] createRequestPacket(final RequestPacket requestPacket) {
        return BitUtil.toByteArray(requestPacket.getRequestData());
    }

    /**
     * Creates a request packet from the given sendable data. Can be used to
     * simply create a packet from the read packet data.
     *
     * @param requestData
     *            The request data to read from.
     * @return The created request packet that represents the data of the given
     *         request data.
     * @throws PacketFormatException
     *             If the packet format is wrong.
     */
    public static RequestPacket createRequestPacket(final byte[] requestData) throws PacketFormatException {
        final PacketID packetId = PacketID.getById(requestData[0]);

        if (packetId == null) {
            throw new PacketFormatException(String.format("Unknown packet ID 0x%02x!", requestData[0]));
        }
        if (packetId.getPacketIdType() != PacketID.Type.REQUEST) {
            throw new PacketFormatException(String.format("Packet ID %s (0x%02x) is not a request header!", packetId,
                    requestData[0]));
        }

        final RequestPacket requestPacket;
        switch (packetId) {
            case REQUEST_PLUGIN_INFORMATION:
                requestPacket = new PluginInformationRequestPacket(requestData);
                break;
            case REQUEST_PLUGIN_LIST:
                requestPacket = new PluginListRequestPacket();
                break;
            default:
                throw new PacketFormatException(
                        "Some packet IDs are not correctly mapped with their type! Please create an issues!");
        }

        return requestPacket;
    }
}
