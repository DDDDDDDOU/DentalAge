package com.sjtu.djw.wxcodelogin.controller;


import com.sjtu.djw.wxcodelogin.annotation.TokenRequired;
import com.sjtu.djw.wxcodelogin.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @TokenRequired
    @RequestMapping("/getUserById")
    public String getUserById() {
        return "1231231";
    }
}
