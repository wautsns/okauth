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
package com.github.wautsns.okauth.core.client.builtin.oschina;

import com.github.wautsns.okauth.core.client.kernel.OAuth2AppInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * OSChina oauth2 app info.
 *
 * @author wautsns
 * @since May 22, 2020
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class OSChinaOAuth2AppInfo extends OAuth2AppInfo {

    /** Client id. */
    private String clientId;
    /** Client secret. */
    private String clientSecret;
    /** Redirect uri. */
    private String redirectUri;

}
