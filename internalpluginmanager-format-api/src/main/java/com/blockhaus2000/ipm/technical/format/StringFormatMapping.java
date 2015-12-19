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
package com.blockhaus2000.ipm.technical.format;

/**
 * A {@link StringFormatMapping} maps a {@link StringFormatMappingKey} to a
 * String to be used within the {@link StringFormatter}.
 *
 */
public class StringFormatMapping {
    /**
     * The key that is mapped to the String.
     *
     */
    private final StringFormatMappingKey key;
    /**
     * The mapped String.
     *
     */
    private final String formatted;

    /**
     * Constructor of StringFormatMapping.
     *
     * @param key
     *            The key to use for mapping.
     * @param formatted
     *            The mapped String.
     */
    public StringFormatMapping(final StringFormatMappingKey key, final String formatted) {
        assert key != null : "Key cannot be null!";
        assert formatted != null : "Formatted cannot be null!";

        this.key = key;
        this.formatted = formatted;
    }

    /**
     *
     * @return {@link StringFormatMapping#key}.
     */
    public StringFormatMappingKey getKey() {
        return this.key;
    }

    /**
     *
     * @return {@link StringFormatMapping#formatted}.
     */
    public String getFormatted() {
        return this.formatted;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getName() + "[key=" + this.key + ", formatted=" + this.formatted + "]";
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
        result = prime * result + (this.formatted == null ? 0 : this.formatted.hashCode());
        result = prime * result + (this.key == null ? 0 : this.key.hashCode());
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
        if (!(obj instanceof StringFormatMapping)) {
            return false;
        }
        final StringFormatMapping other = (StringFormatMapping) obj;
        if (this.formatted == null) {
            if (other.formatted != null) {
                return false;
            }
        } else if (!this.formatted.equals(other.formatted)) {
            return false;
        }
        if (this.key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!this.key.equals(other.key)) {
            return false;
        }
        return true;
    }
}
