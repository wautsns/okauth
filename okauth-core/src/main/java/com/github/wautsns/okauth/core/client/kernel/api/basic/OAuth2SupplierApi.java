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
package com.github.wautsns.okauth.core.client.kernel.api.basic;

import com.github.wautsns.okauth.core.exception.OAuth2Exception;

/**
 * OAuth2 supplier api.
 *
 * @author wautsns
 * @since May 24, 2020
 */
public interface OAuth2SupplierApi<T> {

    /**
     * Execute and return result.
     *
     * @return result
     * @throws OAuth2Exception if oauth2 failed
     */
    T execute() throws OAuth2Exception;

}
