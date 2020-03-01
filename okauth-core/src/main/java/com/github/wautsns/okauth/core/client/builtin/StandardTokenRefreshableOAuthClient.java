/**
h
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
package com.github.wautsns.okauth.core.client.builtin;

import com.github.wautsns.okauth.core.client.OpenPlatform;
import com.github.wautsns.okauth.core.client.kernel.TokenRefreshableOAuthClient;
import com.github.wautsns.okauth.core.client.kernel.http.OAuthRequestExecutor;
import com.github.wautsns.okauth.core.client.kernel.http.model.basic.OAuthUrl;
import com.github.wautsns.okauth.core.client.kernel.http.model.dto.OAuthRequest;
import com.github.wautsns.okauth.core.client.kernel.http.model.dto.OAuthResponse;
import com.github.wautsns.okauth.core.client.kernel.model.dto.OAuthRedirectUriQuery;
import com.github.wautsns.okauth.core.client.kernel.model.dto.OAuthToken;
import com.github.wautsns.okauth.core.client.kernel.model.dto.OpenPlatformUser;
import com.github.wautsns.okauth.core.client.kernel.model.properties.OAuthAppProperties;
import com.github.wautsns.okauth.core.exception.OAuthIOException;
import com.github.wautsns.okauth.core.exception.error.OAuthErrorException;
import com.github.wautsns.okauth.core.exception.error.RefreshTokenHasExpiredException;

/**
 * Standard token refreshable oauth client.
 *
 * @since Feb 29, 2020
 * @author wautsns
 */
