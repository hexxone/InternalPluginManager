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
package com.blockhaus2000.ipm.minecraft.canary;

import java.io.File;

import net.canarymod.plugin.Plugin;

import com.blockhaus2000.ipm.base.injection.InjectionManager;
import com.blockhaus2000.ipm.minecraft.Server;
import com.blockhaus2000.ipm.technical.plugin.PluginManager;

/**
 * TODO: Add type description!
 *
 */
public class Main extends Plugin {
    /**
     * {@inheritDoc}
     *
     * @see net.canarymod.plugin.Plugin#enable()
     */
    @Override
    public boolean enable() {
        InjectionManager.addResource(CanaryServer.getInstance(), Server.class);
        PluginManager.getInstance().start(new File("plugins" + File.separator + this.getName(), "plugins"));

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @see net.canarymod.plugin.Plugin#disable()
     */
    @Override
    public void disable() {
        // Nothing to do.
    }
}
