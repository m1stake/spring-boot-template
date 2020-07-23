package com.songfeifan.samples.sbt.wx;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thumb media id 缓存
 */
public class ThumbMediaCache {


    /**
     * {
     *     'appId': {
     *          expireTime:,
     *          accessToken:
     *     }
     * }
     */
    private static Map<String, ThumbMediaIdObj> cache = new ConcurrentHashMap<>();

    private ThumbMediaCache() { }

    static String get(String appId) {

        ThumbMediaIdObj thumbMediaIdObj = cache.get(appId);

        long now = System.currentTimeMillis();

        if (thumbMediaIdObj != null && thumbMediaIdObj.getExpireTime() > now) {
            return thumbMediaIdObj.getThumbMediaId();
        } else {
            return null;
        }

    }

    static void setCache(String appId, String thumbMediaId) {
        int expireIn = 3 * 24 * 3600;
        // 减一小时，避免时间差
        cache.put(appId, new ThumbMediaIdObj(thumbMediaId,
                System.currentTimeMillis() + (expireIn - 3600) * 1000));
    }

    static void disCache(String appId) {
        cache.remove(appId);
    }

    @AllArgsConstructor
    @Data
    private static class ThumbMediaIdObj {
        private String thumbMediaId;
        private Long expireTime;
    }

}
