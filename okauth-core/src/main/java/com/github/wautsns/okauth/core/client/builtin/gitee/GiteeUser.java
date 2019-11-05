package com.github.wautsns.okauth.core.client.builtin.gitee;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.wautsns.okauth.core.client.core.dto.OAuthUser;
import com.github.wautsns.okauth.core.client.util.http.Response;

/**
 *
 * @author wautsns
 */
@JsonNaming(SnakeCaseStrategy.class)
public class GiteeUser extends OAuthUser {

    public GiteeUser(Response response) {
        super(response);
    }

    @Override
    public String getOpenId() {
        return getString("id");
    }

    @Override
    public String getNickname() {
        return getString("name");
    }

    @Override
    public String getAvatarUrl() {
        return getString("avatar_url");
    }

}