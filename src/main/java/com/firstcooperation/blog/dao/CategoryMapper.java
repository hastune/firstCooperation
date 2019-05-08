package com.firstcooperation.blog.dao;

import com.firstcooperation.blog.entity.Category;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *  Mapper 接口
 *
 * @author kevin
 * @since 2019-05-08
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
