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
package com.blockhaus2000.plugin.exception;

/**
 *
 * @author Blockhaus2000
 */
@SuppressWarnings("javadoc")
public class PluginException extends Exception {
    private static final long serialVersionUID = -7270008574817315415L;

    public PluginException() {
        super();
    }

    public PluginException(final String description) {
        super(description);
    }

    public PluginException(final Throwable cause) {
        super(cause);
    }

    public PluginException(final String description, final Throwable cause) {
        super(description, cause);
    }
}
