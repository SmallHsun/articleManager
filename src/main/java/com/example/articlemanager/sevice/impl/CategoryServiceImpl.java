package com.example.articlemanager.sevice.impl;

import com.example.articlemanager.mapper.CategoryMapper;
import com.example.articlemanager.pojo.Category;
import com.example.articlemanager.sevice.CategoryService;
import com.example.articlemanager.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void add(Category category) {
        //補充屬性值創建文章人的ID，更新時間，創建時間
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateTime(LocalDateTime.now());
        category.setCreateUserId(userId);
        categoryMapper.add(category);
    }

    @Override
    public List<Category> getList() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return categoryMapper.getList(userId);
    }
}
