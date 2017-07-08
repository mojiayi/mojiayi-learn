package com.mojiayi.learn.spring.bean;

/**
 * Created by Administrator on 2017/7/8.
 */
public class SimpleResponse {
    private int code;
    private String desc;

    public SimpleResponse(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
