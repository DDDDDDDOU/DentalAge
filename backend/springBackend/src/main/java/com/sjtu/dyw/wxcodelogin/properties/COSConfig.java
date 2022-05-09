package com.sjtu.dyw.wxcodelogin.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cos")
@Data
@NoArgsConstructor
public class COSConfig {
    private String secretId;
    private String secretKey;
    private String bucket;
    private String region;

}
