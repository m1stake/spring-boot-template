package com.songfeifan.samples.sbt.model.enums;

/**
 * 消息类型
 */
public enum MsgType {

    /**
     * sample
     */
    SAMPLE("sample.xml", "sample.html.vm"),

    ;

    /**
     * excel导入配置文件路径
     */
    private String templatePath;

    /**
     * 企业微信消息模板地址
     */
    private String wxMsgTemplatePath;


    MsgType(String templatePath, String wxMsgTemplatePath) {
        this.templatePath = "excel_upload_template/" + templatePath;
        this.wxMsgTemplatePath = "qywx_msg_template/" + wxMsgTemplatePath;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public String getWxMsgTemplatePath() {
        return wxMsgTemplatePath;
    }

}
