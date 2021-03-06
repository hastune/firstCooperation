package com.firstcooperation.blog.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 关注表
 *
 * @author kevin
 * @since 2019-05-20
 */
@Data
@Accessors(chain = true)
public class Follow implements Serializable {

    private static final long serialVersionUID = 1L;

    // 追随者的id == user_id
    @TableId("follow_id")
	private Integer followId;
    // 被追随者的id
	@TableField("follows_id")
	private Integer followsId;
    // 点击追随时的时间
	@TableField("follow_time")
	private Date followTime;


}
