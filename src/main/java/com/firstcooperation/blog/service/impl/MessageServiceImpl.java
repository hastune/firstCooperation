package com.firstcooperation.blog.service.impl;

import com.firstcooperation.blog.dao.MessageMapper;
import com.firstcooperation.blog.entity.Message;
import com.firstcooperation.blog.service.MessageService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author kevin
 * @since 2019-05-08
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
