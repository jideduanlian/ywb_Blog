package com.Ywb.runner;

import com.ywb.domain.entity.Article;
import com.ywb.domain.mapper.ArticleMapper;
import com.ywb.domain.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisCache redisCache;
    @Override
    public void run(String... args) throws Exception {
        //查询博客信息  id  viewCount
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> map = articles.stream().collect(Collectors.toMap(article -> article.getId().toString(), new Function<Article, Integer>() {
            @Override
            public Integer apply(Article article) {
                return article.getViewCount().intValue();
            }
        }));
        redisCache.setCacheMap("article:viewCount",map);
    }
}
