package com.songfeifan.samples.sbt.model.dto;

import com.songfeifan.samples.sbt.model.enums.MsgType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@ApiModel("工资条上传")
@Data
public class UploadDTO {

    @ApiModelProperty(hidden = true)
    private MultipartFile file;

    @ApiModelProperty("消息类型")
    private MsgType msgType;

}
