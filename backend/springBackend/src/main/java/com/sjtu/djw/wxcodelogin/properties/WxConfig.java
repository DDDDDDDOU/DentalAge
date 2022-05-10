package com.sjtu.djw.wxcodelogin.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Draco
 * @className: WxConfig
 * @description:
 * @date 2022/3/24 19:05
 */
@Component
@ConfigurationProperties(prefix = "wx")
@Data
@NoArgsConstructor
public class WxConfig {
    private String appId;
    private String appSecret;
    private String server;
    private String qrCodeUrl;
    private String tokenUrl;
    private String openIdUrl;
    private String userInfoUrl;
}
