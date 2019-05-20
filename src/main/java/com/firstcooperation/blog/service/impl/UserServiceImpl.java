package com.firstcooperation.blog.service.impl;

import com.firstcooperation.blog.dao.UserMapper;
import com.firstcooperation.blog.entity.User;
import com.firstcooperation.blog.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.firstcooperation.blog.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author kevin
 * @since 2019-05-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 根据email或者name 以及password查询用户
     * @param nameOrEmail
     * @param password
     * @return
     */
    @Override
    public User selectByNameOrEmail(String nameOrEmail, String password) {
        return userMapper.selectByNameOrEmail(nameOrEmail,MD5Util.encode(password));
    }
}
