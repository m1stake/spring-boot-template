package com.songfeifan.samples.sbt.model.entity;

import lombok.Data;

/**
 * 用户
 */
@Data
public class UserDO {

    /**
     * id
     */
    private Long id;

    /**
     * oa id
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 部门
     */
    private String department;

    /**
     * 密码
     */
    private String password;

}
