package com.firstcooperation.blog.dao;

import com.firstcooperation.blog.entity.Label;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签表 Mapper 接口
 *
 * @author kevin
 * @since 2019-05-20
 */
@Mapper
public interface LabelMapper extends BaseMapper<Label> {

}
