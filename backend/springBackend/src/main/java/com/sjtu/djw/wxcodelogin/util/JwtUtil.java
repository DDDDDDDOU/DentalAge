package com.sjtu.djw.wxcodelogin.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Date;
import java.util.HashMap;

public class JwtUtil {

    //过期时间15分钟
    private static final long EXPIRE_TIME = 60*60*1000;

    //生成签名,1小时后过期
    public static String sign(String eventKey,String openId, Integer userId){
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //使用openId作为私钥进行加密
        Algorithm algorithm = Algorithm.HMAC256(openId);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带eventKey userID生成签名
        return JWT.create().withHeader(header).withClaim("userId",userId)
                .withExpiresAt(date).sign(algorithm);
    }

    //校验token
    public static boolean verity(String token,String openId){
        try {
            Algorithm algorithm = Algorithm.HMAC256(openId);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}