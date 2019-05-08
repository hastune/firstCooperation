package com.firstcooperation.blog.service.impl;

import com.ruibo.icms.common.modules.domain.User;
import com.ruibo.icms.common.modules.dao.UserMapper;
import com.ruibo.icms.common.modules.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
