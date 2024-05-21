package com.example.articlemanager.sevice.impl;

import com.example.articlemanager.mapper.ArticleMapper;
import com.example.articlemanager.pojo.Article;
import com.example.articlemanager.pojo.PageBean;
import com.example.articlemanager.sevice.ArticleService;
import com.example.articlemanager.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //創建PageBean 對象
        PageBean<Article> pb = new PageBean<>();
        //開啟分頁查詢引入pageHelper
        PageHelper.startPage(pageNum,pageSize);
        //調用mapper
        Map<String,Object>map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> as =articleMapper.list(userId,categoryId,state);
        Page<Article> p = (Page<Article>) as;
        System.out.println(p);
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public Article findById(Integer id) {
        Article article=articleMapper.findById(id);
        return article;
    }

    @Override
    public void delete(Integer id) {
        Article article = articleMapper.findById(id);
        if (article!=null){
            articleMapper.delete(id);
        }else {
            throw new RuntimeException("Article with id " + id + " does not exist");

        }

    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }


}
