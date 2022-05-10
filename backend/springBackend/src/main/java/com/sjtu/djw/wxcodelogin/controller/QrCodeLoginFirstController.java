package com.sjtu.djw.wxcodelogin.controller;


import com.alibaba.fastjson.JSONObject;
import com.sjtu.djw.wxcodelogin.entity.User;
import com.sjtu.djw.wxcodelogin.mapper.TokenMapper;
import com.sjtu.djw.wxcodelogin.mapper.UserMapper;
import com.sjtu.djw.wxcodelogin.properties.WxConfig;
import com.sjtu.djw.wxcodelogin.service.UserService;
import com.sjtu.djw.wxcodelogin.util.*;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Locale;

/**
 * @author Draco
 * @className: QrCodeLoginFirstController
 * @description: 微信扫码登录
 * @date 2022/3/24 18:49
 */
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("qrCodeFirstLogin")
public class QrCodeLoginFirstController {


    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private UserMapper userMapper;


    /**
     *  获取登录二维码
     * @return
     */
    @CrossOrigin
    @GetMapping("/getQrCode")
    public ResultJson getQrCode(){
        try {
            // 获取token开发者
            String accessToken =AccessTokenUtil.getInstance(wxConfig).getAccessToken();
            String getQrCodeUrl = wxConfig.getQrCodeUrl().replace("TOKEN", accessToken);
            // 这里生成一个带参数的二维码，参数是scene_str
            String sceneStr = CodeLoginUtil.getRandomString(8);
            String json="{\"expire_seconds\": 604800, \"action_name\": \"QR_STR_SCENE\"" +", \"action_info\": {\"scene\": {\"scene_str\": \""+sceneStr+"\"}}}";
            String result  = HttpClientUtil.doPostJson(getQrCodeUrl,json);
            JSONObject jsonObject = JSONObject.parseObject(result);
            jsonObject.put("sceneStr",sceneStr);
            return ResultJson.ok(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultJson.error(e.getMessage());
        }
    }


    /**
     *  获取accessToken
     * @return
     */

    /**
     *  验证签名
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/checkSign")
    public String checkSign ( HttpServletRequest request) throws Exception {
        log.info("===========>checkSign");
        //获取微信请求参数
        String signature = request.getParameter ("signature");
        String timestamp = request.getParameter ("timestamp");
        String nonce = request.getParameter ("nonce");
        String echostr = request.getParameter ("echostr");
        //参数排序。 token 就要换成自己实际写的 token
        String [] params = new String [] {timestamp,nonce,"dentalage"} ;
        Arrays.sort (params) ;
        //拼接
        String paramstr = params[0] + params[1] + params[2] ;
        //加密
        //获取 shal 算法封装类
        MessageDigest Sha1Dtgest = MessageDigest.getInstance("SHA-1") ;
        //进行加密
        byte [] digestResult = Sha1Dtgest.digest(paramstr.getBytes ("UTF-8"));
        //拿到加密结果
        String mysignature = CodeLoginUtil.bytes2HexString(digestResult);
        mysignature=mysignature.toLowerCase(Locale.ROOT);
        //是否正确
        boolean signsuccess = mysignature.equals(signature);
        //逻辑处理
        if (signsuccess && echostr!=null) {
            //peizhi  token
            return echostr  ;//不正确就直接返回失败提示．
        }else{
            JSONObject jsonObject = callback(request);
            return jsonObject.toJSONString();
        }
    }


    /**
     *  回调方法
     * @param request
     * @return
     * @throws Exception
     */
    public JSONObject callback(HttpServletRequest request) throws Exception{
        log.info("===========>callback");
        //request中有相应的信息，进行解析
        WxMpXmlMessage message= WxMpXmlMessage.fromXml(request.getInputStream());//获取消息流,并解析xml
        String messageType=message.getMsgType();								//消息类型
        String messageEvent=message.getEvent();								    //消息事件
        // openid
        String fromUser=message.getFromUser();									//发送者帐号
        // 生成二维码时穿过的特殊参数
        String eventKey=message.getEventKey();									//二维码参数


        //if判断，判断查询
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","200");
        if(messageType.equals("event")){
            jsonObject = null;
            //先根据openid从数据库查询  => 从自己数据库中查取用户信息 => jsonObject
            User user = userMapper.findUserByOpenId(fromUser);
            if(messageEvent.equals("SCAN")){
                //扫描二维码
                //return "欢迎回来";
            }
            if(messageEvent.equals("subscribe")){
                //关注
                //return "谢谢您的关注";
            }
            //没有该用户
            //从微信上中拉取用户信息
            String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" +AccessTokenUtil.getInstance(wxConfig).getAccessToken() +
                        "&openid=" + fromUser +
                        "&lang=zh_CN";
            String result = HttpClientUtil.doGet(url);
            jsonObject = JSONObject.parseObject(result);

            // 扫码成功，存入缓存
            if (user == null) {
                userMapper.insert(eventKey,fromUser);
                user = userMapper.findUserByOpenId(fromUser);
            }
            String token = JwtUtil.sign(eventKey,fromUser, user.getId());
            String findToken = tokenMapper.findTokenByUserId(user.getId());
            if(findToken == null) {
                tokenMapper.insert(user.getId(),token,eventKey);
            } else {
                tokenMapper.updateTokenByUserId(token,user.getId(),eventKey);
            }
            jsonObject.put("token",token);
            return jsonObject;

        }
        return jsonObject;
    }

    /**
     *  根据二维码标识获取用户openId=>获取用户信息
     * @param eventKey
     * @return
     */
    @RequestMapping("login")
    public ResultJson login(String eventKey){
        String token = tokenMapper.findTokenByEventKey(eventKey);
        if (token != null) {
            tokenMapper.deleteTokenByEventKey(eventKey);
            return ResultJson.ok(token);
        } else {
            return ResultJson.error("未扫码成功！") ;
        }
    }


}

