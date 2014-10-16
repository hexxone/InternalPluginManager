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
package com.blockhaus2000.ipmx.network.packet;

import com.blockhaus2000.ipmx.network.packet.request.RequestPacketFactory;
import com.blockhaus2000.ipmx.network.packet.response.ResponsePacketFactory;

/**
 * A {@link PacketID} is a defined protocol value, that is used to define the
 * packet type, end, etc.. The format of the message is VERY important, so it
 * will be added to every single packet ID.
 * <p>
 * You can work directly with this, but it is much easier to work with the
 * {@link ResponsePacketFactory} and the {@link RequestPacketFactory}. Please
 * read the documentation of the also.
 * </p>
 *
 */
public enum PacketID {
    /**
     * Indicates the packet end.
     *
     * <p>
     * <b> NOTE: This is <b>no</b> valid heaader ID, so it can not be used as a
     * single packet. Because of that, no format is added here. </b>
     * </p>
     *
     */
    TECHNICAL_PACKET_END(PacketID.Type.TECHNICAL, (byte) 0xff),

    /**
     * Says that the access is granted.
     *
     * <p>
     * Format:
     * <table border='1px'>
     * <tr>
     * <td>ID</td>
     * <td>Byte count</td>
     * <td>Value</td>
     * <td>Description</td>
     * </tr>
     * <tr>
     * <td>1</td>
     * <td>1</td>
     * <td><code>0x11</code></td>
     * <td>The packet header ID</td>
     * </tr>
     * <tr>
     * <td>2</td>
     * <td>1</td>
     * <td><code>0xff</code></td>
     * <td>The packet end ID.</td>
     * </tr>
     * </table>
     * </p>
     */
    RESPONSE_ACCESS_GRANTED(PacketID.Type.RESPONSE, (byte) 0x11),
    /**
     * Says that the access is denied.
     *
     * <p>
     * Format:
     * <table border='1px'>
     * <tr>
     * <td>ID</td>
     * <td>Byte count</td>
     * <td>Value</td>
     * <td>Description</td>
     * </tr>
     * <tr>
     * <td>1</td>
     * <td>1</td>
     * <td><code>0x22</code></td>
     * <td>The packet header ID</td>
     * </tr>
     * <tr>
     * <td>2</td>
     * <td>1</td>
     * <td><code>0xff</code></td>
     * <td>The packet end ID.</td>
     * </tr>
     * </table>
     * </p>
     */
    RESPONSE_ACCESS_DENIED(PacketID.Type.RESPONSE, (byte) 0x22),

    /**
     * Says that an illegal packet format has been detected. Also returns an
     * error message.
     *
     * <p>
     * Format:
     * <table border='1px'>
     * <tr>
     * <td>ID</td>
     * <td>Byte count</td>
     * <td>Value</td>
     * <td>Description</td>
     * </tr>
     * <tr>
     * <td>1</td>
     * <td>1</td>
     * <td><code>0x33</code></td>
     * <td>The packet header ID</td>
     * </tr>
     * <tr>
     * <td>2</td>
     * <td>4</td>
     * <td><code></code></td>
     * <td>The (4 byte long) count of bytes of the following error message
     * String.</td>
     * </tr>
     * <tr>
     * <td>3</td>
     * <td>Value of 2</td>
     * <td><code></code></td>
     * <td>The error message String, splitted in bytes.</td>
     * </tr>
     * <tr>
     * <td>4</td>
     * <td>1</td>
     * <td><code>0xff</code></td>
     * <td>The packet end ID.</td>
     * </tr>
     * </table>
     * </p>
     */
    RESPONSE_ILLEGAL_FORMAT(PacketID.Type.RESPONSE, (byte) 0x33),

