package com.firstcooperation.blog.service.impl;

import com.ruibo.icms.common.modules.domain.Article;
import com.ruibo.icms.common.modules.dao.ArticleMapper;
import com.ruibo.icms.common.modules.service.ArticleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author kevin
 * @since 2019-05-08
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
