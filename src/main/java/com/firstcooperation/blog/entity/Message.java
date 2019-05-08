package com.firstcooperation.blog.entity;

import java.util.Date;
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
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    // 留言者id
	@TableField("message_id")
	private String messageId;
    // 博主id
	@TableField("user_id")
	private String userId;
    // 评论文章的id
	@TableField("article_id")
	private String articleId;
    // 评论的内容
	private String message;
    // 评论的时间
	@TableField("message_time")
	private Date messageTime;


}
