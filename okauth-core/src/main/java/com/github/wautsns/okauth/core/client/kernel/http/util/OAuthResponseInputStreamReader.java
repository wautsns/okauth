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
package com.github.wautsns.okauth.core.client.kernel.http.util;

import com.github.wautsns.okauth.core.util.Reader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;

/**
 * OAuth response input stream reader.
 *
 * @author wautsns
 * @since Feb 28, 2020
 */
public interface OAuthResponseInputStreamReader extends Serializable {

    /**
     * Read input stream as map.
     *
     * @param inputStream input stream, require nonnull
     * @return map
     * @throws IOException if IO exception occurs
     */
    Map<String, Serializable> readAsMap(InputStream inputStream) throws IOException;

    // -------------------- built-in readers ------------------------

    /** the reader for json input stream */
    OAuthResponseInputStreamReader JSON = Reader::readJsonAsMap;

}
