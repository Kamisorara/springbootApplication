package com.application.entity.resp;

import lombok.Data;

//回复格式
@Data
public class RestBean {
    private int code;
    private String reason;
    private String token;
    private String[] userInfo;
}
