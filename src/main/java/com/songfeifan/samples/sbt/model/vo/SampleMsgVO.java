package com.songfeifan.samples.sbt.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SampleMsgVO extends MsgVO {

    private String serialNum;

    private String companyName;

    private String department;

    private String name;

}
