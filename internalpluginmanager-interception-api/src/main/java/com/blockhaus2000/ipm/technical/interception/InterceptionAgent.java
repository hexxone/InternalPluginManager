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

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.tools.attach.BsdVirtualMachine;
import sun.tools.attach.LinuxVirtualMachine;
import sun.tools.attach.SolarisVirtualMachine;
import sun.tools.attach.WindowsVirtualMachine;

import com.blockhaus2000.ipm.base.FileUtil;
import com.blockhaus2000.ipm.base.OperatingSystem;
import com.blockhaus2000.ipm.technical.interception.exception.InterceptionRuntimeException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import com.sun.tools.attach.spi.AttachProvider;

/**
 * The interception agent attaches to the JVM in order to plug the
 * {@link ClassFileTransfomerAgent} to the class loading process to enable
 * interception.
 *
 */
public class InterceptionAgent {
    /**
     * The logger for this class.
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(InterceptionAgent.class);

    /**
     * The {@link AttachProvider} that is used to attach to
     * {@link VirtualMachine}s.
     *
     */
    private static final AttachProvider ATTACH_PROVIDER = new AttachProvider() {
        @Override
        public String type() {
            return null;
        }

        @Override
        public String name() {
            return null;
        }

        @Override
        public List<VirtualMachineDescriptor> listVirtualMachines() {
            return null;
        }

        @Override
        public VirtualMachine attachVirtualMachine(final String id) throws AttachNotSupportedException, IOException {
            return null;
        }
    };

    /**
     * THE instance of this class.
     *
     */
    private static final InterceptionAgent INSTANCE = new InterceptionAgent();

    /**
     * The name of the system property that has to be used to define the path to
     * the JAR file that contains this class.
     *
     */
    public static String INTERCEPTION_JAR_PATH_SYSPROP = "ipm.interception.file";

    /**
     * The {@link Instrumentation} to plug {@link ClassFileTransformer}s to.
     *
     */
    private Instrumentation instrumentation;

    /**
     * Represents the state of this agent (i.e.g if it is initialized or not).
     *
     */
    private boolean initialized;

    /**
     * This method is invoked if the JVM is started with the special parameter
     * <code>-javaagent</code> in order to activate this agent.
     *
     * @param args
     *            The agent arguments.
     * @param instrumentation
     *            The {@link Instrumentation} for this JVM.
     */
    public static void premain(final String args, final Instrumentation instrumentation) {
        InterceptionAgent.getInstance().init(instrumentation);
    }

    /**
     * This method is invoked if this agent is dynamically attached to the JVM
     * within the runtime.
     *
     * <p>
     * <b> NOTE: This is the preferred way to attach an agent, because the user
     * does not have to start the JVM with special parameters. </b>
     * </p>
     *
     * @param args
     *            The agent arguments.
     * @param instrumentation
     *            The {@link Instrumentation} for this JVM.
     */
    public static void agentmain(final String args, final Instrumentation instrumentation) {
        InterceptionAgent.getInstance().init(instrumentation);
    }

    /**
     * Attaches this agent to the currently running JVM.
     *
     */
    public static void attach() {
        InterceptionAgent.getInstance().internalAttach();
    }

    /**
     * Initializes this agent, attaches the {@link ClassFileTransfomerAgent} to
     * the given {@link Instrumentation} and saves it for later usage.
     *
     * @param instrumentation
     *            The {@link Instrumentation} to save and attach the
     *            {@link ClassFileTransfomerAgent} to.
     */
    private void init(final Instrumentation instrumentation) {
        if (this.initialized) {
            InterceptionAgent.LOGGER.debug("Already initialized!");
            return;
        }

        this.instrumentation = instrumentation;
        this.instrumentation.addTransformer(new ClassFileTransfomerAgent());
        this.initialized = true;
    }

