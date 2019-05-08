package com.firstcooperation.blog.service.impl;

import com.ruibo.icms.common.modules.domain.Category;
import com.ruibo.icms.common.modules.dao.CategoryMapper;
import com.ruibo.icms.common.modules.service.CategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author kevin
 * @since 2019-05-08
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
