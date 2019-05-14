package com.firstcooperation.blog.service.impl;

import com.firstcooperation.blog.dao.UserMapper;
import com.firstcooperation.blog.entity.User;
import com.firstcooperation.blog.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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



}
