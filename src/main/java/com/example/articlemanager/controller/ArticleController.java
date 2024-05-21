package com.example.articlemanager.controller;


import com.example.articlemanager.pojo.Article;
import com.example.articlemanager.pojo.PageBean;
import com.example.articlemanager.pojo.Result;
import com.example.articlemanager.sevice.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {


    @Autowired
    private ArticleService articleService;
    @PostMapping("/add")
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(Integer pageNum,
                                          Integer pageSize,
                                          @RequestParam(required = false) Integer categoryId,
                                          @RequestParam(required = false)String state){
        PageBean<Article> pb = articleService.list(pageNum,pageSize,categoryId,state);

        return Result.success(pb);
    }


    @GetMapping("/detail")
    public Result<Article> detail (@RequestParam("id") Integer id){
        Article article=articleService.findById(id);

        return Result.success(article);
    }

    @DeleteMapping
    public Result delete(Integer id){
        articleService.delete(id);
        return Result.success();
    }


    @PutMapping
    public Result update(@RequestBody @Validated Article article){
        articleService.update(article);

        return Result.success();
    }


}
