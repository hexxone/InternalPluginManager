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

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * The logger.
     *
     */
    private static final Logger LOGGER = IpmxNetworkMain.getInstance().getLogger();

    /**
     * The server port.
     *
     */
    private final int port;
    /**
     * The whitelist. If the whitelist is disabled, this is <code>null</code>.
     *
     */
    final List<String> whitelist;

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
        this.port = port;
        this.whitelist = whitelist;

        this.setDaemon(true);
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        final ServerSocket server = this.startServer();

        while (!this.isInterrupted()) {
            Connection con = null;
            String ip = "Unknown.";
            try {
                con = this.acceptConnection(server);
                if (con == null) {
                    continue;
                }
                ip = con.socket().getInetAddress().getHostAddress();

                final byte[] requestData = ServerUtil.readPacket(con.socket().getInputStream());

                ServerThread.LOGGER.log(Level.FINEST, "Raw data from " + ip + ": " + Arrays.toString(requestData));

                final byte[] responseData = ResponsePacketFactory.createResponsePacket(this.getResponsePacket(requestData));

                ServerThread.LOGGER.log(Level.FINEST, "Sending response data (" + Arrays.toString(responseData) + ").");

                con.out().write(responseData);

                con.out().close();
            } catch (final IOException cause) {
                ServerThread.LOGGER.log(Level.SEVERE, "An error occurred whilest handling connection of \"" + ip + "\"!", cause);
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (final IOException cause) {
                        ServerThread.LOGGER.log(Level.SEVERE, "An error occurred whilest closing connection to \"" + ip + "\"!",
                                cause);
                    }
                }
            }
        }

        ServerThread.LOGGER.log(Level.FINER, "Server stopped.");
    }

    /**
     * Starts the IPMNE server.
     *
     * @return The server socket.
     */
    private ServerSocket startServer() {
        ServerThread.LOGGER.log(Level.FINER, "Starting server on port " + this.port);

        final ServerSocket server;
        try {
            server = new ServerSocket(this.port);
        } catch (final IOException cause) {
            ServerThread.LOGGER.log(Level.SEVERE, "An error occurred whilest creating server socket!", cause);
            return null;
        }

        ServerThread.LOGGER.log(Level.FINEST, "Waiting for connection requests.");

        // Break every five seconds to avoid that the thread is running infinite
        // without a connection.
        try {
            server.setSoTimeout((int) TimeUnit.MILLISECONDS.convert(5, TimeUnit.SECONDS));
        } catch (final SocketException cause) {
            ServerThread.LOGGER.log(Level.SEVERE, "An error occurred whilest setting timeout!", cause);
            return null;
        }

        return server;
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
        final Connection con;
        try {
            con = new Connection(server.accept());
        } catch (final SocketTimeoutException dummy) {
            return null;
        }

        final String socketIp = con.socket().getInetAddress().getHostAddress();

        ServerThread.LOGGER.log(Level.FINER, "Access requested from IP " + socketIp + ".");

        if (this.whitelist == null) {
            ServerThread.LOGGER.log(Level.FINER, "Whitelist disabled. Access granted!");
        } else if (this.whitelist.contains(socketIp)) {
            ServerThread.LOGGER.log(Level.FINER, "Whitelist contains IP. Access granted!");
        } else {
            ServerThread.LOGGER.log(Level.FINER, "Whitelist does not contain IP. Access denied!");

            con.out().write(ResponsePacketFactory.createResponsePacket(new AccessDeniedResponsePacket()));

            con.close();
            return null;
        }

        con.out().write(ResponsePacketFactory.createResponsePacket(new AccessGrantedResponsePacket()));

        return con;
    }

    /**
     * Gets the response packet for the given request data.
     *
     * @param requestData
     *            The received request data.
     * @return The created response packet.
     */
    private ResponsePacket getResponsePacket(final byte[] requestData) {
        ResponsePacket responsePacket = new IllegalFormatResponsePacket("Unknown error.");
        try {
            responsePacket = this.getReponsePacket(requestData, RequestPacketFactory.createRequestPacket(requestData));
        } catch (final PacketFormatException ex) {
            responsePacket = new IllegalFormatResponsePacket(ex.getLocalizedMessage());
        }
        return responsePacket;
    }

    /**
     * Gets the response packet for the given request data and the given request
     * packet.
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
