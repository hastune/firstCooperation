package com.firstcooperation.blog.utils;


import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class test {

    public void cramerTask(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("11111");
                // 调用的是TimerTask类中的cancel()方法
                this.cancel();
            }
        }, new Date());
    }

    public static  void  main(String[] args){
        new test().cramerTask();
    }
}
