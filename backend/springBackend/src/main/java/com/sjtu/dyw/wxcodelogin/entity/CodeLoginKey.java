package com.sjtu.dyw.wxcodelogin.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Draco
 * @className: CodeLoginKey
 * @description:
 * @date 2022/3/24 19:02
 */
@Component
@Data
@NoArgsConstructor
public class CodeLoginKey {
    private Long id;
    private String eventKey;
    private String openId;

    public CodeLoginKey(String eventKey, String openId) {
        this.eventKey = eventKey;
        this.openId = openId;
    }
}
