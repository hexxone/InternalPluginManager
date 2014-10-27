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
package com.blockhaus2000.ipm.minecraft.block.meta.impl;

import com.blockhaus2000.ipm.minecraft.block.meta.BlockMeta;
import com.blockhaus2000.ipm.minecraft.block.meta.SignBlockMeta;

/**
 * Concrete {@link SignBlockMeta} implementation.
 *
 */
public class ConcreteSignBlockMeta extends ConcreteBlockMeta implements SignBlockMeta {
    /**
     * <code>firstLine</code>
     *
     */
    private final String firstLine;
    /**
     * <code>secondLine</code>
     *
     */
    private final String secondLine;
    /**
     * <code>thrirdLine</code>
     *
     */
    private final String thirdLine;
    /**
     * <code>fourthLine</code>
     *
     */
    private final String fourthLine;

    /**
     * Constructor of AbstractWeightedPressurePlateBlockMeta.
     *
     * @param blockMeta
     *            The {@link BlockMeta} that contains common information about
     *            this block (meta).
     * @param firstLine
     *            <code>firstLine</code>
     * @param secondLine
     *            <code>secondLine</code>
     * @param thirdLine
     *            <code>thirdLine</code>
     * @param fourthLine
     *            <code>fourthLine</code>
     */
    public ConcreteSignBlockMeta(final BlockMeta blockMeta, final String firstLine, final String secondLine,
            final String thirdLine, final String fourthLine) {
        super(blockMeta);

        this.firstLine = firstLine;
        this.secondLine = secondLine;
        this.thirdLine = thirdLine;
        this.fourthLine = fourthLine;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.SignBlockMeta#getFirstLine()
     */
    @Override
    public String getFirstLine() {
        return this.firstLine;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.SignBlockMeta#getSecondLine()
     */
    @Override
    public String getSecondLine() {
        return this.secondLine;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.SignBlockMeta#getThirdLine()
     */
    @Override
    public String getThirdLine() {
        return this.thirdLine;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.minecraft.block.meta.SignBlockMeta#getFourthLine()
     */
    @Override
    public String getFourthLine() {
        return this.fourthLine;
    }
}
