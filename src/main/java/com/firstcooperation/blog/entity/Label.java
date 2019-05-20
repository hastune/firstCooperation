package com.firstcooperation.blog.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 标签表
 *
 * @author kevin
 * @since 2019-05-20
 */
@Data
@Accessors(chain = true)
public class Label implements Serializable {

    private static final long serialVersionUID = 1L;

    // 标签的id
	@TableId(value = "label_id", type = IdType.AUTO)
	private Integer labelId;
    // 标签的名称
	@TableField("label_name")
	private String labelName;


}
