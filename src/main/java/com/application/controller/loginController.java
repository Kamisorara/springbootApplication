package com.application.controller;

import com.application.entity.Account;
import com.application.mapper.AccountMapper;
import com.application.service.AccountService;
import com.application.service.VerifyService;
import com.application.utils.JWTUtils;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class loginController {


    @Autowired
    AccountMapper accountMapper;
    @Autowired
    AccountService accountService;
    @Autowired
    VerifyService verifyService;


    /**
     * 登录接口
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> judge(@RequestParam("username") String username,
                                     @RequestParam("password") String password
    ) {
        Map<String, Object> map = new HashMap<>();
        try {
            QueryWrapper<Account> accountQueryWrapper = new QueryWrapper<>();
            accountQueryWrapper.eq("user_name", username);
            Account accounts = accountMapper.selectOne(accountQueryWrapper);
            if (accounts.getPassword().equals(password)) {
                //登录成功生成token
                Map<String, String> payload = new HashMap<>();
                payload.put("userId", String.valueOf(accounts.getId()));
                payload.put("userName", accounts.getUserName());
                String token = JWTUtils.getToken(payload);
                map.put("code", 200);
                map.put("msg", "登录成功！");
                map.put("token", token);
            } else {
                map.put("code", 400);
                map.put("msg", "登录失败，请检查账号或密码是否正确！");
            }
        } catch (Exception e) {
            map.put("code", 400);
            map.put("msg", "该账号未进行注册，请前往注册账号,并检查输入是否正确");
        }
        return map;
    }

    /**
     * 注册接口
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String, Object> register(@RequestParam("username") String username,
                                        @RequestParam("password") String password,
                                        @RequestParam("password2") String passwordRepeat,
                                        @RequestParam("email") String email,
                                        @RequestParam("verify") String verify) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (verifyService.doVerify(email, verify) && password.equals(passwordRepeat)) {
                Account account = new Account();
                account.setUserName(username);
                account.setPassword(password);
                accountMapper.insert(account);
                map.put("code", 200);
                map.put("msg", email + "用户" + "注册成功！");
                return map;
            } else {
                map.put("code", 400);
                map.put("msg", "注册失败，请检查邮箱验证码或是密码是是否输入正确");
                return map;
            }
        } catch (Exception e) {
            map.put("code", 400);
            map.put("msg", "发生未知错误!");
            return map;
        }
    }

    /**
     * 验证邮箱
     */
    @RequestMapping(value = "/verify-code", method = RequestMethod.POST)
    public Map<String, Object> verifyCode(@RequestParam("email") String email) {
        Map<String, Object> map = new HashMap<>();
        try {
            verifyService.sendVerifyCode(email);
            map.put("code", 200);
            map.put("msg", "邮件发送成功");
            return map;
        } catch (Exception e) {
            map.put("code", 400);
            map.put("msg", "邮件发送失败！");
            return map;
        }
    }


    /**
     * 网站token验证
     */
    @RequestMapping("/token")
    public Map<String, Object> testToken(HttpServletRequest request) {
        String token = request.getHeader("authorization"); //在请求头中获取信息(前端的请求中携带了token)
        Map<String, Object> map = new HashMap<>();
        //验证令牌
        try {
            DecodedJWT verify = JWTUtils.verify(token);
            String userName = verify.getClaim("userName").asString();
            Integer userId = verify.getClaim("userId").asInt();
            String[] userInfo = {String.valueOf(userId), userName};
            map.put("userInfo", userInfo);
            //没有错误就是正确 返回用户名
            map.put("msg", "即将跳转!");
            map.put("code", 200);
            return map;
        } catch (TokenExpiredException e) {
            map.put("msg", "token已过期，请重新登录!");
            map.put("code", 400);
            return map;
        } catch (Exception e) {
            map.put("code", 400);
            map.put("msg", "请进行登录");
            return map;
        }
    }

    /**
     * 退出接口
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Map<String, Object> logoutSys() {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "退出成功!");
        map.put("code", 200);
        return map;
    }
}
