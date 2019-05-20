package com.firstcooperation.blog.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.firstcooperation.blog.entity.User;
import com.firstcooperation.blog.entity.Userinfo;
import com.firstcooperation.blog.service.EmailService;
import com.firstcooperation.blog.service.UserService;
import com.firstcooperation.blog.service.UserinfoService;
import com.firstcooperation.blog.utils.MD5Util;
import com.firstcooperation.blog.utils.Response;
import com.firstcooperation.blog.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
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
     * @param emailOrName
     * @param password
     * @param httpSession
     * @return
     */
    @RequestMapping("selectOne")
    public Response selectOne(String emailOrName, String password, HttpSession httpSession){
        Response response = new Response();
//        System.out.println(emailOrName);
//        System.out.println(password);
        //查询用户
        User user =this.userService.selectByNameOrEmail(emailOrName,password);
        if(user != null){
            //登录成功
            //信息保存到session中
            httpSession.setAttribute("user",user);
           response.setMessage("homePage.html?id="+user.getUserId());
        }else{
            response.setMessage("用户名或密码错误")
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
    public Response insertOneRow(@RequestBody User user){
        Response response = new Response();
        //将email封装进user
        String email = redisTemplate.opsForValue().get("email");
        user.setEmail(email);
        //实现MD5加密
        String password =  MD5Util.encode(user.getPassword());
        user.setPassword(password);
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd : hh-mm-ss");
        user.setCreateTime(new Date());
        if(!this.userService.insert(user)){
            //注册失败
            response.setMessage("未知问题请联系管理员")
                    .setCode(StatusCode.Fail_Code);
        }
        //创建userinfo表数据 默认为空
        Userinfo userinfo = new Userinfo();
        userinfo.setUserId(user.getUserId())
                .setHeadImg("")
                .setAbout("")
                .setAddress("")
                .setHobby("")
                .setPhone("")
                .setQq("")
                .setSex(1)
                .setWechat("")
                .setWeibo("");
        System.err.println(userinfo.getUserId());
        this.userinfoService.insert(userinfo);
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
        User user = userService.selectOne(new EntityWrapper<User>().eq("email",email));
        if(user == null) {
            //发送注册邮件
            sendEmail(emailService,email,redisTemplate,response);
        }
        else {
            //说明邮箱已经注册了,提示用户重新输入邮箱
            response.setMessage("邮箱已存在，请重新输入！")
                    .setCode(StatusCode.VerifyCode_Send_Fail);
        }

        return response;
    }

    /**
     * email是前台获得的参数
     * 这里进行的是修改密码的操作，与上面的注册用户不同
     * @param email
     * @return
     */
    @RequestMapping("getUserByEmail")
    public Response getUserByEmail(String email){
        Response response = new Response();
        User user = userService.selectOne(new EntityWrapper<User>().eq("email",email));
        if(user != null) {
            //前台已经传来了email
            //生成6位数
            sendEmail(emailService,email,redisTemplate,response);
        }
        else {
            //说明用户不存在，提示用户不存在，跳到注册页面
            response.setMessage("用户不存在，请重新输入！")
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


    @RequestMapping("updateOneRow")
    public Response updateOneRow(String password){
        Response response = new Response();
        //取出email
        String email = redisTemplate.opsForValue().get("email");
        EntityWrapper<User> ew = new EntityWrapper<>();
        //取出user
        User user = userService.selectOne(ew.eq("email",email));

        //对password进行加密
        //实现MD5加密
        String pass=  MD5Util.encode(password);
        if(pass.equals(user.getPassword())){
            //说明忘记密码和原来密码一致，提示用户
            response.setMessage("与原来密码一致，请重新输入")
                    .setCode(StatusCode.Fail_Code);
        }else{
            //说明与原来的不一致，我们进行更新操作
            user.setPassword(pass);
            ew.eq("user_id",user.getUserId());
            if(!userService.update(user,ew)){
                response.setMessage("修改失败！")
                        .setCode(StatusCode.Fail_Code);
            }
        }

        return response;

    }

    //开发一个发送邮件的公共方法
    public static void sendEmail(EmailService emailService,String email,RedisTemplate redisTemplate,Response response){
        String checkCode = (int) ((Math.random() * 9 + 1) * 100000) + "";
        String codeMessage = "尊敬的用户您好!" +
                "\n您的注册验证码为：" + checkCode+"。" +
                "\n本验证码在1小时内有效，请于1小时内完成注册操作！"+
                "\n 发送人：lureSky工作组";

        emailService.sendEmail(email, "注册验证码", codeMessage);

        //将验证码传入到redis
        //1小时失效
        redisTemplate.opsForValue().set("checkCode", checkCode, 60 * 60, TimeUnit.SECONDS);
        //如果redis存入验证码成功，我们就把email存入到session或者redis
        redisTemplate.opsForValue().set("email", email, 60 * 60, TimeUnit.SECONDS);
        response.setMessage("验证码已发送");
    }

}

