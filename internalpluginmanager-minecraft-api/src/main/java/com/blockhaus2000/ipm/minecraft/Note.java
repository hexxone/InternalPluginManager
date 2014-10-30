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
    /**
     * Octave 1 - F#
     *
     */
    O1_F_SHARP(0),
    /**
     * Octave 1 - G
     *
     */
    O1_G(1),
    /**
     * Octave 1 - G#
     *
     */
    O1_G_SHARP(2),
    /**
     * Octave 1 - A
     *
     */
    O1_A(3),
    /**
     * Octave 1 - A#
     *
     */
    O1_A_SHARP(4),
    /**
     * Octave 1 - H
     *
     */
    O1_H(5),
    /**
     * Octave 1 - C
     *
     */
    O1_C(6),
    /**
     * Octave 1 - C#
     *
     */
    O1_C_SHARP(7),
    /**
     * Octave 1 - D
     *
     */
    O1_D(8),
    /**
     * Octave 1 - D#
     *
     */
    O1_D_SHARP(9),
    /**
     * Octave 1 - E
     *
     */
    O1_E(10),
    /**
     * Octave 1 - F
     *
     */
    O1_F(11),
    /**
     * Octave 2 - F#
     *
     */
    O2_F_SHARP(12),
    /**
     * Octave 2 - G
     *
     */
    O2_G(13),
    /**
     * Octave 2 - G#
     *
     */
    O2_G_SHARP(14),
    /**
     * Octave 2 - A
     *
     */
    O2_A(15),
    /**
     * Octave 2 - A#
     *
     */
    O2_A_SHARP(16),
    /**
     * Octave 2 - H
     *
     */
    O2_H(17),
    /**
     * Octave 2 - C
     *
     */
    O2_C(18),
    /**
     * Octave 2 - C#
     *
     */
    O2_C_SHARP(19),
    /**
     * Octave 2 - D
     *
     */
    O2_D(20),
    /**
     * Octave 2 - D#
     *
     */
    O2_D_SHARP(21),
    /**
     * Octave 2 - E
     *
     */
    O2_E(22),
    /**
     * Octave 2 - F
     *
     */
    O2_F(23),
    /**
     * Octave 3 - F#
     *
     */
    O3_F_SHARP(24);

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
     * Searchs for the {@link Note} with the given ID.
     *
     * @param id
     *            The ID to search for.
     * @return The associated {@link Note}, if found. Otherwise
     *         <code>null</code>.
     */
    public static Note getById(final int id) {
        for (final Note note : Note.values()) {
            if (note.getId() == id) {
                return note;
            }
        }
        return null;
    }

    /**
     *
     * @return {@link Note#id}
     */
    public int getId() {
        return this.id;
    }
}
