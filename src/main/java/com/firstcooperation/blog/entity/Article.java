package com.firstcooperation.blog.entity;

import java.util.Date;
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
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    // 文章的id
    @TableId("article_id")
	private String articleId;
    // 文章的标题
	private String title;
    // 文章的内容
	private String context;
    // 文章创建的时间
	@TableField("create_time")
	private Date createTime;
    // 文章浏览的次数
	@TableField("view_count")
	private Long viewCount;
    // 所属用户的id
	@TableField("user_id")
	private String userId;
    // 文章所属于的分类
	@TableField("category_id")
	private String categoryId;


}
