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
package com.blockhaus2000.ipmx.network;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.blockhaus2000.ipmx.network.packet.PacketID;

/**
 * This is a utility class to work with an IPMNE server.
 *
 */
public final class ServerUtil {
    /**
     * Constructor of ServerUtil.
     *
     */
    private ServerUtil() {
        // Utility classes should not have a visible constructor.
    }

    /**
     * Reads the data of a whole packet from the given input stream
     *
     * @param in
     *            The input stream to read the data from.
     * @return The read data.
     * @throws IOException
     *             If an I/O error occurres.
     */
    public static byte[] readPacket(final InputStream in) throws IOException {
        assert in != null : "In cannot be null!";

        final DataInputStream dataIn = new DataInputStream(in);

        final List<Byte> rawData = new LinkedList<Byte>();
        while (true) {
            try {
                final byte b = dataIn.readByte();
                if (b == PacketID.TECHNICAL_PACKET_END.getId()) {
                    break;
                }
                rawData.add(b);
            } catch (final EOFException dummy) {
                break;
            }
        }

        final byte[] data = new byte[rawData.size()];
        for (int i = 0; i < rawData.size(); i++) {
            data[i] = rawData.get(i);
        }

        return data;
    }
}
