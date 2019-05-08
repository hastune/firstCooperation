package com.firstcooperation.blog.dao;

import com.ruibo.icms.common.modules.domain.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *  Mapper 接口
 *
 * @author kevin
 * @since 2019-05-08
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
