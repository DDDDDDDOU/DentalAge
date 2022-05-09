package com.sjtu.dyw.wxcodelogin.util;

import com.alibaba.fastjson.JSONObject;
import com.sjtu.dyw.wxcodelogin.properties.WxConfig;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class AccessTokenUtil {
    private String accessToken;

//    @Autowired
//    private static WxConfig wxConfig;

    private static AccessTokenUtil instance;

    public AccessTokenUtil(String s) {
        accessToken = s;
    }

    public static AccessTokenUtil getInstance(WxConfig wxConfig)
    {
        if (instance == null)
        {
            String accessToken = null;
            String getTokenUrl = wxConfig.getTokenUrl().replace("APPID", wxConfig.getAppId()).replace("SECRET", wxConfig.getAppSecret());
            String result = HttpClientUtil.doGet(getTokenUrl);
            JSONObject jsonObject = JSONObject.parseObject(result);
            accessToken = jsonObject.getString("access_token");
            instance = new AccessTokenUtil(accessToken);
        }
        return instance;
    }

}
