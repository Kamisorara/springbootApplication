package com.application.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    private static final String SECRET = "SJFM*$#SD#$%$#@";

    /**
     * 生成TOKEN header.paylo ad.signature
     */

    //生成token工具类
    public static String getToken(Map<String, String> map) {
        //设置令牌过期事件
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 6); // 6小时
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        //遍历map 生成payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });

        //指定过期时间
        String token = builder.withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(SECRET));//签名

        return token;
    }

    /**
     * 验证token合法性
     * 正常就会直接返回，如果不合法会抛出Exception
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }


//    /**
//     * 获取token信息
//     */
//    public static DecodedJWT getTokenInfo(String token) {
//        DecodedJWT verify = JWT.require(Algorithm.HMAC256("KAMISORA")).build().verify(token);
//        return verify;
//    }


}
