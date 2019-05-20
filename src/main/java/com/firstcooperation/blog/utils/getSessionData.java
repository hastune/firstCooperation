package com.firstcooperation.blog.utils;

import com.firstcooperation.blog.entity.User;

import javax.servlet.http.HttpSession;

public class getSessionData {
    private String key;

    public getSessionData(String key){
        this.key = key;
    }

    public getSessionData(){
        this.key = "user";
    }

    public User getUserValue(HttpSession httpSession){
        return (User) httpSession.getAttribute(key);
    }

    public Integer getUserId(HttpSession httpSession){
        return getUserValue(httpSession).getUserId();
    }
}
