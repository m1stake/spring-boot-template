package com.songfeifan.samples.sbt.controller;

import com.alibaba.fastjson.JSON;
import com.songfeifan.samples.sbt.model.dto.MsgDTO;
import com.songfeifan.samples.sbt.model.dto.MsgUploadInfo;
import com.songfeifan.samples.sbt.model.dto.SampleMsgDTO;
import com.songfeifan.samples.sbt.model.dto.UploadDTO;
import com.songfeifan.samples.sbt.model.enums.MsgType;
import com.songfeifan.samples.sbt.model.vo.MsgVO;
import com.songfeifan.samples.sbt.service.MsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Api(tags = "消息")
@RestController
@RequestMapping("msg")
public class MsgController {

    @Autowired
    private MsgService msgService;


    @ApiOperation("上传")
    @PostMapping("upload")
    public List<MsgVO> upload(@RequestPart MultipartFile file, UploadDTO uploadDTO) {

        uploadDTO.setFile(file);

        return msgService.upload(uploadDTO);
    }

    @ApiOperation("发送消息")
    @PostMapping("send")
    public void sendMsg(@RequestBody MsgUploadInfo uploadInfo) {

        if (uploadInfo.getMsgType() == MsgType.SAMPLE) {
            uploadInfo.setMsgList(JSON.parseArray(uploadInfo.getMsgListJson(), SampleMsgDTO.class));
        }

        msgService.sendMsg(uploadInfo);
    }

}
