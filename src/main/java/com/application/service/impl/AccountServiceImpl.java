package com.application.service.impl;

import com.application.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {


    @Override
    public boolean isRight(String oldPassword, String password) {
        if (oldPassword == null || password == null) {
            return false;
        }
        return oldPassword.equals(password);
    }
}
