package com.songfeifan.samples.sbt.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SampleMsgDTO extends MsgDTO {

    private String serialNum;

    private String companyName;

    private String department;

    private String name;

}
