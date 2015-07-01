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
package com.blockhaus2000.ipm.argvalidator;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class SimpleValidatorContext implements ValidatorContext {
    private final String basePackage;
    private final String resourcePath;

    private ValidatorContextState state = ValidatorContextState.STOPPED;

    public SimpleValidatorContext(final String basePackage) {
        assert basePackage != null && !basePackage.trim().isEmpty() : "BasePackage must not be null or empty!";

        this.basePackage = basePackage.trim();
        this.resourcePath = "/" + this.basePackage.replace('.', '/') + "/**/*.class";
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.argvalidator.ValidatorContext#start()
     */
    @Override
    public void start() {
        if (this.state != ValidatorContextState.STOPPED) {
            throw new IllegalStateException("The validator context is not stopped!");
        }

        this.state = ValidatorContextState.STARTING;

        this.state = ValidatorContextState.STARTED;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.argvalidator.ValidatorContext#stop()
     */
    @Override
    public void stop() throws IllegalStateException {
        // TODO Auto-generated method body.

    }

    /**
     * {@inheritDoc}
     *
     * @see com.blockhaus2000.ipm.argvalidator.ValidatorContext#getState()
     */
    @Override
    public ValidatorContextState getState() {
        return this.state;
    }

    private void test() {
        final ClassLoader loader = SimpleValidatorContext.class.getClassLoader();
        try {
            final Enumeration<URL> resources = loader.getResources(this.resourcePath);
        } catch (final IOException ex) {
            // TODO Auto-generated catch block.
            ex.printStackTrace();
        }
    }

    public static void main(final String[] args) {
        final ValidatorContext context = new SimpleValidatorContext("com.blockhaus2000");
    }

    private void checkState() {
        if (this.state != ValidatorContextState.STARTED) {
            throw new IllegalStateException("The validator context is not started yet!");
        }
    }
}
