package com.firstcooperation.blog.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 上方导航栏分类表
 *
 * @author kevin
 * @since 2019-05-20
 */
@Data
@Accessors(chain = true)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    // 分类的id
    @TableId("category_id")
	private Integer categoryId;
    // 分类的名称
	@TableField("category_name")
	private String categoryName;


}
