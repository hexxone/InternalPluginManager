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
package com.blockhaus2000.ipmx.network.packet.response;

import com.blockhaus2000.ipm.base.BitUtil;
import com.blockhaus2000.ipmx.network.exception.PacketFormatException;
import com.blockhaus2000.ipmx.network.packet.PacketID;
import com.blockhaus2000.ipmx.network.packet.response.packet.AccessDeniedResponsePacket;
import com.blockhaus2000.ipmx.network.packet.response.packet.AccessGrantedResponsePacket;
import com.blockhaus2000.ipmx.network.packet.response.packet.IllegalFormatResponsePacket;
import com.blockhaus2000.ipmx.network.packet.response.packet.PluginInformationResponsePacket;
import com.blockhaus2000.ipmx.network.packet.response.packet.PluginListResponsePacket;

/**
 * The response factory is used to simplify the handling of response packets.
 *
 */
public abstract class ResponsePacketFactory {
    /**
     * Constructor of ResponsePacketFactory.
     *
     */
    private ResponsePacketFactory() {
        // Utility classes should not have a visible constructor.
    }

    /**
     * Creates the sendable data (a byte array (<code>byte[]</code>)) from the
     * given response packet.
     *
     * @param responsePacket
     *            The response packet to read the data from.
     * @return A sendable <code>byte[]</code> that contains the data of the
     *         given response packet.
     */
    public static byte[] createResponsePacket(final ResponsePacket responsePacket) {
        assert responsePacket != null : "ResponsePacket cannot be null!";

        return BitUtil.toByteArray(responsePacket.getResponseData());
    }

    /**
     * Creates a response packet from the given sendable data. Can be used to
     * simply create a packet from the read packet data.
     *
     * @param responseData
     *            The response data to read from.
     * @return The created response packet that represents the data of the given
     *         response data.
     * @throws PacketFormatException
     *             If the packet format is wrong.
     */
    public static ResponsePacket createResponsePacket(final byte[] responseData) throws PacketFormatException {
        assert responseData != null : "ResponseData cannot be null!";

        final PacketID packetId = PacketID.getById(responseData[0]);

        if (packetId == null) {
            throw new PacketFormatException(String.format("Unknown packet ID 0x%02x!", responseData[0]));
        }
        if (packetId.getPacketIdType() != PacketID.Type.RESPONSE) {
            throw new PacketFormatException(String.format("Packet ID %s (0x%02x) is not a response header!", packetId,
                    responseData[0]));
        }

        final ResponsePacket responsePacket;
        switch (packetId) {
            case RESPONSE_ACCESS_DENIED:
                responsePacket = new AccessDeniedResponsePacket();
                break;
            case RESPONSE_ACCESS_GRANTED:
                responsePacket = new AccessGrantedResponsePacket();
                break;
            case RESPONSE_ILLEGAL_FORMAT:
                responsePacket = new IllegalFormatResponsePacket(responseData);
                break;
            case RESPONSE_PLUGIN_INFORMATION:
                responsePacket = new PluginInformationResponsePacket(responseData);
                break;
            case RESPONSE_PLUGIN_LIST:
                responsePacket = new PluginListResponsePacket(responseData);
                break;
            default:
                throw new PacketFormatException(
                        "Some packet IDs are not correctly mapped with their type! Please create an issues!");
        }

        return responsePacket;
    }
}
