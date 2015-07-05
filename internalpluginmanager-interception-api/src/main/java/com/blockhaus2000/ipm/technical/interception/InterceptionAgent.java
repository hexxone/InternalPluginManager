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

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

public class InterceptionAgent {
    private static final Logger LOGGER = LoggerFactory.getLogger(InterceptionAgent.class);

    private static final InterceptionAgent INSTANCE = new InterceptionAgent();

    private Instrumentation instrumentation;

    public static void agentmain(final String args, final Instrumentation instrumentation) {
        InterceptionAgent.getInstance().init(instrumentation);
    }

    public static void attach() {
        InterceptionAgent.LOGGER.info("Attaching interception agent to current virtual machine.");

        final String ipmJarFile = ""; // TODO: insert correct path.

        final String vmName = ManagementFactory.getRuntimeMXBean().getName();
        final String vmPid = vmName.split("@")[0];

        InterceptionAgent.LOGGER.debug("Attaching to virtual machine with name {}.", vmName);

        VirtualMachine virtualMachine = null;
        try {
            virtualMachine = VirtualMachine.attach(vmName);
            virtualMachine.loadAgent(ipmJarFile);
        } catch (final AttachNotSupportedException cause) {
            InterceptionAgent.LOGGER.warn("The current virtual machine does not support runtime attachments! "
                    + "To enable interception, " + "invoke the JVM with the parameter -javaagent:" + ipmJarFile + ". "
                    + "Disabling interception support ...", cause);
        } catch (final Exception cause) {
            InterceptionAgent.LOGGER.error(
                    "An error occurred whilst attaching the interception agent to the current virtual machine!", cause);
        } finally {
            if (virtualMachine != null) {
                try {
                    virtualMachine.detach();
                } catch (final IOException cause) {
                    InterceptionAgent.LOGGER.error("Failed to detach from virtual machine!", cause);
                }
            }
        }
    }

    private void init(final Instrumentation instrumentation) {
        this.instrumentation = instrumentation;

        this.instrumentation.addTransformer(new ClassFileTransfomerAgent());
    }

    public static InterceptionAgent getInstance() {
        return InterceptionAgent.INSTANCE;
    }

    public Instrumentation getInstrumentation() {
        return this.instrumentation;
    }
}
