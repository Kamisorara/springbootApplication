package com.application.controller;

import com.application.entity.Account;
import com.application.entity.Users;
import com.application.mapper.AccountMapper;
import com.application.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class sysController {

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * 修改密码
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public Map<String, Object> resetPassword(@RequestParam("userName") String userName,
                                             @RequestParam("password") String password) {
        Map<String, Object> map = new HashMap<>();
        UpdateWrapper<Account> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_name", userName);
        updateWrapper.set("password", password);
        int i = accountMapper.update(null, updateWrapper);
        if (i > 0) {
            map.put("code", 200);
            map.put("msg", "修改成功!");
        } else {
            map.put("code", 400);
            map.put("msg", "修改失败，请检查输入是否正确!");
        }
        return map;
    }

    /**
     * 查找全部表格数据
     */
    @RequestMapping("/userMessage")
    public Map<String, Object> getUserMessage() {
        Map<String, Object> map = new HashMap<>();
        List<Users> users = userMapper.selectList(null);
        map.put("code", 200);  //前端中因为自己台添加了axios拦截器，所以一定要返回数值，不然会直接拦截
        map.put("data", users);
        return map;
    }

    /**
     * 添加表格数据
     */
    @RequestMapping(value = "insertUsers", method = RequestMethod.POST)
    public Map<String, Object> insertUsers(@RequestParam("name") String name,
                                           @RequestParam("sex") String sex,
                                           @RequestParam("address") String address,
                                           @RequestParam("phone") String phone,
                                           @RequestParam("birth") String birth) {
        Map<String, Object> map = new HashMap<>();
        Users user = new Users();
        user.setName(name).setAddress(address).setBirth(birth).setPhone(phone).setSex(sex);
        userMapper.insert(user);
        map.put("code", 200);
        map.put("msg", "添加成功!");
        return map;
    }

    /**
     * 删除表格数据
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Map<String, Object> delete(@RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        userMapper.deleteById(id);
        map.put("code", 200);
        map.put("msg", "删除成功");
        return map;
    }
}
