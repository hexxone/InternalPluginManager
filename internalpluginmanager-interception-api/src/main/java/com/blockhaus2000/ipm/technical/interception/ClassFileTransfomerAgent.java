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
package com.blockhaus2000.ipm.technical.interception;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blockhaus2000.ipm.technical.interception.transformer.BytecodeTransformer;

public class ClassFileTransfomerAgent implements ClassFileTransformer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassFileTransfomerAgent.class);

    /**
     * {@inheritDoc}
     *
     * @see java.lang.instrument.ClassFileTransformer#transform(java.lang.ClassLoader,
     *      java.lang.String, java.lang.Class, java.security.ProtectionDomain,
     *      byte[])
     */
    @Override
    public byte[] transform(final ClassLoader loader, final String className, final Class<?> classBeingRedefined,
            final ProtectionDomain protectionDomain, final byte[] classfileBuffer) throws IllegalClassFormatException {
        ClassFileTransfomerAgent.LOGGER.trace("Processing class {}.", className);

        try {
            final BytecodeTransformer transformer = new BytecodeTransformer(classfileBuffer);
            transformer.transform();
            return transformer.getBytecode();
        } catch (final Throwable cause) {
            ClassFileTransfomerAgent.LOGGER.error("An error occurred whilst processing class " + className + "!", cause);
            throw new IllegalClassFormatException("An error occurred whilst processing class " + className + ": "
                    + cause.getLocalizedMessage());
        }
    }
}
