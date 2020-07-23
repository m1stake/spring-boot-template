package com.songfeifan.samples.sbt.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class UserController {

    @PostMapping("login")
    public void login(String username, String password) {
        // pass
    }


}
