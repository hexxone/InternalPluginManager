/*
 * This file is part of RedstoneWorldManager
 * 
 */
package com.blockhaus2000.util.resources;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import me.lobnews.rwm.bukkit.Main;

/**
 * If you annotate a field with this, the field have to be of the type
 * {@link Main} and you have to call {@link Main#initialResources(Class)} with
 * the class object from the class where the resource have to be initialized.
 * 
 * @author Blockhaus2000
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MainPluginResource {
}
