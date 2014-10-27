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
package com.blockhaus2000.ipm.minecraft;

/**
 * A list of notes that can be played by a note block.
 *
 */
public enum Note {
    // TODO: Add notes.
    ;

    /**
     * The note ID.
     *
     */
    private final int id;

    /**
     * Constructor of Note.
     *
     * @param id
     *            The note ID.
     */
    private Note(final int id) {
        this.id = id;
    }

    /**
     *
     * @return {@link Note#id}
     */
    public int getId() {
        return this.id;
    }
}
