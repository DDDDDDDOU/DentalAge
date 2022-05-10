package com.sjtu.djw.wxcodelogin.service;

import com.sjtu.djw.wxcodelogin.entity.User;
import com.sjtu.djw.wxcodelogin.mapper.UserMapper;
import com.sjtu.djw.wxcodelogin.util.JwtUtil;
import com.sjtu.djw.wxcodelogin.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService {
    @Autowired
    UserMapper userMapper;

    public String login(String eventKey, String openId, String password) {
        String token = null;
        try {
            //校验用户是否存在
            User user = userMapper.findUserByOpenId(openId);
            if(user == null){
                ResultJson.loginError("用户不存在");
            }else{
                // 生成token，将 user openId 、id保存到 token 里面
                token = JwtUtil.sign(eventKey,user.getOpenId(),user.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

}
