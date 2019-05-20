package com.firstcooperation.blog.dao;

import com.firstcooperation.blog.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *  Mapper 接口
 *
 * @author kevin
 * @since 2019-05-08
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据email或者name 以及password查询用户
     * @param EmailOrName
     * @param password
     * @return
     */
    User selectByNameOrEmail(@Param("en") String EmailOrName, @Param("password") String password);
}
