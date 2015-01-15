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
package com.blockhaus2000.ipm.mp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * This Mojo generates a plugin meta file from the project information.
 *
 */
@Mojo(name = "generate",
      defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class IpmMpGenerate extends AbstractMojo {
    /**
     * The plugin name (raw copied into plugin description file).
     *
     */
    @Parameter(defaultValue = "${project.artifactId}",
               required = true)
    private String name;

    /**
     * The version (raw copied into plugin description file).
     *
     */
    @Parameter(defaultValue = "${project.version}",
               required = true)
    private String version;

    /**
     * The main class (raw copied into plugin description file).
     *
     */
    @Parameter(required = true)
    private String main;

    /**
     * A list of dependencies that the plugin depends on.
     *
     */
    @Parameter(defaultValue = "",
               required = false)
    private String[] depends;

    /**
     * The directory where to deploy the file.
     *
     */
    @Parameter(defaultValue = "${project.build.outputDirectory}",
               required = true)
    private File directory;

    /**
     * The name of the file where to write the generated project/plugin meta
     * into.
     *
     * <p>
     * <b> NOTE: This file will be replaced! </b>
     * </p>
     *
     */
    @Parameter(defaultValue = "plugin-meta",
               required = true)
    private File file;

    /**
     * Constructor of IpmMpMain.
     *
     */
    public IpmMpGenerate() {
        // Nothing to do.
    }

    /**
     * {@inheritDoc}
     *
     * @see org.apache.maven.plugin.Mojo#execute()
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        this.directory.mkdirs();

        // Delete file to clear it. The PrintWriter creates a new one.
        this.file.delete();

        final PrintWriter writer;
        try {
            writer = new PrintWriter(
                    new BufferedOutputStream(new FileOutputStream(new File(this.directory, this.file.getName()))));
        } catch (final IOException cause) {
            throw new MojoFailureException("Failed to initilize output stream: " + cause.getLocalizedMessage() + ")");
        }

        writer.println("name " + this.name);
        writer.println("version " + this.version);
        writer.println("main " + this.main);
        if (this.depends != null && this.depends.length > 0) {
            String dependencyString = Arrays.toString(this.depends);

            // Remove first '[' and last ']'.
            dependencyString = dependencyString.substring(1, dependencyString.length() - 1);

            writer.println("depends " + dependencyString);
        }

        writer.close();
    }
}
