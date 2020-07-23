package com.songfeifan.samples.sbt.wx;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * access token 缓存
 */
abstract class AccessTokenCache {

    /**
     * {
     *     'appId': {
     *          expireTime:,
     *          accessToken:
     *     }
     * }
     */
    private static Map<String, Token> tokenCache = new ConcurrentHashMap<>();

    private AccessTokenCache() { }

    static String getAccessToken(String appId) {

        Token token = tokenCache.get(appId);
        long now = System.currentTimeMillis();

        if (token != null && token.getExpireTime() > now) {
            return token.getAccessToken();
        } else {
            return null;
        }

    }

    static void putAccessToken(String appId, String accessToken, int expireIn) {
        // 减100秒，避免时间差
        tokenCache.put(appId, new Token(accessToken, System.currentTimeMillis() + (expireIn - 100) * 1000));
    }

    static void disCache(String appId) {
        tokenCache.remove(appId);
    }

    @AllArgsConstructor
    @Data
    private static class Token {
        private String accessToken;
        private Long expireTime;
    }

}
