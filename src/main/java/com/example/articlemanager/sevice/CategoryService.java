package com.example.articlemanager.sevice;

import com.example.articlemanager.pojo.Category;

import java.util.List;

public interface CategoryService {

    //新增分類
    void add(Category category);

    //獲取分類列表
    List<Category> getList();
}
