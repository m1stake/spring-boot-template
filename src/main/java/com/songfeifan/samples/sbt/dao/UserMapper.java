package com.songfeifan.samples.sbt.dao;

import com.songfeifan.samples.sbt.model.entity.UserDO;

/**
 * 用户
 */
public interface UserMapper {


    UserDO loadByUsername(String username);


}
