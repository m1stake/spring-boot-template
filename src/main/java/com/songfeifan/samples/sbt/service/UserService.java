package com.songfeifan.samples.sbt.service;


import com.songfeifan.samples.sbt.model.UserInfo;

public interface UserService {
    UserInfo loadByUsername(String username);
}