public abstract class StandardTokenRefreshableOAuthClient<U extends OpenPlatformUser>
        extends TokenRefreshableOAuthClient<U> {

    private final OAuthUrl basicAuthorizeUrl;
    private final OAuthRequest basicTokenRequest;
    private final OAuthRequest basicRefreshTokenRequest;

    /**
     * Construct a standard token refreshable oauth client.
     *
     * @param app oauth app properties, require nonnull
     * @param executor oauth request executor, require nonnull
     */
    public StandardTokenRefreshableOAuthClient(
            OAuthAppProperties app, OAuthRequestExecutor executor) {
        super(app, executor);
        // basic authorize url
        basicAuthorizeUrl = new OAuthUrl(getAuthorizeUrl());
        basicAuthorizeUrl.getQuery()
            .addResponseTypeWithValueCode()
            .addClientId(app.getClientId())
            .addRedirectUri(app.getRedirectUri());
        // basic token request
        basicTokenRequest = initBasicTokenRequest();
        basicTokenRequest.getParamsByMethod()
            .addGrantTypeWithValueAuthorizationCode()
            .addClientId(app.getClientId())
            .addClientSecret(app.getClientSecret())
            .addRedirectUri(app.getRedirectUri());
        // basic refresh token request
        basicRefreshTokenRequest = initBasicRefreshTokenRequest();
        basicRefreshTokenRequest.getParamsByMethod()
            .addGrantTypeWithValueRefreshToken()
            .addClientId(app.getClientId())
            .addClientSecret(app.getClientSecret());
    }

    // -------------------- authorize url ---------------------------

    /**
     * Initialize a basic authorize url. <strong>Just need</strong> url.
     *
     * @return a basic authorize url
     */
    protected abstract String getAuthorizeUrl();

    /**
     * Initialize standard authorize url.
     *
     * <p>Query items are as follows:
     * <ul>
     * <li>response_type: {@code "code"}</li>
     * <li>client_id: {@code app.getClientId()}</li>
     * <li>redirect_uri: {@code app.getRedirectUri()}</li>
     * <li>state: {@code state}</li>
     * </ul>
     *
     * @param state {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public final OAuthUrl initAuthorizeUrl(String state) {
        OAuthUrl copy = basicAuthorizeUrl.copy();
        copy.getQuery().addState(state);
        return copy;
    }

    // -------------------- oauth token -----------------------------

    /**
     * Initialize a basic token request.
     *
     * <p>No need to set `grant_type`, `client_id`, `client_secret` and `redirect_uri`.
     *
     * @return a basic token request
     * @see #requestForToken(OAuthRedirectUriQuery)
     */
    protected abstract OAuthRequest initBasicTokenRequest();

    /**
     * {@inheritDoc}
     *
     * <p>Query items(GET)/Form items(POST) added are as follows:
     * <ul>
     * <li>grant_type: {@code "authorization_code"}</li>
     * <li>client_id: {@code app.getClientId()}</li>
     * <li>client_secret: {@code app.getClientSecret()}</li>
     * <li>redirect_uri: {@code app.getRedirectUri()}</li>
     * <li>code: {@code redirectUriQuery.getCode()}</li>
     * </ul>
     *
     * @param redirectUriQuery {@inheritDoc}
     * @return oauth token {@inheritDoc}
     * @throws OAuthIOException {@inheritDoc}
     * @throws OAuthErrorException {@inheritDoc}
     */
    @Override
    public final OAuthToken requestForToken(OAuthRedirectUriQuery redirectUriQuery)
            throws OAuthErrorException, OAuthIOException {
        OAuthRequest copy = basicTokenRequest.copy();
        copy.getParamsByMethod().addCode(redirectUriQuery.getCode());
        return new OAuthToken(execute(copy));
    }

    // -------------------- refresh token ---------------------------

    /**
     * Initialize a basic refresh token request.
     *
     * <p>No need to set `grant_type`, `client_id`, `client_secret`.
     *
     * @return a basic refresh token request
     * @see #requestForToken(OAuthRedirectUriQuery)
     */
    protected abstract OAuthRequest initBasicRefreshTokenRequest();

    /**
     * {@inheritDoc}
     *
     * <p>Query items(GET)/Form items(POST) added are as follows:
     * <ul>
     * <li>grant_type: {@code "refresh_token"}</li>
     * <li>client_id: {@code app.getClientId()}</li>
     * <li>client_secret: {@code app.getClientSecret()}</li>
     * <li>refresh_token: {@code token.getRefreshToken()}</li>
     * </ul>
     *
     * @param token {@inheritDoc}
     * @return {@inheritDoc}
     * @throws OAuthErrorException {@inheritDoc}
     * @throws OAuthIOException {@inheritDoc}
     */
    @Override
    public final OAuthToken refreshToken(OAuthToken token)
            throws OAuthErrorException, OAuthIOException {
        OAuthRequest copy = basicRefreshTokenRequest.copy();
        copy.getParamsByMethod().addRefreshToken(token.getRefreshToken());
        return new OAuthToken(execute(copy));
    }

    // -------------------- error -----------------------------------

    /**
     * {@inheritDoc}
     *
     * <p>Standard oauth client read `error` from response data.
     *
     * @param response {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    protected String getErrorFromResponse(OAuthResponse response) {
        return (String) response.getData().get("error");
    }

    /**
     * {@inheritDoc}
     *
     * <p>Standard oauth client read `error_description` from response data.
     *
     * @param response {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    protected String getErrorDescriptionFromResponse(OAuthResponse response) {
        return (String) response.getData().get("error_description");
    }

    @Override
    protected OAuthErrorException newOAuthErrorException(
            OpenPlatform openPlatform, String error, String errorDescription) {
        if (doesTheErrorMeanThatRefreshTokenHasExpired(error)) {
            return new RefreshTokenHasExpiredException(openPlatform);
        } else {
            return super.newOAuthErrorException(openPlatform, error, errorDescription);
        }
    }

    /**
     * Does the error mean that refresh token has expired.
     *
     * @param error error, require nonnull
     * @return {@code true} if the error mean that refresh token has expired, otherwise
     *         {@code false}
     */
    @Override
    protected abstract boolean doesTheErrorMeanThatRefreshTokenHasExpired(String error);

}
