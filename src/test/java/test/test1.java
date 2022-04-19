package test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.HashMap;

public class test1 {


    @Test
    void contextLoads() { //获取令牌
        //设置令牌过期事件
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 60); //20秒后令牌失效
        HashMap<String, Object> map = new HashMap<>();
        String token = JWT.create()
                .withHeader(map) //header的默认值可以不写
                .withClaim("userId", 21) //payload 只要是非敏感信息都可以看
                .withClaim("userName", "kamisora")
                .withExpiresAt(calendar.getTime()) //指定令牌过期事件
                .sign(Algorithm.HMAC256("KAMISORA"));  //指定密钥  签名

        System.out.println(token);
    }


    @Test
    void verifyToken() { //验签
        //获取验证对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("KAMISORA")).build();
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6ImthbWlzb3JhIiwiZXhwIjoxNjQ5ODE1OTQ3LCJ1c2VySWQiOjIxfQ.wJv9OWZax47SxB-zh-rF-gOgypDZyqTjYUfsvIvPYjA");
        System.out.println(verify.getClaim("userId").asInt());
        System.out.println(verify.getClaim("userName").asString());

    }
}
