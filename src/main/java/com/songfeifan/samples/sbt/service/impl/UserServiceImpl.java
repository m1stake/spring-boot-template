package com.songfeifan.samples.sbt.service.impl;


import com.songfeifan.samples.sbt.dao.UserMapper;
import com.songfeifan.samples.sbt.model.UserInfo;
import com.songfeifan.samples.sbt.model.entity.UserDO;
import com.songfeifan.samples.sbt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo loadByUsername(String username) {
        UserDO userDO = userMapper.loadByUsername(username);
        if (userDO != null) {
            return new UserInfo(userDO);
        } else {
            return null;
        }
    }
}
