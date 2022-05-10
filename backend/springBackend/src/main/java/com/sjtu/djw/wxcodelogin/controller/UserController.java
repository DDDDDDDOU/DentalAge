package com.sjtu.djw.wxcodelogin.controller;


import com.sjtu.djw.wxcodelogin.entity.User;
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

    @RequestMapping("/getUserById")
    public User getUserById() {
        User u = userMapper.findUserById(1);
        return u;
    }
}
