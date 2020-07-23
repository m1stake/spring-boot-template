package com.songfeifan.samples.sbt.security;

import com.songfeifan.samples.sbt.model.UserInfo;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class SecurityUtil {

    private SecurityUtil() {  }

    public static UserInfo getCurrentUserInfo() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null) {
            UsernamePasswordAuthenticationToken authToken
                    = (UsernamePasswordAuthenticationToken) securityContext.getAuthentication();
            if (authToken != null) {
                return (UserInfo) authToken.getPrincipal();
            }
        }
        throw new RuntimeException("当前没有登录用户");
    }

}
