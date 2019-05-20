package com.firstcooperation.blog.service.impl;

import com.firstcooperation.blog.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailServiceImpl implements EmailService {

    //导入发送邮箱的地址
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    //打印日志


    @Override
    public void sendEmail(String to,String title,String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);
        message.setSentDate(new Date());
        //抄送
        //message.setCc()
        //密送
        //message.setBcc();
        mailSender.send(message);
    }
}
