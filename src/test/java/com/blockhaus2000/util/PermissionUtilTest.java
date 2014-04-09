/* This file is part of InternalPluginManager
 *
 * Copyright 2014 Blockhaus2000
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  see the License for the specific language governing permissions and
 *  Limitations under the License.
 */
package com.blockhaus2000.util;

import org.junit.Assert;
import org.junit.Test;

import com.blockhaus2000.bukkit.mock.MockConsoleCommandSender;
import com.blockhaus2000.bukkit.mock.MockPlayer;

/**
 *
 * @author Blockhaus2000
 */
@SuppressWarnings("javadoc")
public class PermissionUtilTest {
    @Test
    public void hasPermission() {
        final String perm = "internalpluginmanager.junit.perm_test";

        Assert.assertTrue(PermissionUtil.hasPermission(null, (String) null));
        Assert.assertTrue(PermissionUtil.hasPermission(null, perm));
        Assert.assertFalse(PermissionUtil.hasPermission(new MockPlayer(null, false), perm));
        Assert.assertTrue(PermissionUtil.hasPermission(new MockPlayer(perm, false), perm));
        Assert.assertTrue(PermissionUtil.hasPermission(new MockPlayer(null, true), perm));
        Assert.assertTrue(PermissionUtil.hasPermission(new MockPlayer(null, false), (String) null));
        Assert.assertTrue(PermissionUtil.hasPermission(new MockConsoleCommandSender(), (String) null));
        Assert.assertTrue(PermissionUtil.hasPermission(new MockConsoleCommandSender(), perm));
    }
}
