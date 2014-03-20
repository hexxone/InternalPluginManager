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
package com.blockhaus2000.util.resources;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.blockhaus2000.main.bukkit.IpmMain;

/**
 * If you annotate a field with this, the field have to be of the type
 * {@link IpmMain} and you have to call
 * {@link ResourceManager#initializeResources(Class)} /
 * {@link ResourceManager#initializeResources(Object)} with the class object
 * from the class where the resource have to be initialized or with an object of
 * your class if the field is non-static.
 * 
 * <p>
 * <b> NOTE: Only use this if you really need the root/main plugin. For event
 * listening, use TODO instead. </b>
 * </p>
 * 
 * @author Blockhaus2000
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MainPluginResource {
}
