package com.application.service;

public interface VerifyService {
    void sendVerifyCode(String mail);

    boolean doVerify(String mail, String code);
}
