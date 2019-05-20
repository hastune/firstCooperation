package com.firstcooperation.blog.service.impl;

import com.firstcooperation.blog.entity.Label;
import com.firstcooperation.blog.dao.LabelMapper;
import com.firstcooperation.blog.service.LabelService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 标签表 服务实现类
 *
 * @author kevin
 * @since 2019-05-20
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {

}
