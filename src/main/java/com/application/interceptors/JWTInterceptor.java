package com.application.interceptors;

import com.application.utils.JWTUtils;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {

    //重写预先处理的方法，因为我们只需要做个拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> map = new HashMap<>();
        //获取请求头参数(token参数)
        String token = request.getHeader("Authorization");
        //验证令牌
        try {
            DecodedJWT verify = JWTUtils.verify(token);
            //如果没错，放行请求
            return true;
        } catch (TokenExpiredException e) {
            map.put("msg", "token已过期，请重新登录!");
        } catch (Exception e) {
            map.put("msg", "token发生错误");
        }
        map.put("state", false); //设置状态
        String json = new ObjectMapper().writeValueAsString(map);
        //这样可以解决一下跨域问题，因为自己定义的拦截器会让先于mapping执行所以不会携带这些信息，这边自己添加进去
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setContentType("application/json;charset=UTF-8"); //设置格式
        response.getWriter().println(json); //把对应的json返回回去
        return false;
    }
}
