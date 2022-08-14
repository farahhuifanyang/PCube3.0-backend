package com.dssc.pcube3.entityservice.controller;

import com.dssc.pcube3.entityservice.entity.Article;
import com.dssc.pcube3.entityservice.entity.EsArticleCondition;
import com.dssc.pcube3.entityservice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/{id}")
    public Article getById(@PathVariable String id) {
        return articleService.queryById(id);
    }

    @GetMapping("/condition")
    public List<Article> getByCondition(@RequestParam(required = false) String title,
                                        @RequestParam(required = false) String content,
                                        @RequestParam(required = false) String theme,
                                        @RequestParam(required = false) String keywords) {
        EsArticleCondition esArticleCondition = new EsArticleCondition();
        esArticleCondition.setTitle(title);
        esArticleCondition.setContent(content);
        esArticleCondition.setTheme(theme);
        esArticleCondition.setKeywords(keywords);
        return articleService.query(esArticleCondition);
    }

    @GetMapping("/event/{id}")
    public List<Article> getByEventId(@PathVariable String id) {
        return articleService.getByEventId(id);
    }

    @GetMapping("/latest")
    public List<Article> getLatest(@RequestParam(required = false) Integer limit,
                                   @RequestParam(defaultValue = "台湾") String keyword) {
        if (limit == null || limit == 0) {
            limit = 20;
        }
        return articleService.getLatest(keyword, limit);
    }
}
