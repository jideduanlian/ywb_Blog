package com.Ywb.controller;

import com.ywb.domain.Dto.AddArticleDto;
import com.ywb.domain.Dto.ArticleDto;
import com.ywb.domain.ResponseResult;
import com.ywb.domain.entity.Article;
import com.ywb.domain.service.ArticleService;
import com.ywb.domain.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article){
        return articleService.add(article);
    }
    @GetMapping("/list")
    //显示信息，并且模糊查询
    public ResponseResult<PageVo> list(Integer pageNum , Integer pageSize, ArticleDto articleDto){
        return articleService.list(pageNum,pageSize,articleDto);
    }
    @GetMapping("/{id}")
    public ResponseResult getArticle(@PathVariable("id") Long id){
        return articleService.getArticle(id);
    }
    @PutMapping
    public ResponseResult updateArticle(@RequestBody Article article){
        return articleService.updateArticle(article);
    }
    @DeleteMapping("/{id}")
    public ResponseResult deleteArticle(@PathVariable("id") Long id){
        return articleService.deleteArticle(id);
    }
}
