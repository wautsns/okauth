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
package com.github.wautsns.okauth.core.client.builtin.github.model;

import com.github.wautsns.okauth.core.assist.http.kernel.model.basic.DataMap;
import com.github.wautsns.okauth.core.client.builtin.BuiltInOpenPlatforms;
import com.github.wautsns.okauth.core.client.kernel.model.OAuth2Token;
import com.github.wautsns.okauth.core.client.kernel.openplatform.OpenPlatform;

/**
 * GitHub oauth2 token.
 *
 * <pre>
 * {
 * 	"access_token": "ACCESS_TOKEN",
 * 	"token_type": "bearer",
 * 	"scope": "SCOPE"
 * }
 * </pre>
 *
 * @author wautsns
 * @since May 17, 2020
 */
public class GitHubOAuth2Token extends OAuth2Token {

    private static final long serialVersionUID = 8408050532302185568L;

    /**
     * Construct a GitHub oauth2 token.
     *
     * @param originalDataMap original data map
     */
    public GitHubOAuth2Token(DataMap originalDataMap) {
        super(originalDataMap);
    }

    @Override
    public OpenPlatform getOpenPlatform() {
        return BuiltInOpenPlatforms.GITHUB;
    }

    @Override
    public String getAccessToken() {
        return getOriginalDataMap().getAsString("access_token");
    }

    /** FIXME GitHub oauth2 access token expires in ??(Assume 1 day). */
    private static final Integer ACCESS_TOKEN_EXPIRATION_SECONDS = 24 * 3600;

    @Override
    public Integer getAccessTokenExpirationSeconds() {
        return ACCESS_TOKEN_EXPIRATION_SECONDS;
    }

    /**
     * Get scopes(delimiter: comma).
     *
     * @return scopes
     */
    public String getScopes() {
        return getOriginalDataMap().getAsString("scope");
    }

    /**
     * Get token type: {@code "bearer"}.
     *
     * @return {@code "bearer"}
     */
    public String getTokenType() {
        return getOriginalDataMap().getAsString("token_type");
    }

}
