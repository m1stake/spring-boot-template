package com.songfeifan.samples.sbt.service.impl;

import com.songfeifan.samples.sbt.excel.ExcelImportUtil;
import com.songfeifan.samples.sbt.model.dto.MsgDTO;
import com.songfeifan.samples.sbt.model.dto.MsgUploadInfo;
import com.songfeifan.samples.sbt.model.dto.UploadDTO;
import com.songfeifan.samples.sbt.model.dto.WxMsgDTO;
import com.songfeifan.samples.sbt.model.enums.MsgType;
import com.songfeifan.samples.sbt.model.vo.MsgVO;
import com.songfeifan.samples.sbt.service.MsgService;
import com.songfeifan.samples.sbt.util.VelocityUtil;
import com.songfeifan.samples.sbt.wx.QyWxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MsgServiceImpl implements MsgService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MsgServiceImpl.class);

    private static final String APP_ID = "jf";

    private static final String MSG_TITLE = "一般消息";

    @Override
    public List<MsgVO> upload(UploadDTO uploadDTO) {

        Map<String, Object> map = new HashMap<>();

        List<MsgVO> r = new ArrayList<>();
        map.put("msgList", r);

        try (InputStream sheetFileIn = uploadDTO.getFile().getInputStream()) {
            ExcelImportUtil.parseData(map, uploadDTO.getMsgType().getTemplatePath(), sheetFileIn);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return r;
    }

    @Override
    public void sendMsg(MsgUploadInfo uploadInfo) {

        MsgType msgType = uploadInfo.getMsgType();
        List<? extends MsgDTO> msgList = uploadInfo.getMsgList();

        if (msgList != null) {

            String accessToken = QyWxUtil.getAccessToken(APP_ID);

            for (MsgDTO msg: msgList) {
                sendOne(accessToken, uploadInfo.getMonth(), msgType, msg);
            }
        }
    }

    private void sendOne(String accessToken, String month, MsgType msgType, MsgDTO msg) {

        // 发送
        String msgContent = VelocityUtil.merge(msgType.getWxMsgTemplatePath(), msg);

        WxMsgDTO msgParams = new WxMsgDTO(
                msg.getUsername(),
                QyWxUtil.getAppConfig(APP_ID).getAgentId(), MSG_TITLE, msgContent,
                QyWxUtil.getThumbMediaId(APP_ID));
        try {
            QyWxUtil.sendMsg(accessToken, msgParams);
        } catch (RuntimeException e) {
            LOGGER.error("消息发送失败", e);
        }
    }

}
