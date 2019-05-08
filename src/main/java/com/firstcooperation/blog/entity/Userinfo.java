package com.firstcooperation.blog.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author kevin
 * @since 2019-05-08
 */
@Data
@Accessors(chain = true)
public class Userinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    // 用户的id
    @TableId("user_id")
	private String userId;
    // 用户的头像图像地址
	@TableField("head_img")
	private String headImg;
    // 用户的个人介绍
	private String about;
    // 用户的地址
	private String address;
    // 用户的性别：0为男 1为女
	private Integer sex;
    // 用户的个人爱好
	private String hobby;
    // 用户的QQ号
	private String qq;
    // 用户的微信号
	private String wechat;
    // 用户的微博
	private String weibo;
    // 用户的手机号码
	private String phone;


}
