package com.songfeifan.samples.sbt.model.dto;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class WxMsgDTO {

    private String touser;

    private String agentid;

    private Content mpnews;

    private String msgtype = "mpnews";

    private String safe = "1";

    public WxMsgDTO() { }

    public WxMsgDTO(String touser, String agentid, String title, String content, String thumbMediaId) {
        this.touser = touser;
        this.agentid = agentid;

        Content mpnews = new Content();
        mpnews.setArticles(Collections.singletonList(new Article(title, content, thumbMediaId)));

        this.mpnews = mpnews;
    }

    @Data
    public static class Content {
        private List<Article> articles;
    }

    /**
     * "title": "Title",
     * "thumb_media_id": "MEDIA_ID",
     * "author": "Author",
     * "content_source_url": "URL",
     * "content": "Content",
     * "digest": "Digest description"
     */
    @Data
    public static class Article {

        private String title;

        private String content;

        private String thumb_media_id;

        public Article() { }

        public Article(String title, String content, String thumb_media_id) {
            this.title = title;
            this.content = content;
            this.thumb_media_id = thumb_media_id;
        }

    }

}
