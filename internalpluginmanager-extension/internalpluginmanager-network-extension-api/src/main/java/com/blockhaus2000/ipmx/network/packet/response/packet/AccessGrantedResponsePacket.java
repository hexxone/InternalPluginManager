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

import com.blockhaus2000.ipm.base.BitUtil;
import com.blockhaus2000.ipmx.network.packet.PacketID;
import com.blockhaus2000.ipmx.network.packet.response.ResponsePacket;

/**
 * Represents the response that is sent if the access is granted by the server.
 *
 */
public class AccessGrantedResponsePacket implements ResponsePacket {
    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipmx.network.packet.response.ResponsePacket#getResponseData()
     */
    @Override
    public byte[][] getResponseData() {
        final byte[][] responseData = new byte[2][];

        responseData[0] = BitUtil.toByteArray(PacketID.RESPONSE_ACCESS_GRANTED.getId());
        responseData[1] = BitUtil.toByteArray(PacketID.TECHNICAL_PACKET_END.getId());

        return responseData;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipmx.network.packet.Packet#getPacketID()
     */
    @Override
    public PacketID getPacketID() {
        return PacketID.RESPONSE_ACCESS_GRANTED;
    }
}
