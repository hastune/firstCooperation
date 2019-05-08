package com.firstcooperation.blog.utils;


/**
 * 通用返回类
 */
public class Response {

    // 状态码
    protected StatusCode code;
    // 状态信息
    protected String message;
    // 返回数据
    protected Object data;

    public Response(){
        this.code = StatusCode.Success;
        this.message = "操作成功";
    }

    public String getMessage() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }
}
