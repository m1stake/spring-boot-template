package com.songfeifan.samples.sbt.security;

import com.songfeifan.samples.sbt.model.UserInfo;
import com.songfeifan.samples.sbt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo user = userService.loadByUsername(username);

        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("未找到用户名为：" + username + "的用户");
        }
    }
}
