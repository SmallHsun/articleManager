package com.example.articlemanager.sevice;

import com.example.articlemanager.pojo.Article;
import com.example.articlemanager.pojo.PageBean;

public interface ArticleService {

    //新增文章
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    Article findById(Integer id);

    void delete(Integer id);
}
