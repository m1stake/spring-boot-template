package com.songfeifan.samples.sbt.wx;

import com.alibaba.fastjson.JSON;
import com.songfeifan.samples.sbt.util.AppContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class QyWxUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(QyWxUtil.class);

    private QyWxUtil() {  }

    private static WeChatQyConfig qyConfig = AppContextUtil.getBean(WeChatQyConfig.class);


    /**
     * 发送消息
     */
    public static void sendMsg(String accessToken, Object params) {
        String url = String.format(qyConfig.getSendMsgEndpoint(),
                accessToken);
        postJson(url, params);
    }

    /**
     * 获取 access_token
     */
    public static String getAccessToken(String appId) {
        if (appId != null) {

            String tokenCache = AccessTokenCache.getAccessToken(appId);
            if (tokenCache != null) {
                return tokenCache;
            }

            Map<String, WeChatQyConfig.AppConfig> appConfigs = qyConfig.getApp();
            if (appConfigs != null) {
                WeChatQyConfig.AppConfig appConfig = appConfigs.get(appId);
                if (appConfig != null) {
                    String tokenUrl = String.format(qyConfig.getTokenEndpoint(),
                            qyConfig.getCorpid(),
                            appConfig.getSecret());

                    Map<String, Object> resp = get(tokenUrl);

                    String accessToken = resp.get("access_token").toString();
                    int expiresIn = (int) resp.get("expires_in");

                    AccessTokenCache.putAccessToken(appId, accessToken, expiresIn);

                    return accessToken;
                } else {
                    throw new RuntimeException("未找到appId[" + appId + "]对应的secret");
                }
            } else {
                throw new RuntimeException("应用配置为空");
            }
        } else {
            throw new RuntimeException("appId为空");
        }
    }

    /**
     * 获取用户Id
     */
    public static String getUserId(String accessToken, String code) {
        String userIdUrl = String.format(qyConfig.getUserInfoEndpoint(),
                accessToken,
                code);
        return get(userIdUrl, "UserId");
    }

    /**
     * 获取应用配置
     */
    public static WeChatQyConfig.AppConfig getAppConfig(String appId) {
        return qyConfig.getApp().get(appId);
    }

    /**
     * 请求接口
     */
    private static Map<String, Object> get(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> respEntity = restTemplate.getForEntity(url, Map.class);
        return respProcess(respEntity);
    }

    /**
     * 请求接口
     */
    private static String get(String url, String parameterKey) {
        Map<String, Object> resp = get(url);
        return resp.get(parameterKey).toString();
    }

    private static void postJson(String url, Object params) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf8");

        HttpEntity<String> requestEntity = new HttpEntity<>(JSON.toJSONString(params), headers);

        post(url, requestEntity);
    }

    private static Map<String, Object> postForm(String url, Map<String, Object> params) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "multipart/form-data");

        MultiValueMap<String, Object> formData = buildForm(params);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(formData, headers);

        return post(url, requestEntity);
    }

    private static Map<String, Object> post(String url, HttpEntity<?> requestEntity) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map> respEntity = restTemplate.postForEntity(url, requestEntity, Map.class);

        return respProcess(respEntity);
    }

    private static Map<String, Object> respProcess(ResponseEntity<Map> respEntity) {
        Map resp = respEntity.getBody();
        int statusCode = respEntity.getStatusCodeValue();
        if (statusCode == 200) {
            if (resp != null && resp.get("errcode").toString().equals("0")) {
                return resp;
            }
        }
        LOGGER.error("请求企业微信接口失败, statusCode: " + statusCode + ", resp: " + JSON.toJSONString(resp));
        throw new RuntimeException("请求企业微信接口失败");
    }

    private static MultiValueMap<String, Object> buildForm(Map<String, Object> params) {
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        params.forEach((k, v) -> form.put(k, Collections.singletonList(v)));
        return form;
    }

    /**
     * 企业微信消息图片
     */
    public static String getThumbMediaId(String appId) {

        String thumbMediaId = ThumbMediaCache.get(appId);
        if (thumbMediaId != null) {
            return thumbMediaId;
        }

        Map<String, Object> formData = new HashMap<>();
        formData.put("media", new ClassPathResource("/qywx_msg_template/thumb_img.png"));

        String url = String.format(qyConfig.getMediaUploadEndpoint(), getAccessToken(appId), "image");

        Map<String, Object> resp = postForm(url, formData);

        String mediaId = resp.get("media_id").toString();

        ThumbMediaCache.setCache(appId, mediaId);

        return mediaId;
    }
}


