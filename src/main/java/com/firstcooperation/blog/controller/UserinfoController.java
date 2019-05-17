package com.firstcooperation.blog.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.firstcooperation.blog.entity.User;
import com.firstcooperation.blog.entity.Userinfo;
import com.firstcooperation.blog.service.UserinfoService;
import com.firstcooperation.blog.utils.Response;
import com.firstcooperation.blog.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;

/**
 *  控制器
 *
 * @author kevin
 * @since 2019-05-08
 */
@RestController
@RequestMapping("/userinfo")
public class UserinfoController {

    @Autowired
    private UserinfoService userinfoService;

    /**
     * 获取用户信息
     * @param userid
     * @return
     */
    @RequestMapping("/getUserInfo")
    public Response getUserInfo(String userid){
        Response response = new Response();
        response.setData(userinfoService.selectById(userid));
        return response;
    }


    /**
     * 更新用户信息
     * @param userinfo
     * @return
     */
    @RequestMapping("/updateUserInfo")
    public Response updateUserInfo(Userinfo userinfo){
        Response response = new Response();
        if(!userinfoService.updateById(userinfo)){
            response.setMessage("操作失败");
            response.setCode(StatusCode.Fail_Code);
        }
        return response;
    }

    /**
     * 文件的上传
     * @param file 上传的文件
     * @param source 保存的分类名
     * @param httpSession
     * @return
     */
    @RequestMapping("/imgUpload")
    public Response imgUpload(MultipartFile file, String source, HttpSession httpSession){
        Response response = new Response();
        //获得源文件名 包括名字+后缀
        //file.getOriginalFilename();
        //获取后缀名
        String suffix = file.getOriginalFilename().split("\\.")[1];
        //使用时间毫秒值+后缀名保存
        String realName = System.currentTimeMillis()+"."+suffix;
        File path = new File("E://blog//"+source);
        File f = new File("E://blog//"+source+"//"+realName);
        if(!path.exists()){
            try {
                path.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                response.setMessage("f.exists")
                .setCode(StatusCode.Fail_Code);
                return response;
            }
        }
        if(!file.isEmpty()){
            try {
                FileOutputStream fout = new FileOutputStream(f);
                InputStream in = file.getInputStream();
                byte[] bytes = new byte[2048];
                int length = 0;
                while((length = in.read(bytes)) != -1){
                    fout.write(bytes);
                }
                fout.close();
                in.close();
                //上传完成
                //数据库中插入结果
                User user = (User)(httpSession.getAttribute("user"));
                Userinfo userinfo = new Userinfo();
                userinfo.setHeadImg(realName)
                        .setUserId(user.getUserId());
                userinfoService.update(userinfo,new EntityWrapper<Userinfo>().eq("user_id",userinfo.getUserId()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                response.setMessage("FileOutputStream")
                        .setCode(StatusCode.Fail_Code);
                return response;
            } catch (IOException e) {
                e.printStackTrace();
                response.setMessage("getInputStream")
                        .setCode(StatusCode.Fail_Code);
                return response;
            }
        }

        return response;

    }

}

