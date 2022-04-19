package com.application.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true) //链式注解
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "sex")
    String sex;

    @Column(name = "address")
    String address;

    @Column(name = "phone")
    String phone;

    @Column(name = "birth")
    String birth;
}
