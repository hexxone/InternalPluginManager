/*
 * This file is part of RedstoneWorldManagerTest
 * 
 */
package com.blockhaus2000.rwmtest;

import com.blockhaus2000.plugin.RwmPlugin;

/**
 * 
 * @author Blockhaus2000
 */
public class Main extends RwmPlugin {
    @Override
    public void onLoad() {
        System.out.println("public void onLoad()");
    }

    @Override
    public void onEnable() {
        System.out.println("public void onEnable()");
    }

    @Override
    public void onDisable() {
        System.out.println("public void onDisable()");
    }
}
