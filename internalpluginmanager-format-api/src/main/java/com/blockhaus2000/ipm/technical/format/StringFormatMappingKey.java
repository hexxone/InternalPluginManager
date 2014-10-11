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
package com.blockhaus2000.ipm.technical.format;

public class StringFormatMappingKey {
    private final StringFormatKeyPrefix prefix;
    private final char key;

    public StringFormatMappingKey(final StringFormatKeyPrefix prefix, final char key) {
        assert prefix != null : "Prefix cannot be null!";
        assert String.valueOf(key).matches("[a-zA-Z0-9]") : "Key has to match the regex \"[a-zA-Z0-9]\"!";

        this.prefix = prefix;
        this.key = key;
    }

    public String getStringKey() {
        return "" + this.prefix.getPrefix() + this.key;
    }

    /**
     *
     * @return {@link StringFormatMappingKey#prefix}.
     */
    public StringFormatKeyPrefix getPrefix() {
        return this.prefix;
    }

    /**
     *
     * @return {@link StringFormatMappingKey#key}.
     */
    public char getKey() {
        return this.key;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getName() + "[prefix=" + this.prefix + ", key=" + this.key + "]";
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.key;
        result = prime * result + (this.prefix == null ? 0 : this.prefix.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof StringFormatMappingKey)) {
            return false;
        }
        final StringFormatMappingKey other = (StringFormatMappingKey) obj;
        if (this.key != other.key) {
            return false;
        }
        if (this.prefix != other.prefix) {
            return false;
        }
        return true;
    }
}
