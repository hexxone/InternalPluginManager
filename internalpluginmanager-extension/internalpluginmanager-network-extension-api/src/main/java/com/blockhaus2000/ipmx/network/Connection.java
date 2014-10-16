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

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * This class is a POJO to store data that is used for connections to a server
 * socket.
 *
 */
public class Connection implements Closeable {
    /**
     * The connected socket.
     *
     */
    private final Socket socket;

    /**
     * The input stream from this connection that is used to read the server
     * response.
     *
     */
    private final DataInputStream in;
    /**
     * The output stream from this connection that is used to write requests for
     * the server.
     *
     */
    private final DataOutputStream out;

    /**
     * Initilizes a new {@link Connection} based on the given socket. Input and
     * output streams are initilized automaticly.
     *
     * @param socket
     *            The connected socket.
     * @throws IOException
     *             If an I/O error occurres.
     */
    protected Connection(final Socket socket) throws IOException {
        this.socket = socket;

        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
    }

    /**
     * {@inheritDoc}
     *
     * @see java.io.Closeable#close()
     */
    @Override
    public void close() throws IOException {
        this.in.close();
        this.out.close();
        this.socket.close();
    }

    /**
     *
     * @return {@link Connection#socket}
     */
    public Socket socket() {
        return this.socket;
    }

    /**
     *
     * @return {@link Connection#in}
     */
    public DataInputStream in() {
        return this.in;
    }

    /**
     *
     * @return {@link Connection#out}
     */
    public DataOutputStream out() {
        return this.out;
    }
}
