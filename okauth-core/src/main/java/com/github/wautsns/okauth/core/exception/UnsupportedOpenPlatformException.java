/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.wautsns.okauth.core.exception;

/**
 * Unsupported open platform exception.
 *
 * @author wautsns
 * @since Mar 04, 2020
 */
public class UnsupportedOpenPlatformException extends OAuthException {

    private static final long serialVersionUID = 4510043726113809910L;

    private final String name;

    public UnsupportedOpenPlatformException(String name) {
        super("unsupported open platform exception");
        this.name = name;
    }

    /**
     * Get the open platform name unsupported.
     *
     * @return the open platform name unsupported
     */
    public String getName() {
        return name;
    }

}
