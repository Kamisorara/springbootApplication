package com.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.application.mapper")
public class MyFirstWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyFirstWebApplication.class, args);
    }

}
