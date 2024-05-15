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
        Category existingCategory  = categoryMapper.findByName(category.getCategoryName());
        if(existingCategory!=null){
            throw new RuntimeException("Category with name " + category.getCategoryName() + " already exists");
        }
        categoryMapper.add(category);
    }

    @Override
    public List<Category> getList() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return categoryMapper.getList(userId);
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.findById(id);
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        Category category = categoryMapper.findById(id);
        if(category==null){
            throw new RuntimeException("Category with id " + id + " does not exist");
        }
        categoryMapper.delete(id);
    }
}
