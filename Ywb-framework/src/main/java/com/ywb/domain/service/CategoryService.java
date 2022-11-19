package com.ywb.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ywb.domain.ResponseResult;
import com.ywb.domain.entity.Category;
import com.ywb.domain.vo.CategoryVo;
import com.ywb.domain.vo.PageVo;

import java.util.List;


/**
 * 分类表(SgCategory)表服务接口
 *
 * @author makejava
 * @since 2022-11-07 20:56:00
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

    List<CategoryVo> listAllCategory();

    PageVo selectCategoryPage(Category category, Integer pageNum, Integer pageSize);

    ResponseResult addCategory(Category category);

    ResponseResult listById(Integer id);

    ResponseResult updateCategory(Category category);

    ResponseResult deleteCategory(Integer id);
}

