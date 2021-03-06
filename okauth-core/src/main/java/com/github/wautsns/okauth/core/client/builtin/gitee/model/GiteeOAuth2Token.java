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
package com.github.wautsns.okauth.core.client.builtin.gitee.model;

import com.github.wautsns.okauth.core.assist.http.kernel.model.basic.DataMap;
import com.github.wautsns.okauth.core.client.builtin.BuiltInOpenPlatformNames;
import com.github.wautsns.okauth.core.client.kernel.model.OAuth2RefreshableToken;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Gitee oauth2 token.
 *
 * <pre>
 * {
 * 	"access_token": "ACCESS_TOKEN",
 * 	"token_type": "bearer",
 * 	"expires_in": 86400,
 * 	"refresh_token": "REFRESH_TOKEN",
 * 	"scope": "SCOPE",
 * 	"created_at": 1590230103
 * }
 * </pre>
 *
 * @author wautsns
 * @since May 17, 2020
 */
@Data
@Accessors(chain = true)
public class GiteeOAuth2Token implements OAuth2RefreshableToken {

    private static final long serialVersionUID = 7155633421437700449L;

    /** Token id. */
    private String tokenId;
    /** Original data map. */
    private final DataMap originalDataMap;

    @Override
    public String getOpenPlatform() {
        return BuiltInOpenPlatformNames.GITEE;
    }

    @Override
    public String getAccessToken() {
        return originalDataMap.getAsString("access_token");
    }

    @Override
    public Integer getAccessTokenExpirationSeconds() {
        return originalDataMap.getAsInteger("expires_in");
    }

    @Override
    public String getRefreshToken() {
        return originalDataMap.getAsString("refresh_token");
    }

    /** FIXME Gitee oauth2 refresh token expires in ??(Assume 7 days). */
    private static final Integer REFRESH_TOKEN_EXPIRATION_SECONDS = 7 * 24 * 3600;

    @Override
    public Integer getRefreshTokenExpirationSeconds() {
        return REFRESH_TOKEN_EXPIRATION_SECONDS;
    }

    /**
     * Get scopes(delimiter: space).
     *
     * @return scopes
     * @see com.github.wautsns.okauth.core.client.builtin.gitee.GiteeOAuth2AppInfo.Scope
     */
    public String getScopes() {
        return originalDataMap.getAsString("scope");
    }

    /**
     * Get created at(seconds timestamp).
     *
     * @return created at(seconds timestamp)
     */
    public Long getCreatedAt() {
        return originalDataMap.getAsLong("created_at");
    }

}
