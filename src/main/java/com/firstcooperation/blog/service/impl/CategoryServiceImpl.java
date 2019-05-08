package com.firstcooperation.blog.service.impl;

import com.firstcooperation.blog.dao.CategoryMapper;
import com.firstcooperation.blog.entity.Category;
import com.firstcooperation.blog.service.CategoryService;
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
