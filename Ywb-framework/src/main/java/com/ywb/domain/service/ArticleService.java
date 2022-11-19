package com.ywb.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ywb.domain.Dto.AddArticleDto;
import com.ywb.domain.Dto.ArticleDto;
import com.ywb.domain.ResponseResult;
import com.ywb.domain.entity.Article;
import com.ywb.domain.vo.PageVo;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult add(AddArticleDto article);

    ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, ArticleDto articleDto);

    ResponseResult getArticle(Long id);

    ResponseResult updateArticle(Article article);

    ResponseResult deleteArticle(Long id);
}
