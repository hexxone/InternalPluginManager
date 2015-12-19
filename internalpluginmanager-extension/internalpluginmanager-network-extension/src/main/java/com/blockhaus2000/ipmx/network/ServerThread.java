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
package com.blockhaus2000.ipmx.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blockhaus2000.ipm.technical.plugin.Plugin;
import com.blockhaus2000.ipm.technical.plugin.PluginManager;
import com.blockhaus2000.ipm.technical.plugin.PluginMeta;
import com.blockhaus2000.ipmx.network.exception.PacketFormatException;
import com.blockhaus2000.ipmx.network.packet.request.RequestPacket;
import com.blockhaus2000.ipmx.network.packet.request.RequestPacketFactory;
import com.blockhaus2000.ipmx.network.packet.request.packet.PluginInformationRequestPacket;
import com.blockhaus2000.ipmx.network.packet.response.ResponsePacket;
import com.blockhaus2000.ipmx.network.packet.response.ResponsePacketFactory;
import com.blockhaus2000.ipmx.network.packet.response.packet.AccessDeniedResponsePacket;
import com.blockhaus2000.ipmx.network.packet.response.packet.AccessGrantedResponsePacket;
import com.blockhaus2000.ipmx.network.packet.response.packet.IllegalFormatResponsePacket;
import com.blockhaus2000.ipmx.network.packet.response.packet.PluginInformationResponsePacket;
import com.blockhaus2000.ipmx.network.packet.response.packet.PluginListResponsePacket;

/**
 * The thread that represents the IPMNE server.
 *
 */
public class ServerThread extends Thread {
    /**
     * The Logger of this class.
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerThread.class);

    /**
     * The timeout for waiting for connection requests in seconds.
     *
     */
    private static final int SERVER_TIMEOUT = 5;

    /**
     * The server port.
     *
     */
    private final int port;
    /**
     * The whitelist. If the whitelist is disabled, this is <code>null</code>.
     *
     */
    private final List<String> whitelist;

