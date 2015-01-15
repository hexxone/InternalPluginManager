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
 */
package com.blockhaus2000.ipm.technical.plugin.dependency;

import com.blockhaus2000.ipm.technical.plugin.Plugin;
import com.blockhaus2000.ipm.technical.plugin.PluginManager;
import com.blockhaus2000.ipm.technical.plugin.dependency.exception.MaximumDependencyDepthReachedException;
import com.blockhaus2000.ipm.technical.plugin.dependency.exception.UnresolveableDependencyException;

/**
 * The dependency builder build a new dependency for the given plugin (name).
 *
 */
public class DependencyBuilder {
    /**
     * The maximum depth of the builder to go into the dependency tree.
     *
     * @see com.blockhaus2000.ipm.technical.plugin.dependency.DependencyBuilder#depth
     */
    private static final int MAXIMUM_DEPTH = 25;

    /**
     * The name of the plugin to start the dependency tree from.
     *
     */
    private final String rootPluginName;

    /**
     * The current depth of the builder in the dependency tree.
     *
     * <p>
     * For Example:
     * <table border='1px'>
     * <tr>
     * <td>Depth = 0</td>
     * <td>Depth = 1</td>
     * <td>Depth = 2</td>
     * </tr>
     * <tr>
     * <td>Root</td>
     * <td></td>
     * <td></td>
     * </tr>
     * <tr>
     * <td></td>
     * <td>Sub</td>
     * <td></td>
     * </tr>
     * <tr>
     * <td></td>
     * <td></td>
     * <td>Sub-Sub</td>
     * </tr>
     * <tr>
     * <td></td>
     * <td>Sub2</td>
     * <td></td>
     * </tr>
     * </table>
     * </p>
     *
     */
    private int depth;

    /**
     * Constructor of DependencyBuilder.
     *
     * @param rootPluginName
     *            The plugin name to build the dependencies for.
     */
    public DependencyBuilder(final String rootPluginName) {
        this.rootPluginName = rootPluginName;
    }

    /**
     * Delegates to {@link DependencyBuilder#getDependency(String)} with
     * <code>pluginName = rootPluginName</code>.
     *
     * @return See {@link DependencyBuilder#getDependency(String)}.
     * @throws UnresolveableDependencyException
     *             See {@link DependencyBuilder#getDependency(String)}
     * @see com.blockhaus2000.ipm.technical.plugin.dependency.DependencyBuilder#getDependency(java.lang.String)
     */
    public Dependency getDependency() throws UnresolveableDependencyException {
        return this.getDependency(this.rootPluginName);
    }

    /**
     * Builds the dependency/dependencies for the iven plugin (name).
     *
     * @param pluginName
     *            The plugin (name) to build the dependencies for.
     * @return The dependecy/dependencies for the given plguin (name).
     * @throws UnresolveableDependencyException
     *             Is thrown if one sub-dependency cannot be correctly resolved
     *             to a (loaded) plugin.
     */
    private Dependency getDependency(final String pluginName) throws UnresolveableDependencyException {
        assert pluginName != null : "PluginName cannot be null!";

        final Plugin plugin = PluginManager.getInstance().getPlugin(pluginName);
        if (plugin == null) {
            throw new UnresolveableDependencyException("Plugin with name \"" + pluginName + "\" cannot be resolved!");
        }

        if (this.depth > DependencyBuilder.MAXIMUM_DEPTH) {
            throw new MaximumDependencyDepthReachedException("Reached maximum allowed dependency depth "
                    + DependencyBuilder.MAXIMUM_DEPTH
                    + "! Maybe, you have a circle in your dependencies or something is really, really bad.");
        }

        this.depth++;

        Dependency dependency = new PlainDependency();
        for (final String dependencyName : plugin.getPluginMeta().getDependencies()) {
            for (final String s : this.getDependency(dependencyName).getDependencyNames()) {
                dependency = new DynamicDependency(dependency, s);
            }
        }

        this.depth--;

        return dependency;
    }
}
