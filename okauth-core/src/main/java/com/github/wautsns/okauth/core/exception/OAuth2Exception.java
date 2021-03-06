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
 * OAuth2 related exception.
 *
 * @author wautsns
 * @since May 16, 2020
 */
public abstract class OAuth2Exception extends Exception {

    private static final long serialVersionUID = 775296624639159639L;

    /**
     * Construct an OAuth2Exception.
     *
     * @param message error message
     */
    public OAuth2Exception(String message) {
        super(message);
    }

    /**
     * Construct an OAuth2Exception.
     *
     * @param cause cause
     * @param message error message
     */
    public OAuth2Exception(Throwable cause, String message) {
        super(message, cause);
    }

    /**
     * Construct an OAuth2Exception.
     *
     * @param cause cause
     */
    public OAuth2Exception(Throwable cause) {
        super(cause);
    }

}
