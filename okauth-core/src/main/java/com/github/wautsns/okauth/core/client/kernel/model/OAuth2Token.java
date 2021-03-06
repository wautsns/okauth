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
package com.github.wautsns.okauth.core.client.kernel.model;

import com.github.wautsns.okauth.core.assist.http.kernel.model.basic.DataMap;

import java.io.Serializable;

/**
 * OAuth2 token.
 *
 * @author wautsns
 * @since May 17, 2020
 */
public interface OAuth2Token extends OpenPlatformSupplier, Serializable {

    /**
     * Get original data map.
     *
     * @return original data map
     */
    DataMap getOriginalDataMap();

    /**
     * Get token id.
     *
     * @return token id
     */
    String getTokenId();

    /**
     * Set token id.
     *
     * @param tokenId token id
     * @return self reference
     */
    OAuth2Token setTokenId(String tokenId);

    /**
     * Get access token.
     *
     * @return access token
     */
    String getAccessToken();

    /**
     * Get access token expiration seconds.
     *
     * @return access token expiration seconds
     */
    Integer getAccessTokenExpirationSeconds();

}
