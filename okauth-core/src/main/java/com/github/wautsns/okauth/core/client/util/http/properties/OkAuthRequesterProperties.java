/**
 * Copyright 2019 the original author or authors.
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
package com.github.wautsns.okauth.core.client.util.http.properties;

import java.util.concurrent.TimeUnit;

import com.github.wautsns.okauth.core.client.util.http.Requester;
import com.github.wautsns.okauth.core.client.util.http.builtin.okhttp.OkHttpRequester;

/**
 * Okauth requester properties.
 *
 * @author wautsns
 */
public class OkAuthRequesterProperties {

    /** requester class, default is {@link OkHttpRequester} */
    private Class<? extends Requester> requesterClass;
    /** max concurrent requests */
    private Integer maxConcurrentRequests;
    /** max idle connection */
    private Integer maxIdleConnections;
    /** keep alive, if the keepAliveTimeUnit is not set, the value is invalid */
    private Long keepAlive;
    /** keep alive time unit, if the keepAlive is not set, the value is invalid */
    private TimeUnit keepAliveTimeUnit = TimeUnit.MILLISECONDS;
    /** connect time out milliseconds */
    private Integer connectTimeoutMilliseconds;

    /** Get {@link #requesterClass}. */
    public Class<? extends Requester> getRequesterClass() {
        return requesterClass;
    }

    /** Set {@link #requesterClass}. */
    public OkAuthRequesterProperties setRequesterClass(Class<? extends Requester> requesterClass) {
        this.requesterClass = requesterClass;
        return this;
    }

    /** Get {@link #maxConcurrentRequests}. */
    public Integer getMaxConcurrentRequests() {
        return maxConcurrentRequests;
    }

    /** Set {@link #maxConcurrentRequests}. */
    public OkAuthRequesterProperties setMaxConcurrentRequests(Integer maxConcurrentRequests) {
        this.maxConcurrentRequests = maxConcurrentRequests;
        return this;
    }

    /** Get {@link #maxIdleConnections}. */
    public Integer getMaxIdleConnections() {
        return maxIdleConnections;
    }

    /** Set {@link #maxIdleConnections}. */
    public OkAuthRequesterProperties setMaxIdleConnections(Integer maxIdleConnections) {
        this.maxIdleConnections = maxIdleConnections;
        return this;
    }

    /** Get {@link #keepAlive}. */
    public Long getKeepAlive() {
        return keepAlive;
    }

    /** Set {@link #keepAlive}. */
    public OkAuthRequesterProperties setKeepAlive(Long keepAlive) {
        this.keepAlive = keepAlive;
        return this;
    }

    /** Get {@link #keepAliveTimeUnit}. */
    public TimeUnit getKeepAliveTimeUnit() {
        return keepAliveTimeUnit;
    }

    /** Set {@link #keepAliveTimeUnit}. */
    public OkAuthRequesterProperties setKeepAliveTimeUnit(TimeUnit keepAliveTimeUnit) {
        this.keepAliveTimeUnit = keepAliveTimeUnit;
        return this;
    }

    /** Get {@link #connectTimeoutMilliSeconds}. */
    public Integer getConnectTimeoutMilliseconds() {
        return connectTimeoutMilliseconds;
    }

    /** Set {@link #connectTimeoutMilliSeconds}. */
    public OkAuthRequesterProperties setConnectTimeoutMilliseconds(
            Integer connectTimeoutMilliseconds) {
        this.connectTimeoutMilliseconds = connectTimeoutMilliseconds;
        return this;
    }

}