    /**
     * Constructor of ServerThread.
     *
     * @param port
     *            The port to use.
     * @param whitelist
     *            The whitelist to use. <code>null</code> if the whitelist
     *            should be disabled.
     */
    public ServerThread(final int port, final List<String> whitelist) {
        assert port > 0 : "Port cannot be <=0!";

        this.port = port;
        this.whitelist = whitelist;

        this.setDaemon(true);
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Thread#run()
     */
    @SuppressWarnings("resource")
    @Override
    public void run() {
        ServerThread.LOGGER.info("Starting IPMNE server on port " + this.port);

        final ServerSocket server = this.startServer();

        ServerThread.LOGGER.info("IPMNE server started on port " + this.port);
        ServerThread.LOGGER.debug("Waiting for connections ...");

        while (!this.isInterrupted()) {
            String ip = "undefined";
            try {
                final Connection con = this.acceptConnection(server);
                if (con == null) {
                    continue;
                }
                ip = con.socket().getInetAddress().getHostAddress();

                final Thread connectionHandlerThread = new ConnectionHandlerThread(con);
                connectionHandlerThread.start();

                ServerThread.LOGGER.debug("Waiting for connections ...");
            } catch (final IOException cause) {
                ServerThread.LOGGER.error("An error occurred whilest handling connection of \"" + ip + "\"!", cause);
            }
        }

        ServerThread.LOGGER.info("Stopped IPMNE server!");
    }

    /**
     * Starts the IPMNE server.
     *
     * @return The server socket.
     */
    private ServerSocket startServer() {
        try {
            final ServerSocket server = new ServerSocket(this.port);

            // Break every five seconds to avoid that the thread is running
            // infinite
            // without a connection.
            server.setSoTimeout((int) TimeUnit.MILLISECONDS.convert(ServerThread.SERVER_TIMEOUT, TimeUnit.SECONDS));

            return server;
        } catch (final SocketException cause) {
            ServerThread.LOGGER.error("An error occurred whilest setting timeout!", cause);
        } catch (final IOException cause) {
            ServerThread.LOGGER.error("An error occurred whilest creating server socket!", cause);
        }

        return null;
    }

    /**
     * Waits for a connection, checks the acces permissions and grants or denies
     * the connection request.
     *
     * @param server
     *            The server socket to use.
     * @return An established connection. Or, if the connection was denied,
     *         <code>null</code>.
     * @throws IOException
     *             If an I/O error occurres.
     */
    private Connection acceptConnection(final ServerSocket server) throws IOException {
        assert server != null : "Server cannot be null!";

        final Connection con;
        try {
            con = new Connection(server.accept());
        } catch (final SocketTimeoutException dummy) {
            return null;
        }

        final String socketIp = con.socket().getInetAddress().getHostAddress();

        ServerThread.LOGGER.debug("Accepted connection from " + socketIp);

        if (this.whitelist == null) {
            ServerThread.LOGGER.debug("Whitelist disabled. Access granted!");
        } else if (this.whitelist.contains(socketIp)) {
            ServerThread.LOGGER.debug("IP is on whitelist. Access granted!");
        } else {
            ServerThread.LOGGER.debug("IP is not on whitelist. Access denied!");

            con.out().write(ResponsePacketFactory.createResponsePacket(new AccessDeniedResponsePacket()));

            con.close();
            return null;
        }

        con.out().write(ResponsePacketFactory.createResponsePacket(new AccessGrantedResponsePacket()));

        return con;
    }

    /**
     * The connection handler hanles a given connection to support multiple
     * requests per connection and parallel requests.
     *
     */
    public static class ConnectionHandlerThread extends Thread {
        /**
         * The Logger of this class.
         *
         */
        private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionHandlerThread.class);

        /**
         * The connection to handle.
         *
         */
        private final Connection con;

        /**
         * Constructor of ConnectionHandlerThread.
         *
         * @param con
         *            The connection to handle.
         */
        public ConnectionHandlerThread(final Connection con) {
            this.con = con;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Thread#run()
         */
        @Override
        public void run() {
            ConnectionHandlerThread.LOGGER.debug("Handling connection from "
                    + this.con.socket().getInetAddress().getHostAddress());

            while (!this.con.socket().isClosed()) {
                try {
                    if (!this.internalRun()) {
                        break;
                    }
                } catch (final IOException cause) {
                    ServerThread.LOGGER.error("An error occurred whilest handling connection from \"" + this.con + "\"!", cause);
                }
            }
            try {
                this.con.close();
            } catch (final IOException cause) {
                ServerThread.LOGGER.error("An error occurred whilest closing connection to \"" + this.con + "\"!", cause);
            }

            ConnectionHandlerThread.LOGGER.debug("Connection handling finished");
        }

        /**
         * The handler.
         *
         * @return Whether the connection is still open or not.
         * @throws IOException
         *             If an I/O error occurres.
         */
        // This method is made to avoid too deep nesting of all the code within
        // this method, cause every IOException has to be handled the same.
        private boolean internalRun() throws IOException {
            final byte[] requestData = ServerUtil.readPacket(this.con.socket().getInputStream());

            ConnectionHandlerThread.LOGGER.debug("Request data: " + Arrays.toString(requestData));

            // If the request data is empty, the connection was closed.
            if (requestData.length == 0) {
                return false;
            }

            final byte[] responseData = ResponsePacketFactory.createResponsePacket(this.getResponsePacket(requestData));

            ConnectionHandlerThread.LOGGER.debug("Sending response data " + Arrays.toString(responseData));

            this.con.out().write(responseData);

            return true;
        }

        /**
         * Gets the response packet for the given request data.
         *
         * @param requestData
         *            The received request data.
         * @return The created response packet.
         */
        private ResponsePacket getResponsePacket(final byte[] requestData) {
            assert requestData != null : "RequestData cannot be null!";

            ConnectionHandlerThread.LOGGER.debug("Retrieving response packet for request data " + Arrays.toString(requestData));

            ResponsePacket responsePacket = new IllegalFormatResponsePacket("Unknown error.");
            try {
                responsePacket = this.getReponsePacket(requestData, RequestPacketFactory.createRequestPacket(requestData));
            } catch (final PacketFormatException ex) {
                responsePacket = new IllegalFormatResponsePacket(ex.getLocalizedMessage());
            }

            ConnectionHandlerThread.LOGGER.debug("Response packet retrieved: " + responsePacket);

            return responsePacket;
        }

        /**
         * Gets the response packet for the given request data and the given
         * request packet.
         *
         * @param requestData
         *            The request data.
         * @param requestPacket
         *            The request packet.
         * @return The created response packet.
         * @throws PacketFormatException
         *             The the request packet is not correct formatted.
         */
        private ResponsePacket getReponsePacket(final byte[] requestData, final RequestPacket requestPacket)
                throws PacketFormatException {
            assert requestData != null : "RequestData cannot be null!";
            assert requestPacket != null : "RequestPacket cannot be null!";

            ConnectionHandlerThread.LOGGER.debug("Processing request packet " + requestPacket);

            final ResponsePacket responsePacket;
            switch (requestPacket.getPacketID()) {
                case REQUEST_PLUGIN_INFORMATION:
                    final PluginInformationRequestPacket specRequestPacket = (PluginInformationRequestPacket) requestPacket;
                    final Plugin plugin = PluginManager.getInstance().getPlugin(specRequestPacket.getPluginName());

                    if (plugin == null) {
                        throw new PacketFormatException("Plugin \"" + specRequestPacket.getPluginName() + "\" can not be find!");
                    }

                    final PluginMeta pluginMeta = plugin.getPluginMeta();
                    responsePacket = new PluginInformationResponsePacket(plugin.getName(), pluginMeta.getVersion(),
                            pluginMeta.getAuthor(), pluginMeta.getDependencies(), plugin.isEnabled());

                    break;
                case REQUEST_PLUGIN_LIST:
                    final List<String> pluginNames = new ArrayList<String>();

                    for (final Plugin p : PluginManager.getInstance().getPlugins()) {
                        pluginNames.add(p.getName());
                    }

                    responsePacket = new PluginListResponsePacket(pluginNames.toArray(new String[pluginNames.size()]));

                    break;
                default:
                    throw new PacketFormatException("Illegal packet content: " + Arrays.toString(requestData));
            }
            return responsePacket;
        }
    }
}
