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
package com.blockhaus2000.ipmx.network;

import java.io.IOException;
import java.net.Socket;

import com.blockhaus2000.ipmx.network.exception.AccessDeniedException;
import com.blockhaus2000.ipmx.network.exception.PacketFormatException;
import com.blockhaus2000.ipmx.network.packet.request.RequestPacketFactory;
import com.blockhaus2000.ipmx.network.packet.request.packet.PluginInformationRequestPacket;
import com.blockhaus2000.ipmx.network.packet.request.packet.PluginListRequestPacket;
import com.blockhaus2000.ipmx.network.packet.response.ResponsePacket;
import com.blockhaus2000.ipmx.network.packet.response.ResponsePacketFactory;
import com.blockhaus2000.ipmx.network.packet.response.packet.IllegalFormatResponsePacket;
import com.blockhaus2000.ipmx.network.packet.response.packet.PluginInformationResponsePacket;
import com.blockhaus2000.ipmx.network.packet.response.packet.PluginListResponsePacket;

/**
 * The {@link RequestConnection} is an extension for the base {@link Connection}
 * to easy speak with a IPMNE server.
 *
 */
public final class RequestConnection extends Connection {
    /**
     * Constructor of RequestConnection. Only invokes <code>super(Socket)</code>
     * .
     *
     * @param socket
     *            Is passed into <code>super</code>-call.
     * @throws IOException
     *             If the <code>super</code>-call throws it.
     */
    private RequestConnection(final Socket socket) throws IOException {
        super(socket);
    }

    /**
     * Creates a request for all plugins to the IPMNE server.
     *
     * @return The {@link PluginListResponsePacket} from which you can read the
     *         whole plugin list.
     * @throws IOException
     *             If an I/O error occurres.
     * @throws PacketFormatException
     *             If the the server returned an illegal packet.
     */
    public PluginListResponsePacket getPluginList() throws IOException, PacketFormatException {
        this.out().write(RequestPacketFactory.createRequestPacket(new PluginListRequestPacket()));

        final ResponsePacket responsePacket = ResponsePacketFactory.createResponsePacket(ServerUtil.readPacket(this.in()));
        switch (responsePacket.getPacketID()) {
            case RESPONSE_PLUGIN_LIST:
                return (PluginListResponsePacket) responsePacket;
            case RESPONSE_ILLEGAL_FORMAT:
                throw new PacketFormatException(((IllegalFormatResponsePacket) responsePacket).getMessage());
            default:
                throw new PacketFormatException(String.format("Unexpected response packet with ID 0x%02x", responsePacket
                        .getPacketID().getId()));
        }
    }

    /**
     * Creates a request for a detailed plugin information for thw plugin with
     * the given name to the IPMNE server.
     *
     * @param pluginName
     *            The plugin name to lookup.
     * @return The {@link PluginInformationResponsePacket} from where you can
     *         read all get plugin information.
     * @throws IOException
     *             If an I/O error occurres.
     * @throws PacketFormatException
     *             If the the server returned an illegal packet.
     */
    public PluginInformationResponsePacket getPluginInformation(final String pluginName) throws IOException,
            PacketFormatException {
        assert pluginName != null : "PluginName cannot be null or empty!";

        this.out().write(RequestPacketFactory.createRequestPacket(new PluginInformationRequestPacket(pluginName)));

        final ResponsePacket responsePacket = ResponsePacketFactory.createResponsePacket(ServerUtil.readPacket(this.in()));
        switch (responsePacket.getPacketID()) {
            case RESPONSE_PLUGIN_INFORMATION:
                return (PluginInformationResponsePacket) responsePacket;
            case RESPONSE_ILLEGAL_FORMAT:
                throw new PacketFormatException(((IllegalFormatResponsePacket) responsePacket).getMessage());
            default:
                throw new PacketFormatException(String.format("Unexpected response packet with ID 0x%02x", responsePacket
                        .getPacketID().getId()));

        }
    }

    /**
     * The factory is used to connect to a IPMNE server and checks connection
     * rights, etc.
     *
     */
    public abstract static class Factory {
        /**
         * Constructor of AbstractFactory.
         *
         */
        private Factory() {
            // Utility classes should not have a visible constructor.
        }

        /**
         * Creates a new {@link RequestConnection}.
         *
         * @param host
         *            The host to connect to.
         * @param port
         *            The port to connect to.
         * @return The created {@link RequestConnection}.
         * @throws IOException
         *             If an I/O error occurress.
         * @throws AccessDeniedException
         *             If the connection was denied from the IPMNE server.
         */
        public static RequestConnection createRequestConnection(final String host, final int port) throws IOException,
                AccessDeniedException {
            assert host != null : "Host cannot be null!";
            assert port > 0 : "Port cannot be <=0!";

            final RequestConnection con = new RequestConnection(new Socket(host, port));

            ResponsePacket responsePacket;
            try {
                responsePacket = ResponsePacketFactory.createResponsePacket(ServerUtil.readPacket(con.in()));
            } catch (final PacketFormatException cause) {
                // Just pack this exception into a runtime exception, because it
                // can not be handled as well as it should be handled.
                // This should also NEVER happen.
                throw new RuntimeException(cause);
            }
            switch (responsePacket.getPacketID()) {
                case RESPONSE_ACCESS_GRANTED:
                    // Access granted. Nothing to do.
                    break;
                default:
                    throw new AccessDeniedException("The access was denied!");
            }

            return con;
        }
    }
}