    /**
     * Attaches this agent to the currently running JVM.
     *
     */
    private void internalAttach() {
        if (this.initialized) {
            InterceptionAgent.LOGGER.debug("Already attached!");
            return;
        }

        InterceptionAgent.LOGGER.info("Attaching interception agent to current virtual machine.");

        String ipmJarFile;
        try {
            ipmJarFile = this.retrieveJarFilePath();
        } catch (final IOException cause) {
            InterceptionAgent.LOGGER.error("Failed to retrieve JAR file path! Disabling interception support ...", cause);

            return;
        }

        VirtualMachine virtualMachine = null;
        try {
            virtualMachine = this.attachToVm();
            virtualMachine.loadAgent(ipmJarFile, null);
        } catch (final AttachNotSupportedException cause) {
            InterceptionAgent.LOGGER.warn("The current virtual machine does not support attaching! To enable interception, "
                    + "invoke the JVM with the parameter -javaagent:" + ipmJarFile + ". " + "Disabling interception support ...",
                    cause);
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

    /**
     * Attaches to the JVM and returns the attaches {@link VirtualMachine}.
     *
     * @return The {@link VirtualMachine} that is attached.
     * @throws AttachNotSupportedException
     *             If attachments are not supported by the currently running
     *             JVM.
     * @throws IOException
     *             If any I/O error occurs.
     */
    private VirtualMachine attachToVm() throws AttachNotSupportedException, IOException {
        InterceptionAgent.LOGGER.debug("Attaching to virtual machine.");

        final VirtualMachine virtualMachine;
        if (AttachProvider.providers().isEmpty()) {
            InterceptionAgent.LOGGER.debug("No attachment providers found! Creating one ...");

            final String vmName = System.getProperty("java.vm.name");
            if (!vmName.contains("HotSpot")) {
                InterceptionAgent.LOGGER.warn("Running on a non-HotSpot virtual machine! Trying to attach anyway ...");
            }
            virtualMachine = this.retrieveOsDepentVmImplementation();
        } else {
            virtualMachine = VirtualMachine.attach(this.retrieveJvmId());
        }

        InterceptionAgent.LOGGER.debug("Attached!");

        return virtualMachine;
    }

    /**
     * Retrieves and attaches to the currently running JVM and return the
     * correct implementation of {@link VirtualMachine} for the current OS.
     *
     * <p>
     * <b> NOTE: Supported operating systems are: Windows, BSD, Linux and
     * Solaris. </b>
     * </p>
     *
     * @return The attached {@link VirtualMachine}.
     * @throws AttachNotSupportedException
     *             If the current JVM does not support attachments.
     * @throws IOException
     *             If any I/O error occurs.
     */
    private VirtualMachine retrieveOsDepentVmImplementation() throws AttachNotSupportedException, IOException {
        switch (OperatingSystem.detect()) {
            case WINDOWS:
                InterceptionAgent.LOGGER.debug("Attaching to window vm.");

                return new WindowsVirtualMachine(InterceptionAgent.ATTACH_PROVIDER, this.retrieveJvmId());
            case MAC:
                InterceptionAgent.LOGGER.debug("Attaching to bsd vm.");

                return new BsdVirtualMachine(InterceptionAgent.ATTACH_PROVIDER, this.retrieveJvmId());
            case UNIX:
                InterceptionAgent.LOGGER.debug("Attaching to linux vm.");

                return new LinuxVirtualMachine(InterceptionAgent.ATTACH_PROVIDER, this.retrieveJvmId());
            case SOLARIS:
                InterceptionAgent.LOGGER.debug("Attaching to solaris vm.");

                return new SolarisVirtualMachine(InterceptionAgent.ATTACH_PROVIDER, this.retrieveJvmId());
            default:
                throw new AttachNotSupportedException("Operating system " + OperatingSystem.get() + " not supported!");
        }
    }

    /**
     *
     * @return The name of the currently running JVM.
     */
    private String retrieveJvmName() {
        return ManagementFactory.getRuntimeMXBean().getName();
    }

    /**
     *
     * @return The ID of the currently running JVM.
     */
    private String retrieveJvmId() {
        final String name = this.retrieveJvmName();
        return name.substring(0, name.indexOf("@"));
    }

    /**
     * Retrieves the path of the JAR file containing this class from the system
     * property {@link InterceptionAgent#INTERCEPTION_JAR_PATH_SYSPROP} or the
     * class path.
     *
     * @return The path to the JAR file which contains this class.
     * @throws IOException
     *             If any I/O error occurs.
     */
    private String retrieveJarFilePath() throws IOException {
        String jarFile = System.getProperty(InterceptionAgent.INTERCEPTION_JAR_PATH_SYSPROP, null);

        final boolean fetchFromClassPath;
        if (jarFile == null) {
            InterceptionAgent.LOGGER.warn("Failed to retrieve interception jar file from syetem properties! "
                    + "(set it via sysprop " + InterceptionAgent.INTERCEPTION_JAR_PATH_SYSPROP
                    + "). Trying to fetch file from class path ...");

            fetchFromClassPath = true;
        } else if (!new File(jarFile).isFile()) {
            InterceptionAgent.LOGGER.warn("File specified via sysprop (" + jarFile
                    + ") does not exist! Trying to fetch file from class path ...");

            fetchFromClassPath = true;
        } else {
            fetchFromClassPath = false;
        }

        if (fetchFromClassPath) {
            final ClassLoader loader = InterceptionAgent.class.getClassLoader();
            if (loader instanceof URLClassLoader) {
                final List<String> potentialFiles = new ArrayList<String>();

                for (final URL url : ((URLClassLoader) loader).getURLs()) {
                    final String realFileName = url.getFile();
                    final String fileName = realFileName.toLowerCase();
                    if (fileName.contains("internalpluginmanager") || fileName.contains("ipm")) {
                        InterceptionAgent.LOGGER.debug("Found potential jar file: {}", realFileName);

                        potentialFiles.add(realFileName);
                    }
                }

                InterceptionAgent.LOGGER.debug("Potential files: {}", potentialFiles.toString());

                if (potentialFiles.size() > 1) {
                    InterceptionAgent.LOGGER
                            .warn("Found multiple potential files! Trying to determine correct file by checking the content.");

                    for (final String potentialFileName : potentialFiles) {
                        final File file = new File(potentialFileName);

                        if (!file.isFile()) {
                            InterceptionAgent.LOGGER.trace("File {} is not a file! Skipping ...", file.getAbsolutePath());
                            continue;
                        }

                        final File tempDir = FileUtil.createTempDir();
                        FileUtil.unzip(file, tempDir);

                        if (FileUtil.getFilesRecursive(tempDir, new FileFilter() {
                            @Override
                            public boolean accept(final File file) {
                                return file.getName().equals(InterceptionAgent.class.getSimpleName() + ".class");
                            }
                        }).size() > 0) {
                            InterceptionAgent.LOGGER.debug("Found file containing the interception agent!");

                            jarFile = potentialFileName;
                        }
                    }
                } else if (potentialFiles.size() == 1) {
                    InterceptionAgent.LOGGER.debug("Great! Only one potential file found.");

                    jarFile = potentialFiles.get(0);
                } else {
                    InterceptionAgent.LOGGER.warn("No potential fiels found!");

                    jarFile = null;
                }
            }
        }

        if (jarFile == null) {
            throw new InterceptionRuntimeException("Could not fetch jar file!");
        }

        InterceptionAgent.LOGGER.info("Found file: {}", jarFile);

        return jarFile;
    }

    /**
     *
     * @return {@link InterceptionAgent#INSTANCE}
     */
    public static InterceptionAgent getInstance() {
        return InterceptionAgent.INSTANCE;
    }

    /**
     *
     * @return {@link InterceptionAgent#instrumentation}
     * @throws IllegalStateException
     *             If the agent is not initialized yet.
     */
    public Instrumentation getInstrumentation() {
        if (!this.initialized) {
            throw new IllegalStateException("The interception agent is not initialized yet!");
        }

        return this.instrumentation;
    }
}