    /**
     * Request for a list of plugins.
     *
     * <p>
     * Format:
     * <table border='1px'>
     * <tr>
     * <td>ID</td>
     * <td>Byte count</td>
     * <td>Value</td>
     * <td>Description</td>
     * </tr>
     * <tr>
     * <td>1</td>
     * <td>1</td>
     * <td><code>0x01</code></td>
     * <td>The packet header ID</td>
     * </tr>
     * <tr>
     * <td>2</td>
     * <td>1</td>
     * <td><code>0xff</code></td>
     * <td>The packet end ID.</td>
     * </tr>
     * </table>
     * </p>
     */
    REQUEST_PLUGIN_LIST(PacketID.Type.REQUEST, (byte) 0x01),
    /**
     * The plugin list that will be returned if a plugin list request is
     * detected.
     *
     * <p>
     * Format:
     * <table border='1px'>
     * <tr>
     * <td>ID</td>
     * <td>Byte count</td>
     * <td>Value</td>
     * <td>Description</td>
     * </tr>
     * <tr>
     * <td>1</td>
     * <td>1</td>
     * <td><code>0x10</code></td>
     * <td>The packet header ID</td>
     * </tr>
     * <tr>
     * <td>2</td>
     * <td>4</td>
     * <td><code></code></td>
     * <td>The (4 byte long) count of plugin names following.</td>
     * </tr>
     * <tr>
     * <td></td>
     * <td></td>
     * <td></td>
     * <td></td>
     * </tr>
     * <tr>
     * <td></td>
     * <td></td>
     * <td></td>
     * <td>The following values are repeating as often as 2 defines.</td>
     * </tr>
     * <tr>
     * <td>3.x.1</td>
     * <td>4</td>
     * <td><code></code></td>
     * <td>The (4 byte long) count of bytes that the following String (the
     * plugin name) has.</td>
     * </tr>
     * <tr>
     * <td>3.x.2</td>
     * <td>Value of 3.x.1</td>
     * <td><code></code></td>
     * <td>The plugin name String (splitted into bytes).</td>
     * </tr>
     * <tr>
     * <td></td>
     * <td></td>
     * <td></td>
     * <td>The repeating stops here.</td>
     * </tr>
     * <tr>
     * <td></td>
     * <td></td>
     * <td></td>
     * <td></td>
     * </tr>
     * <tr>
     * <td>4</td>
     * <td>1</td>
     * <td><code>0xff</code></td>
     * <td>The packet end ID.</td>
     * </tr>
     * </table>
     * </p>
     */
    RESPONSE_PLUGIN_LIST(PacketID.Type.RESPONSE, (byte) 0x10),

