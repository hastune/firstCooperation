package com.firstcooperation.blog.service.impl;

import com.ruibo.icms.common.modules.domain.Message;
import com.ruibo.icms.common.modules.dao.MessageMapper;
import com.ruibo.icms.common.modules.service.MessageService;
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
