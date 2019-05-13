package com.firstcooperation.blog.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.firstcooperation.blog.entity.User;
import com.firstcooperation.blog.entity.Userinfo;
import com.firstcooperation.blog.service.UserService;
import com.firstcooperation.blog.service.UserinfoService;
import com.firstcooperation.blog.utils.Response;
import com.firstcooperation.blog.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 *  控制器
 *
 * @author kevin
 * @since 2019-05-08
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserinfoService userinfoService;

    /**
     * 用户登录
     * @param email
     * @param password
     * @param httpSession
     * @return
     */
    @RequestMapping("selectOne")
    public Response selectOne(String email, String password, HttpSession httpSession){
        Response response = new Response();
        User user =this.userService.selectOne(new EntityWrapper<User>().eq("email",email).and().eq("password",password));
        if(user != null){
            //登录成功
            //信息保存到session中
            httpSession.setAttribute("user",user);
            //创建userinfo表数据 默认为空
            this.userinfoService.insert(new Userinfo());
        }else{
            response.setMessage("邮箱或密码错误")
                    .setCode(StatusCode.Fail_Code);
        }
        return response;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("insertOneRow")
    public Response insertOneRow(User user){
        Response response = new Response();
        if(!this.userService.insert(user)){
            //注册失败
            response.setMessage("未知问题请联系管理员")
                    .setCode(StatusCode.Fail_Code);
        }
        return response;
    }
}

