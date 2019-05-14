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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    // 用户的ID
    @TableId("user_id")
	private String userId;
    // 用户的昵称
	@TableField("nick_name")
	private String nickName;
    // 用户的邮箱
	private String email;
    // 用户密码
	private String password;


}
