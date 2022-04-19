package com.application;

import com.application.repo.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyFirstWebApplicationTests {
    @Autowired
    AccountRepository accountRepository;


    @Test
    void contextLoads() {
        System.out.println(accountRepository.findAllByUsername("kamisora"));
    }

}
