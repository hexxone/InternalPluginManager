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
package com.blockhaus2000.minecraft.util.tabcompl;

@SuppressWarnings("javadoc")
public class TabCompleteException extends RuntimeException {
    private static final long serialVersionUID = 4499640925520898783L;

    public TabCompleteException() {
        super();
    }

    public TabCompleteException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TabCompleteException(final String message) {
        super(message);
    }

    public TabCompleteException(final Throwable cause) {
        super(cause);
    }
}
