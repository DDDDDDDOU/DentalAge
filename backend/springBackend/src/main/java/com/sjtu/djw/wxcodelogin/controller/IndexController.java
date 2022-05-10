package com.sjtu.djw.wxcodelogin.controller;

import com.sjtu.djw.wxcodelogin.properties.WxConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Draco
 * @className: IndexController
 * @description:
 * @date 2022/3/24 18:41
 */
@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private WxConfig wxConfig;
    /**
     * 首页
     * @return
     */
    @RequestMapping
    public String index(Model model) throws UnsupportedEncodingException {
        // 微信扫码第二种方式需要
        String redirectUrl = URLEncoder.encode(wxConfig.getServer() + "/qrCodeSecondLogin/getWxUserInfo","UTF-8") ;
        model.addAttribute("wxConfig",wxConfig);
        model.addAttribute("redirectUrl",redirectUrl);
        return "index";
    }



}
