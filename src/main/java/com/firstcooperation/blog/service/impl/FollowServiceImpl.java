package com.firstcooperation.blog.service.impl;

import com.firstcooperation.blog.dao.FollowMapper;
import com.firstcooperation.blog.entity.Follow;
import com.firstcooperation.blog.service.FollowService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author kevin
 * @since 2019-05-08
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {

}
