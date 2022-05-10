package com.sjtu.djw.wxcodelogin.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor

public class User {
    private Integer id;
    private String userName;
    private Boolean gender;
    private Date registerTime;
    private String openId;
    private String location;
}
