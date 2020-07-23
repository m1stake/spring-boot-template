/*
 * @author songfeifan-南昌泽诺信息科技有限公司
 * @date 2019/3/12 14:47
 */
package com.songfeifan.samples.sbt.wx;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix="we-chat-qy")
public class WeChatQyConfig {

    /**
     * 获取access_token的链接
     */
    private String tokenEndpoint;

    /**
     * 获取user_id的链接
     */
    private String userInfoEndpoint;

    /**
     * 发送消息链接
     */
    private String sendMsgEndpoint;

    /**
     * 临时素材上传链接
     */
    private String mediaUploadEndpoint;

    /**
     * 企业微信中的企业corpid
     */
    private String corpid;

    /**
     * 应用配置
     */
    private Map<String, AppConfig> app;

    @Data
    public static class AppConfig {
        /**
         * 应用secret
         */
        private String secret;

        /**
         * 应用id
         */
        private String agentId;
    }
}
