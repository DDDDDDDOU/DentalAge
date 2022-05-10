package com.sjtu.djw.wxcodelogin.util;

import com.alibaba.fastjson.JSONObject;
import com.sjtu.djw.wxcodelogin.properties.WxConfig;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Date;

@Slf4j
@Component
@Data
@NoArgsConstructor
public class AccessTokenUtil {
    private String accessToken;

    private static Integer expire;
    private static Date date;

    private static Date currentDate;

    private static AccessTokenUtil instance;

    public AccessTokenUtil(String s) {
        accessToken = s;
    }

    public static AccessTokenUtil getInstance(WxConfig wxConfig)
    {

        if (date == null) {
            date = new Date();
        }
        currentDate = new Date();
        if (instance == null || ((int)((currentDate.getTime() - date.getTime()) / 1000) > expire))
        {
            String accessToken = null;
            String getTokenUrl = wxConfig.getTokenUrl().replace("APPID", wxConfig.getAppId()).replace("SECRET", wxConfig.getAppSecret());
            String result = HttpClientUtil.doGet(getTokenUrl);
            JSONObject jsonObject = JSONObject.parseObject(result);
            accessToken = jsonObject.getString("access_token");
            expire = jsonObject.getInteger("expires_in");
            date = new Date();
            instance = new AccessTokenUtil(accessToken);
        }
        return instance;
    }

}
