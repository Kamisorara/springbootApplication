package com.application.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true) //链式注解
@TableName("tb_users")
public class Users {
    Long id;
    String name;
    String sex;
    String address;
    String phone;
    String birth;
}
