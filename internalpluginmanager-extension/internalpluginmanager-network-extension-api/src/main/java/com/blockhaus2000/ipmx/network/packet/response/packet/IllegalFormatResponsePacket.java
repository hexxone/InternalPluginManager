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

import java.util.Arrays;

import com.blockhaus2000.ipm.base.BitUtil;
import com.blockhaus2000.ipmx.network.packet.PacketID;
import com.blockhaus2000.ipmx.network.packet.response.ResponsePacket;

/**
 * Represents the response of the server if a packet with an illegal format was
 * received.
 *
 */
public class IllegalFormatResponsePacket implements ResponsePacket {
    /**
     * The error message.
     *
     */
    private final String message;

    /**
     * Constructor of IllegalFormatResponsePacket.
     *
     * @param message
     *            The error message.
     */
    public IllegalFormatResponsePacket(final String message) {
        this.message = message;
    }

    /**
     * Constructs an illegal format response packet from the data of the given
     * <code>byte[]</code>.
     *
     * @param responseData
     *            The <code>byte[]</code> to read the data from.
     */
    public IllegalFormatResponsePacket(final byte[] responseData) {
        final int start = 5;
        final int end = start + BitUtil.fromBytes(responseData[1], responseData[2], responseData[3], responseData[4]);
        final String message = BitUtil.fromBytesToString(Arrays.copyOfRange(responseData, start, end));

        this.message = message;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipmx.network.packet.response.ResponsePacket#getResponseData()
     */
    @Override
    public byte[][] getResponseData() {
        final byte[][] responseData = new byte[4][];

        responseData[0] = BitUtil.toByteArray(PacketID.RESPONSE_ILLEGAL_FORMAT.getId());
        responseData[1] = BitUtil.toByteArray(this.message.length());
        responseData[2] = BitUtil.toByteArray(this.message);
        responseData[3] = BitUtil.toByteArray(PacketID.TECHNICAL_PACKET_END.getId());

        return responseData;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipmx.network.packet.Packet#getPacketID()
     */
    @Override
    public PacketID getPacketID() {
        return PacketID.RESPONSE_ILLEGAL_FORMAT;
    }

    /**
     *
     * @return {@link IllegalFormatResponsePacket#message}
     */
    public String getMessage() {
        return this.message;
    }
}
