package com.application.entity.resp;

import lombok.Data;

//回复格式
@Data
public class RestBean<T> {
    private int code;
    private String reason;
    private T data;
}
