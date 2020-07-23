package com.songfeifan.samples.sbt.model.dto;

import com.songfeifan.samples.sbt.model.enums.MsgType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class MsgUploadInfo {

    private String month;

    private MsgType msgType;

    private String msgListJson;

    @ApiModelProperty(hidden = true)
    private List<? extends MsgDTO> msgList;

}
