package com.songfeifan.samples.sbt.service;

import com.songfeifan.samples.sbt.model.dto.MsgUploadInfo;
import com.songfeifan.samples.sbt.model.dto.UploadDTO;
import com.songfeifan.samples.sbt.model.vo.MsgVO;

import java.util.List;

public interface MsgService {

    List<MsgVO> upload(UploadDTO uploadDTO);

    void sendMsg(MsgUploadInfo uploadInfo);

}