    /**
     * Requests information about a specific plugin.
     *
     * <p>
     * Format:
     * <table border='1px'>
     * <tr>
     * <td>ID</td>
     * <td>Byte count</td>
     * <td>Value</td>
     * <td>Description</td>
     * </tr>
     * <tr>
     * <td>1</td>
     * <td>1</td>
     * <td><code>0x02</code></td>
     * <td>The packet header ID</td>
     * </tr>
     * <tr>
     * <td>2</td>
     * <td>4</td>
     * <td><code></code></td>
     * <td>The (4 byte long) count of bytes of the following String (the
     * requested plugin name).</td>
     * </tr>
     * <tr>
     * <td>3</td>
     * <td>Value of 2</td>
     * <td><code></code></td>
     * <td>The plugin name String (splitted into bytes).</td>
     * </tr>
     * <tr>
     * <td>4</td>
     * <td>1</td>
     * <td><code>0xff</code></td>
     * <td>The packet end ID.</td>
     * </tr>
     * </table>
     * </p>
     */
    REQUEST_PLUGIN_INFORMATION(PacketID.Type.REQUEST, (byte) 0x02),
    /**
     * The plugin information that will be returned if a plugin information
     * request is detected.
     *
     * <p>
     * Format:
     * <table border='1px'>
     * <tr>
     * <td>ID</td>
     * <td>Byte count</td>
     * <td>Value</td>
     * <td>Description</td>
     * </tr>
     * <tr>
     * <td>1</td>
     * <td>1</td>
     * <td><code>0x20</code></td>
     * <td>The packet header ID</td>
     * </tr>
     * <tr>
     * <td>2</td>
     * <td>4</td>
     * <td><code></code></td>
     * <td>The (4 byte long) count of bytes from the plugin name.</td>
     * </tr>
     * <tr>
     * <td>3</td>
     * <td>4</td>
     * <td><code></code></td>
     * <td>The (4 byte long) count of bytes from the plugin version.</td>
     * </tr>
     * <tr>
     * <td>4</td>
     * <td>4</td>
     * <td><code></code></td>
     * <td>The (4 byte long) count of bytes from the plugin author.</td>
     * </tr>
     * <tr>
     * <td>5</td>
     * <td>4</td>
     * <td><code></code></td>
     * <td>The (4 byte long) count of plugin dependencies.</td>
     * </tr>
     * <tr>
     * <td>6</td>
     * <td>1</td>
     * <td><code>0x00</code>/<code>0x01</code></td>
     * <td>The plugin enabled status (<code>0x01 = true</code>,
     * <code>0x00 = false</code>).</td>
     * </tr>
     * <tr>
     * <td>7</td>
     * <td>Value of 2</td>
     * <td><code></code></td>
     * <td>The plugin name (splitted into bytes).</td>
     * </tr>
     * <tr>
     * <td>8</td>
     * <td>Value of 3</td>
     * <td><code></code></td>
     * <td>The plugin version (splitted into bytes).</td>
     * </tr>
     * <tr>
     * <td>9</td>
     * <td>Value of 4</td>
     * <td><code></code></td>
     * <td>The plugin author (splitted into bytes).</td>
     * </tr>
     * <tr>
     * <td></td>
     * <td></td>
     * <td></td>
     * <td></td>
     * </tr>
     * <tr>
     * <td></td>
     * <td></td>
     * <td></td>
     * <td>The following values are repeating as often as 5 defines.</td>
     * </tr>
     * <tr>
     * <td>10.x.1</td>
     * <td>4</td>
     * <td><code></code></td>
     * <td>The (4 byte long) count of bytes that the following String (the
     * dependency name) has.</td>
     * </tr>
     * <tr>
     * <td>10.x.2</td>
     * <td>Value of 3.x.1</td>
     * <td><code></code></td>
     * <td>The dependency String (splitted into bytes).</td>
     * </tr>
     * <tr>
     * <td></td>
     * <td></td>
     * <td></td>
     * <td>The repeating stops here.</td>
     * </tr>
     * <tr>
     * <td></td>
     * <td></td>
     * <td></td>
     * <td></td>
     * </tr>
     * <tr>
     * <td>11</td>
     * <td>1</td>
     * <td><code>0xff</code></td>
     * <td>The packet end ID.</td>
     * </tr>
     * </table>
     * </p>
     */
    RESPONSE_PLUGIN_INFORMATION(PacketID.Type.RESPONSE, (byte) 0x20);

    /**
     * The {@link PacketID.Type} of this packet ID.
     *
     */
    private final PacketID.Type packetIdType;
    /**
     * The ID byte of this packet.
     *
     */
    private final byte id;

    /**
     * Constructor of PacketID.
     *
     * @param packetIdType
     *            The {@link PacketID.Type}.
     * @param id
     *            The byte ID.
     */
    private PacketID(final PacketID.Type packetIdType, final byte id) {
        this.packetIdType = packetIdType;
        this.id = id;
    }

    /**
     *
     * @return {@link PacketID#packetIdType}
     */
    public PacketID.Type getPacketIdType() {
        return this.packetIdType;
    }

    /**
     *
     * @return {@link PacketID#id}
     */
    public byte getId() {
        return this.id;
    }

    /**
     * Searchs for a {@link PacketID} that has the given ID.
     *
     * @param id
     *            The ID to search for.
     * @return The {@link PacketID}, if found. Otherwise <code>null</code>.
     */
    public static PacketID getById(final byte id) {
        for (final PacketID packetId : PacketID.values()) {
            if (packetId.getId() == id) {
                return packetId;
            }
        }
        return null;
    }

    /**
     * The packet ID type.
     *
     */
    public static enum Type {
        /**
         * The packet ID is a technical packet ID (like packet end).
         *
         */
        TECHNICAL,
        /**
         * The packet ID is a request packet ID.
         *
         */
        REQUEST,
        /**
         * The packet ID is a response packet ID.
         *
         */
        RESPONSE;
    }
}
