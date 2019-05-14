package com.firstcooperation.blog.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.firstcooperation.blog.entity.User;
import com.firstcooperation.blog.entity.Userinfo;
import com.firstcooperation.blog.service.EmailService;
import com.firstcooperation.blog.service.UserService;
import com.firstcooperation.blog.service.UserinfoService;
import com.firstcooperation.blog.utils.Response;
import com.firstcooperation.blog.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

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

    //加入邮箱服务
    @Autowired
    private EmailService emailService;
    //加入redis模版
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

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
        //将email封装进user
        String email = redisTemplate.opsForValue().get("email");
        user.setEmail(email);
        if(!this.userService.insert(user)){
            //注册失败
            response.setMessage("未知问题请联系管理员")
                    .setCode(StatusCode.Fail_Code);
        }
        return response;
    }

    /**
     * email是前台获得的参数，进行邮箱注册，得到校验码
     * @param email
     * @return
     */
    @RequestMapping("getCheckCode")
    public Response getCheckCode(String email){
        Response response = new Response();
        //校验下邮箱
        User user = userService.selectOne(new EntityWrapper<User>().eq("email",email));
        if(user == null) {

            //前台已经传来了email
            //生成6位数
            String checkCode = (int) ((Math.random() * 9 + 1) * 100000) + "";
            String codeMessage = "尊敬的用户您好，您的注册验证码为：" + checkCode;

            emailService.sendEmail(email, "注册验证码", codeMessage);

            //将验证码传入到redis
            //1小时失效
            redisTemplate.opsForValue().set("checkCode", checkCode, 60 * 60, TimeUnit.SECONDS);
            //如果redis存入验证码成功，我们就把email存入到session或者redis
            redisTemplate.opsForValue().set("email", email, 60 * 60, TimeUnit.SECONDS);
            response.setMessage("验证码已发送");

        }else{
            //说明邮箱已经注册了,提示用户重新输入邮箱
            response.setMessage("邮箱已存在，请重新输入！")
                                .setCode(StatusCode.VerifyCode_Send_Fail);
        }

        return response;
    }

    /**
     * 前台输入了校验码，后台取出redis的数据进行比对，
     * @param code
     * @return
     */
    @RequestMapping("eqCheckCode")
    public Response eqCheckCode(String code){
        Response response = new Response();

        //从redis从取出进行比对
        String eqCode = redisTemplate.opsForValue().get("checkCode");
        if(code.equals(eqCode)){
            response.setMessage("SUCCESS");
        }else {
            response.setMessage("校验码输入错误")
                                .setCode(StatusCode.Invalid_VerifyCode);
        }

        return response;
    }

}

