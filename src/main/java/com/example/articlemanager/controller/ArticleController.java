package com.example.articlemanager.controller;


import com.example.articlemanager.pojo.Article;
import com.example.articlemanager.pojo.Result;
import com.example.articlemanager.sevice.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {


    @Autowired
    private ArticleService articleService;
    @PostMapping("/add")
    public Result add(@RequestBody Article article){
        articleService.add(article);
        return Result.success();
    }

}
